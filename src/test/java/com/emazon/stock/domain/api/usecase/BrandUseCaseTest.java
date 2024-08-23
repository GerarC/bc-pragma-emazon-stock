package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        doThrow(new EntityNotFoundException("Brand with name nothing not found"))
                .when(brandPersistencePort).getBrandByName("nothing");
        doNothing().when(brandPersistencePort).save(brand);
        brandUseCase.save(brand);
        verify(brandPersistencePort).save(brand);
    }

    @Test
    void saveRepeatedName() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        when(brandPersistencePort.getBrandByName("nothing")).thenReturn(brand);
        doNothing().when(brandPersistencePort).save(brand);
        assertThrows(EntityAlreadyExistsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveNotName() {
        Brand brand = new Brand(1L, "", "description", null);
        assertThrows(EmptyFieldException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveNotDescription() {
        Brand brand = new Brand(1L, "name", "", null);
        assertThrows(EmptyFieldException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveTooLargeName() {
        Brand brand = new Brand(1L, "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "description", null);
        assertThrows(OutOfBoundsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveTooLargeDescription() {
        Brand brand = new Brand(1L,
                "nothing",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                null);
        assertThrows(OutOfBoundsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void getAllBrands() {
        int page = 0;
        String col = "";
        boolean asc = true;
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));
        when(brandPersistencePort.getAllBrands(page, col, asc)).thenReturn(mockBrands);
        DomainPage<Brand> returnedBrands = brandUseCase.getAllBrands(page, col, asc);
        verify(brandPersistencePort).getAllBrands(page, col, asc);
        assertEquals(mockBrands.getContent().size(), returnedBrands.getContent().size());
        assertEquals(mockBrands.getContent().get(0).getId(), returnedBrands.getContent().get(0).getId());
        assertEquals(mockBrands.getContent().get(1).getId(), returnedBrands.getContent().get(1).getId());
    }
}