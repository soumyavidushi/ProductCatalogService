package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Qualifier
@Service("sps")
// @Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;


    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElse(null);
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
}
