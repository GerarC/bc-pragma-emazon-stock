package com.emazon.stock.adapters.driving.rest.v1.service;

import com.emazon.stock.adapters.driving.rest.v1.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;

public interface CategoryService {
    void save(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long id);
    PageResponse<CategoryResponse> getAllCategories(PaginationRequest paginationRequest);
}
