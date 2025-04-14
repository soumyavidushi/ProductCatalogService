package org.example.productcatalogservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
