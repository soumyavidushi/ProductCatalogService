package org.example.productcatalogservice.repos;

import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<Product> findProductByOrderByPriceDesc();

    @Query(" select p.name from Product p where p.id=?1")
    String findProductNameById(Long id);

    @Query("select c.name from Category c join Product p on p.category.id = c.id where p.id=:pid")
    String findCategoryNameFromProductId(Long pid);


    // fn(String name, Integer i -> fn("soumya",1)} Positional Association
// fn(id: 1, name:"soumya") // Named Association
}
