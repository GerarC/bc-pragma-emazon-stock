package com.emazon.stock.adapters.driving.service;

import com.emazon.stock.adapters.driving.dto.CategoryDTO;

public interface CategoryService {
    void save(CategoryDTO categoryDTO);
    CategoryDTO getCategory(Long id);
}
