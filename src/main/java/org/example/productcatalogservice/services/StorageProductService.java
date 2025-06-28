package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.Role;
import org.example.productcatalogservice.dtos.UserDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.models.Scope;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Qualifier
@Service("sps")
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Product getProductById(Long id) {
      /*  Product product = (Product)redisTemplate.opsForHash().get("products",id);
        if(product == null) {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if(optionalProduct.isPresent()) {
                redisTemplate.opsForHash().put("products",id,optionalProduct.get());
                return optionalProduct.get();
            }
            return null;
        }
        return product; */
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.get();

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        Optional<Product> productOptional = Optional.ofNullable(getProductById(product.getId()));
        return productOptional.orElseGet(() -> productRepo.save(product));
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;

    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = Optional.ofNullable(getProductById(product.getId()));
        return productOptional.orElseGet(() -> productRepo.save(product));
    }

    @Override
    public Product getProductDetailsBasedOnUserRole(Long productId, Long userId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getScope().equals(Scope.LISTED)){
                return product;
            } else if(product.getScope().equals(Scope.UNLISTED)){
                // call user service
               // UserDto userDto = restTemplate.getForEntity("http://localhost:8090/user/{userId}", UserDto.class, userId).getBody();
                UserDto userDto = restTemplate.getForEntity("http://UserAuthentication/user/{userId}", UserDto.class, userId).getBody();
                if(userDto.getRole().equals(Role.ADMIN)) {
                    return product;
                }
            }
        }
        return null;
    }
}
