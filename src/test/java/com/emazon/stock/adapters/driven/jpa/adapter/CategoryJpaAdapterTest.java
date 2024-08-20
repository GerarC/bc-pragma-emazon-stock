package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.CategoryEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryJpaAdapterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void save() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryEntity categoryEntity = new CategoryEntity(1L, "nothing", "description");
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);
        categoryJpaAdapter.save(category);
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void getCategory() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryEntity categoryEntity = new CategoryEntity(1L, "nothing", "description");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.findById(2L)).thenThrow(new EntityNotFoundException("Category with id 2 not found"));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);
        Category categoryReturned = categoryJpaAdapter.getCategory(1L);
        verify(categoryRepository).findById(1L);
        assertEquals(category, categoryReturned);
        try{
            categoryJpaAdapter.getCategory(2L);
            assert(false);
        }catch(EntityNotFoundException e){
            assert(true);
        }
    }

    @Test
    void getCategoryByName() {
        Category category = new Category(1L, "nothing", "description", null);
        CategoryEntity categoryEntity = new CategoryEntity(1L, "nothing", "description");
        when(categoryRepository.findByName("nothing")).thenReturn(Optional.of(categoryEntity));
        when(categoryRepository.findByName("another")).thenThrow(new EntityNotFoundException("Category with name another not found"));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);
        Category categoryReturned = categoryJpaAdapter.getCategoryByName("nothing");
        verify(categoryRepository).findByName("nothing");
        assertEquals(category, categoryReturned);
        try{
            categoryJpaAdapter.getCategoryByName("another");
            assert(false);
        }catch(EntityNotFoundException e){
            assert(true);
        }
    }
}