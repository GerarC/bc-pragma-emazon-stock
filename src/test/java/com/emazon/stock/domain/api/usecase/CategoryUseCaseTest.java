package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Category category = new Category(1L, "nothing", "description", null);
        doThrow(new EntityNotFoundException("Category with name nothing not found"))
                .when(categoryPersistencePort).getCategoryByName("nothing");
        doNothing().when(categoryPersistencePort).save(category);
        categoryUseCase.save(category);
        verify(categoryPersistencePort).save(category);
    }

    @Test
    void saveNotName() {
        Category category = new Category(1L, "", "description", null);
        try{
            categoryUseCase.save(category);
            assert(false);
        } catch (EmptyFieldException e) {
            assert(true);
        }

        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveNotDescription() {
        Category category = new Category(1L, "name", "", null);
        try{
            categoryUseCase.save(category);
            assert(false);
        } catch (EmptyFieldException e) {
            assert(true);
        }
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveTooLargeName() {
        Category category = new Category(1L, "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "description", null);
        try{
            categoryUseCase.save(category);
            assert(false);
        } catch (OutOfBoundsException e) {
            assert(true);
        }
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveTooLargeDescription() {
        Category category = new Category(1L, "nothing", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", null);
        try{
            categoryUseCase.save(category);
            assert(false);
        } catch (OutOfBoundsException e) {
            assert(true);
        }
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void getCategory() {
    }
}