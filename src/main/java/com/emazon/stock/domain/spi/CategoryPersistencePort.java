package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {
    void save(Category category);
    Category getCategory(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories(int page, String col, boolean asc);
    // List<Category> getAllCategories(Integer page, Integer size);
}
