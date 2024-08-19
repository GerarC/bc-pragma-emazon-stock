package com.emazon.stock.infrastructure.adapters.jpa.adapter;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.infrastructure.adapters.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastructure.adapters.jpa.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;

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
        return categoryEntityMapper.toCategory(categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
