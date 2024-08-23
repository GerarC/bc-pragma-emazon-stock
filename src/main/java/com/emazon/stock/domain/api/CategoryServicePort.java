package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.DomainPage;

public interface CategoryServicePort {
    void save(Category category);
    Category getCategory(Long id);
    DomainPage<Category> getAllCategories(int page, String col, boolean asc);
    // List<Category> getAllCategories(Integer page, Integer size);
}
