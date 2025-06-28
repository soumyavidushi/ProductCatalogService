package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;
import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProduct(Product input,Long id);

    Boolean deleteProduct(Long id);

    Product createProduct(Product product);

    Product getProductDetailsBasedOnUserRole(Long productId, Long userId);
}
