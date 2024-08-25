package com.emazon.stock.adapters.driving.rest.service;

import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;


public interface ProductService {
    void save(ProductRequest product);
    // PageResponse<Product> getAllProducts(PaginationData paginationData);
    // List<CategoryResponse> getProductCategories(Long id);
}
