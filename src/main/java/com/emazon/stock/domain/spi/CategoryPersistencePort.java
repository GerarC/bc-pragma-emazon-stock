package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.DomainPage;

public interface CategoryPersistencePort {
    void save(Category category);
    Category getCategory(Long id);
    Category getCategoryByName(String name);
    DomainPage<Category> getAllCategories(int page, String col, boolean asc);
}
