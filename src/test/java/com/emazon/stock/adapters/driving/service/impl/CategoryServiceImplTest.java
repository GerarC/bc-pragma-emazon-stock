package com.emazon.stock.adapters.driving.service.impl;

import com.emazon.stock.adapters.driving.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.mapper.request.CategoryRequestMapper;
import com.emazon.stock.adapters.driving.mapper.response.CategoryResponseMapper;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Category;
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
        try{
            categoryService.getCategory(2L);
            assert(false);
        }catch(EntityNotFoundException e){
            assert(true);
        }
    }

    @Test
    void getAllCategories() {
        int page = 0;
        String col = "";
        boolean asc = true;
        List<Category> mockCategories = List.of(
                new Category(1L, "nothing", "description", null),
                new Category(2L, "something", "second description", null)
        );
        List<CategoryResponse> mockDTOs = List.of(
                new CategoryResponse(1L, "nothing", "description"),
                new CategoryResponse(2L, "something", "second description")
        );
        when(categoryServicePort.getAllCategories(page, col, asc)).thenReturn(mockCategories);
        when(categoryResponseMapper.toResponses(mockCategories)).thenReturn(mockDTOs);
        List<CategoryResponse> returnedDTOs = categoryService.getAllCategories(page, col, asc);
        verify(categoryServicePort).getAllCategories(page, col, asc);
        assertEquals(mockDTOs.size(), returnedDTOs.size());
        assertEquals(mockDTOs.get(0).getId(), returnedDTOs.get(0).getId());
    }
}