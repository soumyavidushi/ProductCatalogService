package org.example.productcatalogservice.repos;

import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);

    List<Product> findAll();
}
