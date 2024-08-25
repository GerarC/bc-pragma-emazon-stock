package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.ProductBrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductCategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
import com.emazon.stock.adapters.driving.rest.utils.JsonParser;
import com.emazon.stock.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    public ProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void createProduct() throws Exception {
        ProductCategoryRequest  productCategoryRequest = new ProductCategoryRequest(1L);
        ProductBrandRequest productBrandRequest = new ProductBrandRequest(1L);
        ProductRequest productRequest = new ProductRequest("name", "description", BigDecimal.ONE, 1L, List.of(productCategoryRequest), productBrandRequest);

        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonParser.toJson(productRequest)))
                .andExpect(status().isCreated());
        verify(productService, times(1)).save(any(ProductRequest.class));
    }

}