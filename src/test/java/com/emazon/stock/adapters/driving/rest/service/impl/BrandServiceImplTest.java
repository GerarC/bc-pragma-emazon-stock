package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.adapters.driving.rest.mapper.request.BrandRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.BrandResponseMapper;
import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandServiceImplTest {

    @Mock
    private BrandServicePort brandServicePort;

    @Mock
    private BrandResponseMapper brandResponseMapper;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        BrandRequest brandRequest = new BrandRequest("nothing", "description");

        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);

        brandService.save(brandRequest);
        verify(brandServicePort).save(brand);
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
        ResponsePage<BrandResponse> mockDTOs = new ResponsePage<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));
        when(brandServicePort.getAllBrands(page, col, asc)).thenReturn(mockBrands);
        when(brandResponseMapper.toResponsePage(mockBrands)).thenReturn(mockDTOs);
        ResponsePage<BrandResponse> returnedDTOs = brandService.getAllBrands(page, col, asc);
        verify(brandServicePort).getAllBrands(page, col, asc);
        assertEquals(mockDTOs.getContent().size(), returnedDTOs.getContent().size());
        assertEquals(mockDTOs.getContent().get(0).getId(), returnedDTOs.getContent().get(0).getId());
    }
}