package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.CategoryEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.DomainPage;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.utils.DomainConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void save(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found")));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryEntityMapper.toCategory(categoryRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Category with name " + name + " not found")));
    }

    @Override
    public DomainPage<Category> getAllCategories(int page, String col, boolean asc) {
        Pageable pageable;
        if (col == null || col.isEmpty()) {
            pageable = PageRequest.of(page, DomainConstants.PAGE_SIZE);
        } else {
            Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, col);
            pageable = PageRequest.of(page, DomainConstants.PAGE_SIZE).withSort(sort);
        }
        Page<CategoryEntity> returnedCategories = categoryRepository.findAll(pageable);
        return categoryEntityMapper.toDomainPage(returnedCategories);
    }
}
