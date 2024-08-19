package com.emazon.stock.application.service.impl;

import com.emazon.stock.application.dto.CategoryDTO;
import com.emazon.stock.application.mapper.CategoryDTOMapper;
import com.emazon.stock.application.service.CategoryService;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryDTOMapper categoryDTOMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        categoryServicePort.save(categoryDTOMapper.toCategory(categoryDTO));
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        Category category = categoryServicePort.getCategory(id);
        return categoryDTOMapper.toDto(category);
    }
}
