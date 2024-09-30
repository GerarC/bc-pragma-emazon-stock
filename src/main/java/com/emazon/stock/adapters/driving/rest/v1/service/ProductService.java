package com.emazon.stock.adapters.driving.rest.v1.service;

import com.emazon.stock.adapters.driving.rest.v1.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.filter.ProductFilterRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductResponse;

import java.util.List;


public interface ProductService {
    void save(ProductRequest product);
    PageResponse<ProductResponse> getAllProducts(ProductFilterRequest filter, PaginationRequest paginationRequest);
    List<ProductCategoryResponse> getProductCategories(Long id);
    void addSupply(Long id, ProductRequest productRequest);
    void removeSupply(Long id, ProductRequest productRequest);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getProducts(List<Long> ids);
}
