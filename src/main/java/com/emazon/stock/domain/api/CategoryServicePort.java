package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;

import java.util.List;

public interface CategoryServicePort {
    void save(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories(int page, String col, boolean asc);
    // List<Category> getAllCategories(Integer page, Integer size);
}
