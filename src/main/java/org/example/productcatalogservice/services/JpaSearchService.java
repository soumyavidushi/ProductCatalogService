package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.SortParam;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams) {
       // Sort sort = Sort.by(Sort.Direction.DESC, "price").and(Sort.by(Sort.Direction.DESC, "id"));

        Sort sort = null;
       if(!sortParams.isEmpty()){
           sort = Sort.by(sortParams.getFirst().getParamName()).descending();
       }

        for (int i=1;i<sortParams.size();i++) {
            assert sort != null;
            sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
        }
        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}
