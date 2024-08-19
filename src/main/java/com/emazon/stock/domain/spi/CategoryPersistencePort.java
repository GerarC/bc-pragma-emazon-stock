package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {
    void save(Category category);
    Category getCategory(Long id);
    // List<Category> getAllCategories(Integer page, Integer size);
}
