package org.example.productcatalogservice.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testQueries() {
      /*  List<Product> prroductList = productRepo.findProductByOrderByPriceDesc();
        for(Product product: prroductList) {
            System.out.println(product.getName());
        } */

       // System.out.println(productRepo.findProductNameById(1L));

        System.out.println(productRepo.findCategoryNameFromProductId(12L));
    }
}