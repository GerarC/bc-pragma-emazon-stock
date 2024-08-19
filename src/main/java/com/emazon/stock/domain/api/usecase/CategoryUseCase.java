package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.CategoryPersistencePort;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(Category category) {
        categoryPersistencePort.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }
}
