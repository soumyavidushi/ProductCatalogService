package org.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    String name;
    String description;
    String imageUrl;
    Double price;
    Category category;
    Boolean isPrime;
}
