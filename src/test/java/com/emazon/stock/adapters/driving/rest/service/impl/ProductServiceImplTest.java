package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductBrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductCategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.request.ProductRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.ProductResponseMapper;
import com.emazon.stock.domain.api.ProductServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductServicePort productServicePort;

    @Mock
    private ProductRequestMapper productRequestMapper;

    @Mock
    private ProductResponseMapper productResponseMapper;

    @Mock
    private PaginationRequestMapper paginationRequestMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Product mockProduct = new Product(null, "burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new Category()), new Brand());
        ProductRequest productRequest = new ProductRequest("burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new ProductCategoryRequest()), new ProductBrandRequest());

        when(productRequestMapper.toProduct(productRequest)).thenReturn(mockProduct);

        productService.save(productRequest);
        verify(productServicePort).save(mockProduct);
    }

    @Test
    void getAllBrands() {
        PaginationData paginationData = new PaginationData(0, "", true);
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true);
        DomainPage<Product> mockPage = getProductDomainPage();
        PageResponse<ProductResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new ProductResponse(1L, "burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList("fastfood"), "burgerkink"),
                new ProductResponse(2L, "burger2", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList("fastfood"), "burgerkink")
        ));

        when(productServicePort.getAllProducts(paginationData)).thenReturn(mockPage);
        when(productResponseMapper.toResponsePage(mockPage)).thenReturn(mockDTOs);
        when(paginationRequestMapper.toPaginationData(paginationRequest)).thenReturn(paginationData);

        PageResponse<ProductResponse> returnedDTOs = productService.getAllProducts(paginationRequest);

        verify(productServicePort).getAllProducts(paginationData);
        assertEquals(mockDTOs.getContent().size(), returnedDTOs.getContent().size());
        assertEquals(mockDTOs.getContent().get(0).getId(), returnedDTOs.getContent().get(0).getId());
    }

    private static DomainPage<Product> getProductDomainPage() {
        DomainPage<Product> mockPage = new DomainPage<>();
        mockPage.setContent(List.of(
                new Product(1L, "burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new Category(1L, "fastfood", "fastfood", null)), new Brand(1L, "burgerkink", "burgerkink", null)),
                new Product(2L, "burger2", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList(new Category(1L, "fastfood", "fastfood", null)), new Brand(1L, "burgerkink", "burgerkink", null))
        ));
        return mockPage;
    }

    @Test
    void getProductCategories() {
        List<Category> categories = List.of(
                new Category(1L, "nothing", "hey", null),
                new Category(2L, "something", "hey", null)
        );
        List<ProductCategoryResponse> categoryResponses = List.of(
                new ProductCategoryResponse(1L, "nothing"),
                new ProductCategoryResponse(2L, "something")
        );
        when(productServicePort.getProductCategories(1L)).thenReturn(categories);
        when(productResponseMapper.toProductCategoryResponses(categories)).thenReturn(categoryResponses);

        List<ProductCategoryResponse> returnedCategories = productService.getProductCategories(1L);
        verify(productServicePort).getProductCategories(any());
        verify(productResponseMapper).toProductCategoryResponses(any());
        assertEquals(categoryResponses.size(), returnedCategories.size());
        assertEquals(categoryResponses.get(0).getId(), returnedCategories.get(0).getId());
        assertEquals(categoryResponses.get(1).getName(), returnedCategories.get(1).getName());

    }
}