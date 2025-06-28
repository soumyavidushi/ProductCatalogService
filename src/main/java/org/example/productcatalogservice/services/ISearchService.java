package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;

import java.util.List;

public interface ISearchService {
    List<Product> searchProducts(String query);
}
