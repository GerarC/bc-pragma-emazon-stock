package com.emazon.stock.application.service;

import com.emazon.stock.application.dto.CategoryDTO;
import com.emazon.stock.domain.model.Category;

public interface CategoryService {
    void save(CategoryDTO categoryDTO);
    CategoryDTO getCategory(Long id);
}
