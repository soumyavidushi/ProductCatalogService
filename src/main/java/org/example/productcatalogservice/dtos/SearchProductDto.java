package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductDto {
    private String query;
    private Integer pageSize;
    private Integer pageNumber;
}
