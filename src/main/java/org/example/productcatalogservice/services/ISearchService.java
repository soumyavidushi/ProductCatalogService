package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.SortParam;
import org.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams);
}
