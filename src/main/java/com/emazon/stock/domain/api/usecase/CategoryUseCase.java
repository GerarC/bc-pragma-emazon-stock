package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.pagination.PaginationData;

import static com.emazon.stock.domain.utils.ValidationUtils.validateDescription;
import static com.emazon.stock.domain.utils.ValidationUtils.validateName;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(Category category) {
        validateName(category.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(category.getDescription(), DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT);
        try {
            categoryPersistencePort.getCategoryByName(category.getName());
            throw new EntityAlreadyExistsException(Category.class.getSimpleName(), category.getName());
        } catch (EntityNotFoundException e) {
            categoryPersistencePort.save(category);
        }
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public DomainPage<Category> getAllCategories(PaginationData paginationData) {
        return categoryPersistencePort.getAllCategories(paginationData);
    }
}
