package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.CategoryEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format(DomainConstants.CATEGORY_NOT_FOUND_ID, id)
        )));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryEntityMapper.toCategory(categoryRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(
                String.format(DomainConstants.CATEGORY_NOT_FOUND_NAME, name)
        )));
    }

    @Override
    public DomainPage<Category> getAllCategories(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<CategoryEntity> returnedCategories = categoryRepository.findAll(pageable);
        return categoryEntityMapper.toDomainPage(returnedCategories);
    }

}
