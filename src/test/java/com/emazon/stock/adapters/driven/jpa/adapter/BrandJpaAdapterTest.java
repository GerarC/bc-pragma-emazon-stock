package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.adapters.driven.jpa.utils.PaginationJPA;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandJpaAdapterTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @Mock
    private PaginationJPAMapper paginationJPAMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void save() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        BrandEntity brandEntity = new BrandEntity(1L, "nothing", "description", Collections.singletonList(new ProductEntity()));
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);
        brandJpaAdapter.save(brand);
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void getBrandByName() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        BrandEntity brandEntity = new BrandEntity(1L, "nothing", "description", Collections.singletonList(new ProductEntity()));
        when(brandRepository.findByName("nothing")).thenReturn(Optional.of(brandEntity));
        when(brandRepository.findByName("another")).thenThrow(new EntityNotFoundException("Brand with name another not found"));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);
        Brand brandReturned = brandJpaAdapter.getBrandByName("nothing");
        verify(brandRepository).findByName("nothing");
        assertEquals(brand, brandReturned);
        assertThrows(EntityNotFoundException.class, () -> brandJpaAdapter.getBrandByName("another"));
    }

    @Test
    void getAllBrands() {
        PaginationData paginationData = new PaginationData(0, "", true);
        PaginationJPA paginationJPA = new PaginationJPA(0, "", true);
        Page<BrandEntity> brandEntities = new PageImpl<>(List.of(
                new BrandEntity(1L, "nothing", "description", Collections.singletonList(new ProductEntity())),
                new BrandEntity(2L, "something", "second description", Collections.singletonList(new ProductEntity())
        )));
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));

        when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntities);
        when(brandEntityMapper.toDomainPage(any())).thenReturn(mockBrands);
        when(paginationJPAMapper.toJPA(paginationData)).thenReturn(paginationJPA);
        DomainPage<Brand> returnedBrands = brandJpaAdapter.getAllBrands(paginationData);
        assertEquals(mockBrands.getContent().get(0).getName(), returnedBrands.getContent().get(0).getName());
        assertEquals(mockBrands.getContent().get(1).getName(), returnedBrands.getContent().get(1).getName());
    }

    @Test
    void getAllBrandsSorted() {
        PaginationData paginationData = new PaginationData(0, "name", true);
        PaginationJPA paginationJPA = new PaginationJPA(0, "name", true);
        Page<BrandEntity> brandEntities = new PageImpl<>(List.of(
                new BrandEntity(1L, "nothing", "description", Collections.singletonList(new ProductEntity())),
                new BrandEntity(2L, "something", "second description", Collections.singletonList(new ProductEntity()))
        ));
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntities);
        when(brandEntityMapper.toDomainPage(any())).thenReturn(mockBrands);
        when(paginationJPAMapper.toJPA(paginationData)).thenReturn(paginationJPA);

        DomainPage<Brand> returnedBrands = brandJpaAdapter.getAllBrands(paginationData);
        assertEquals(mockBrands.getContent().get(0).getName(), returnedBrands.getContent().get(0).getName());
        assertEquals(mockBrands.getContent().get(1).getName(), returnedBrands.getContent().get(1).getName());
    }

    @Test
    void getAllBrandsSortedDesc() {
        PaginationData paginationData = new PaginationData(0, "name", false);
        PaginationJPA paginationJPA = new PaginationJPA(0, "name", false);
        Page<BrandEntity> brandEntities = new PageImpl<>(List.of(
                new BrandEntity(1L, "nothing", "description", Collections.singletonList(new ProductEntity())),
                new BrandEntity(2L, "something", "second description", Collections.singletonList(new ProductEntity()))
        ));
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setPage(paginationData.getPage());
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntities);
        when(brandEntityMapper.toDomainPage(any())).thenReturn(mockBrands);
        when(paginationJPAMapper.toJPA(paginationData)).thenReturn(paginationJPA);
        DomainPage<Brand> returnedBrands = brandJpaAdapter.getAllBrands(paginationData);
        assertEquals(mockBrands.getContent().get(0).getName(), returnedBrands.getContent().get(0).getName());
        assertEquals(mockBrands.getContent().get(1).getName(), returnedBrands.getContent().get(1).getName());
        assertEquals(paginationData.getPage(), returnedBrands.getPage());
    }

    @Test
    void getBrand() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        BrandEntity brandEntity = new BrandEntity(1L, "nothing", "description", null);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));
        when(brandRepository.findById(2L)).thenThrow(new EntityNotFoundException("Brand with id 2 not found"));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);
        Brand brandReturned = brandJpaAdapter.getBrand(1L);
        verify(brandRepository).findById(1L);
        assertEquals(brand, brandReturned);
        try {
            brandJpaAdapter.getBrand(2L);
            assert (false);
        } catch (EntityNotFoundException e) {
            assert (true);
        }
    }
}