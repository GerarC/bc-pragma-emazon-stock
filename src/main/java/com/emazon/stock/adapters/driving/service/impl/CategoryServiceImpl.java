package com.emazon.stock.adapters.driving.service.impl;

import com.emazon.stock.adapters.driving.dto.CategoryDTO;
import com.emazon.stock.adapters.driving.mapper.CategoryDTOMapper;
import com.emazon.stock.adapters.driving.service.CategoryService;
import com.emazon.stock.domain.api.CategoryServicePort;
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
        return categoryDTOMapper.toDto(categoryServicePort.getCategory(id));
    }
}
