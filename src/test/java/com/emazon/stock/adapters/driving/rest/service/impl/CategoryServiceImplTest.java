package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.adapters.driving.rest.mapper.request.CategoryRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.CategoryResponseMapper;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @Mock
    private PaginationRequestMapper paginationRequestMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryRequest categoryRequest = new CategoryRequest("nothing", "description");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);

        categoryService.save(categoryRequest);
        verify(categoryServicePort).save(category);
    }

    @Test
    void getCategory() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryResponse categoryResponse = new CategoryResponse(1L, "nothing", "description");

        when(categoryServicePort.getCategory(1L)).thenReturn(category);
        when(categoryServicePort.getCategory(2L)).thenThrow(new EntityNotFoundException("Category with id 2 not found"));
        when(categoryResponseMapper.toResponse(category)).thenReturn(categoryResponse);

        CategoryResponse categoryReturned = categoryService.getCategory(1L);
        verify(categoryServicePort).getCategory(1L);
        assertEquals(categoryResponse, categoryReturned);

        assertThrows(EntityNotFoundException.class, () -> categoryService.getCategory(2L));
    }

    @Test
    void getAllCategories() {
        PaginationData paginationData = new PaginationData(0, "", true);
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true);
        DomainPage<Category> mockCategories = new DomainPage<>();
        mockCategories.setContent(List.of(
                new Category(1L, "nothing", "description", null),
                new Category(2L, "something", "second description", null)
        ));
        ResponsePage<CategoryResponse> mockDTOs = new ResponsePage<>();
        mockDTOs.setContent(List.of(
                new CategoryResponse(1L, "nothing", "description"),
                new CategoryResponse(2L, "something", "second description")
        ));

        when(categoryServicePort.getAllCategories(paginationData)).thenReturn(mockCategories);
        when(categoryResponseMapper.toResponsePage(mockCategories)).thenReturn(mockDTOs);
        when(paginationRequestMapper.toPaginationData(paginationRequest)).thenReturn(paginationData);

        ResponsePage<CategoryResponse> returnedDTOs = categoryService.getAllCategories(paginationRequest);
        verify(categoryServicePort).getAllCategories(paginationData);
        assertEquals(mockDTOs.getContent().size(), returnedDTOs.getContent().size());
        assertEquals(mockDTOs.getContent().get(0).getId(), returnedDTOs.getContent().get(0).getId());
    }
}