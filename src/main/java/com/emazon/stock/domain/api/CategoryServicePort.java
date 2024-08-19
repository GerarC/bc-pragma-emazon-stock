package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;

public interface CategoryServicePort {
    void save(Category category);
    Category getCategory(Long id);
    // List<Category> getAllCategories(Integer page, Integer size);
}
