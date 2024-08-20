package com.emazon.stock.adapters.driving.service.impl;

import com.emazon.stock.adapters.driving.dto.CategoryDTO;
import com.emazon.stock.adapters.driving.mapper.CategoryDTOMapper;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryDTOMapper categoryDTOMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryDTO categoryDTO = new CategoryDTO(1L, "nothing", "description");
        when(categoryDTOMapper.toCategory(categoryDTO)).thenReturn(category);
        categoryService.save(categoryDTO);
        verify(categoryServicePort).save(category);
    }

    @Test
    void getCategory() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryDTO categoryDTO = new CategoryDTO(1L, "nothing", "description");
        when(categoryServicePort.getCategory(1L)).thenReturn(category);
        when(categoryServicePort.getCategory(2L)).thenThrow(new EntityNotFoundException("Category with id 2 not found"));
        when(categoryDTOMapper.toDto(category)).thenReturn(categoryDTO);
        CategoryDTO categoryReturned = categoryService.getCategory(1L);
        verify(categoryServicePort).getCategory(1L);
        assertEquals(categoryDTO, categoryReturned);
        try{
            categoryService.getCategory(2L);
            assert(false);
        }catch(EntityNotFoundException e){
            assert(true);
        }
    }
}