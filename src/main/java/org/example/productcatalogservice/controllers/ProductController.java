package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.CategoryDto;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Qualifier("sps")
    @Autowired
    private IProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = from(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @GetMapping("/{productId}/{userId}")
    public ProductDto getProductDetailsBasedOnUserRole(@PathVariable long productId, @PathVariable long userId) {
        Product product = productService.getProductDetailsBasedOnUserRole(productId, userId);
        if(product != null) {
            return from(product);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id) {
        if(id<0) {
            throw new IllegalArgumentException("Product id must be greater than 0");
        } else if(id == 0) {
            throw new IllegalArgumentException("Product id must be positive");
        }
        Product product = productService.getProductById(id);
        return from(product);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(from(productDto));
        return from(product);
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,
                                     @RequestBody ProductDto productDto) {
        Product product = productService.replaceProduct(from(productDto), id);
        return from(product);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        if(product != null) {
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setImageUrl(product.getImageUrl());
            if(product.getCategory() != null) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setName(product.getCategory().getName());
                categoryDto.setId(product.getCategory().getId());
                categoryDto.setDescription(product.getCategory().getDescription());
                productDto.setCategory(categoryDto);
            }
        }
        return productDto;
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return  product;
    }
}
