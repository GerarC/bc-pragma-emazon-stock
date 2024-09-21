package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductBrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductCategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
import com.emazon.stock.adapters.driving.rest.utils.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {

    MockMvc mockMvc;
    private final WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    public ProductControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Test
    void createProduct() throws Exception {
        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest(1L);
        ProductBrandRequest productBrandRequest = new ProductBrandRequest(1L);
        ProductRequest productRequest = new ProductRequest("name", "description", BigDecimal.ONE, 1L, List.of(productCategoryRequest), productBrandRequest);

        this.mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(productRequest)))
                .andExpect(status().isCreated());
        verify(productService, times(1)).save(any(ProductRequest.class));
    }

    @Test
    void getAllProducts() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true, 10);
        PageResponse<ProductResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new ProductResponse(1L, "burger", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList("fast-food"), "burger-kink"),
                new ProductResponse(2L, "burger2", "a burger", BigDecimal.valueOf(0), 1L, Collections.singletonList("fast-food"), "burger-kink")
        ));
        when(productService.getAllProducts(null, paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/products"))
                //.andExpect(content().json(JsonParser.toJson(mockDTOs)))
                .andExpect(status().isOk());
    }

    @Test
    void getProductCategories() throws Exception {
        List<ProductCategoryResponse> categoryResponses = List.of(
                new ProductCategoryResponse(1L, "nothing"),
                new ProductCategoryResponse(2L, "something")
        );
        when(productService.getProductCategories(1L)).thenReturn(categoryResponses);
        this.mockMvc.perform(get("/products/1/categories"))
                .andExpect(content().json(JsonParser.toJson(categoryResponses)))
                .andExpect(status().isOk());
    }

    @Test
    void addSupplies() throws Exception {
        Long id = 1L;
        ProductRequest productRequest = new ProductRequest(null, null, null, 1L, null, null);
        doNothing().when(productService).addSupply(id, productRequest);
        this.mockMvc.perform(put("/products/1/add-supply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(productRequest)))
                .andExpect(status().isOk());
    }
}