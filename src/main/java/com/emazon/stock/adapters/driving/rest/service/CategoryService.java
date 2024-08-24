package com.emazon.stock.adapters.driving.rest.service;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;

public interface CategoryService {
    void save(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long id);
    ResponsePage<CategoryResponse> getAllCategories(PaginationRequest paginationRequest);
}
