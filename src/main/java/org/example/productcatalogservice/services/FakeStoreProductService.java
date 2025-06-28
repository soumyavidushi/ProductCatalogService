package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.FakeStoreProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Qualifier
@Service("fkps")
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id).getBody();
        if(fakeStoreProductDto != null) {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate1 = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate1.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        if(response.getBody() != null) {
            for(FakeStoreProductDto dto : response.getBody()) {
                Product product = from(dto);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        FakeStoreProductDto requestBody = from(product);

        // Step 3: Create HttpEntity to hold the DTO and headers
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate2 = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate2.postForEntity("https://fakestoreapi.com/products", requestEntity, FakeStoreProductDto.class).getBody();
        if(fakeStoreProductDto != null) {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public Product getProductDetailsBasedOnUserRole(Long productId, Long userId) {
        return null;
    }

    @Override
    public Product replaceProduct(Product input,Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        FakeStoreProductDto requestBody = from(input);

        // Step 3: Create HttpEntity to hold the DTO and headers
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(requestBody, headers);
        FakeStoreProductDto fakeStoreProductDto = this.putForEntity("https://fakestoreapi.com/products/{id}", requestEntity, FakeStoreProductDto.class, id).getBody();
        if(fakeStoreProductDto != null) {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        RestTemplate restTemplate4 = restTemplateBuilder.build();
        restTemplate4.delete("http://fakestoreapi.com/products/{id}", id);
        return true;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate3 = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate3.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate3.responseEntityExtractor(responseType);
        return restTemplate3.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
