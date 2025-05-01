package org.example.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Qualifier("sps")
    @MockitoBean
    private IProductService productService;

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetProducts_RunSuccessfully() throws Exception {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getAllProducts()).thenReturn(products);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Product 1");

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Product 2");

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        productDtos.add(productDto2);



        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(productDtos)));
    }

    @Test
    public void Test_CreateProduct_RunSuccessfully() throws Exception {
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Super Computer");
        productDto.setPrice(100000.00);

        Product product = new Product();
        product.setId(1L);
        product.setName("Super Computer");
        product.setPrice(100000.00);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(productDto)));

    }

}
