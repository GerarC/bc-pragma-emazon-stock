package com.emazon.stock.adapters.driving.service;

import com.emazon.stock.adapters.driving.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void save(CategoryRequest categoryRequest);
    CategoryResponse getCategory(Long id);
    List<CategoryResponse> getAllCategories(int page, String col, boolean asc);
}
