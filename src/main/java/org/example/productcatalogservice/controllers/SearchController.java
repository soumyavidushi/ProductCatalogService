package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.SearchProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody SearchProductDto searchProductDto  ) {
        return searchService.searchProducts(searchProductDto.getQuery());
    }
}
