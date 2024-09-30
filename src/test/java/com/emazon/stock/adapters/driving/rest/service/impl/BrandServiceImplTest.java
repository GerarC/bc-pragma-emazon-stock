package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.v1.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.v1.mapper.request.BrandRequestMapper;
import com.emazon.stock.adapters.driving.rest.v1.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.v1.mapper.response.BrandResponseMapper;
import com.emazon.stock.adapters.driving.rest.v1.service.impl.BrandServiceImpl;
import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
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

    @Mock
    private PaginationRequestMapper paginationRequestMapper;

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
        PaginationData paginationData = new PaginationData(0, "", true, 10);
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true, 10);
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));
        PageResponse<BrandResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));

        when(brandServicePort.getAllBrands(paginationData)).thenReturn(mockBrands);
        when(brandResponseMapper.toResponsePage(mockBrands)).thenReturn(mockDTOs);
        when(paginationRequestMapper.toPaginationData(paginationRequest)).thenReturn(paginationData);

        PageResponse<BrandResponse> returnedDTOs = brandService.getAllBrands(paginationRequest);

        verify(brandServicePort).getAllBrands(paginationData);
        assertEquals(mockDTOs.getContent().size(), returnedDTOs.getContent().size());
        assertEquals(mockDTOs.getContent().get(0).getId(), returnedDTOs.getContent().get(0).getId());
    }
}