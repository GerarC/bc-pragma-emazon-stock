package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.adapters.driving.rest.mapper.request.CategoryRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.CategoryResponseMapper;
import com.emazon.stock.adapters.driving.rest.service.CategoryService;
import com.emazon.stock.domain.api.CategoryServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryResponseMapper categoryResponseMapper;
    private final CategoryRequestMapper categoryRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public void save(CategoryRequest categoryRequest) {
        categoryServicePort.save(categoryRequestMapper.toCategory(categoryRequest));
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getCategory(id));
    }

    @Override
    public ResponsePage<CategoryResponse> getAllCategories(PaginationRequest paginationRequest) {
        return categoryResponseMapper.toResponsePage(categoryServicePort.getAllCategories(paginationRequestMapper.toPaginationData(paginationRequest)));
    }
}
