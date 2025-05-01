package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Qualifier("sps")
    @MockitoBean
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void TestGetProductDetailsById_withValidProductId_RunSuccessfully(){
        // Arrange
        Long id = 3L;
        Product product = new Product();
        product.setId(3L);
        product.setName("Iphone");
        product.setDescription("Phone");
        product.setPrice(100000.00);
        when(productService.getProductById(id)).thenReturn(product);

        // Act
        ProductDto productDto = productController.getProductDetails(id);

        // Assert
        assertEquals("Iphone", productDto.getName());
        assertEquals("Phone", productDto.getDescription());
        assertEquals(100000.00, productDto.getPrice());
        assertEquals(id, productDto.getId());
        assertNotNull(productDto);
    }

    @Test
    public void TestGetProductDetailsById_withNegativeValidProductId_ThrowException(){
       // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductDetails(0L));
        assertEquals("Product id must be positive", exception.getMessage());
    }

    @Test
    public void TestGetProductDetailsById_ProductServiceCalledWithCorrectArguments_RunSuccessfully(){
    // Arrange
        Long productId = 5L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ProductDto productDto = productController.getProductDetails(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(productId, idCaptor.getValue());
    }
}