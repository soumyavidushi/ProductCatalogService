package org.example.productcatalogservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
public class TestModel extends BaseModel {
    private Integer numField;
}
