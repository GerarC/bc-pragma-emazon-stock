package com.emazon.stock.adapters.driving.rest.service;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;

import java.util.List;

public interface CategoryService {
    void save(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long id);
    ResponsePage<CategoryResponse> getAllCategories(int page, String col, boolean asc);
}
