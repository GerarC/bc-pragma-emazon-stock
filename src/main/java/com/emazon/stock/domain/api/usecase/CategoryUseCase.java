package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.exceptions.CategoryAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.DomainPage;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;

import java.util.List;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(Category category) {
        if(category.getName().trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if(category.getDescription().trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(category.getName().trim().length() > DomainConstants.CATEGORY_NAME_LENGTH_LIMIT)
            throw new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.CATEGORY_NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}));
        if(category.getDescription().trim().length() > DomainConstants.CATEGORY_NAME_LENGTH_LIMIT)
            throw new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}));

        try {
            categoryPersistencePort.getCategoryByName(category.getName());
            throw new CategoryAlreadyExistsException(Category.class.getSimpleName(), category.getName());
        } catch (EntityNotFoundException e) {
            categoryPersistencePort.save(category);
        }
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public DomainPage<Category> getAllCategories(int page, String col, boolean asc) {
        return categoryPersistencePort.getAllCategories(page, col, asc);
    }
}
