package com.emazon.stock.adapters.driving.service.impl;

import com.emazon.stock.adapters.driving.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.mapper.request.CategoryRequestMapper;
import com.emazon.stock.adapters.driving.mapper.response.CategoryResponseMapper;
import com.emazon.stock.adapters.driving.service.CategoryService;
import com.emazon.stock.domain.api.CategoryServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryResponseMapper categoryResponseMapper;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void save(CategoryRequest categoryRequest) {
        categoryServicePort.save(categoryRequestMapper.toCategory(categoryRequest));
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getCategory(id));
    }

    @Override
    public List<CategoryResponse> getAllCategories(int page, String col, boolean asc) {
        return categoryResponseMapper.toResponses(categoryServicePort.getAllCategories(page, col, asc));
    }
}
