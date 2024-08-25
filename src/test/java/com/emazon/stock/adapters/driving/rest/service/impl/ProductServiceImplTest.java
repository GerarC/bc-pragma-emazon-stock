package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.ProductBrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductCategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.mapper.request.ProductRequestMapper;
import com.emazon.stock.domain.api.ProductServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductServicePort productServicePort;

    @Mock
    private ProductRequestMapper productRequestMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Product mockProduct = new Product(null, "burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new Category()),  new Brand());
        ProductRequest productRequest = new ProductRequest("burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new ProductCategoryRequest()),  new ProductBrandRequest());

        when(productRequestMapper.toProduct(productRequest)).thenReturn(mockProduct);

        productService.save(productRequest);
        verify(productServicePort).save(mockProduct);
    }
}