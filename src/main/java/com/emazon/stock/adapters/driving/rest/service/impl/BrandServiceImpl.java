package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.mapper.request.BrandRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.BrandResponseMapper;
import com.emazon.stock.adapters.driving.rest.service.BrandService;
import com.emazon.stock.domain.api.BrandServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {
    private final BrandServicePort brandServicePort;
    private final BrandResponseMapper brandResponseMapper;
    private final BrandRequestMapper brandRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;

    @Override
    public void save(BrandRequest brandRequest) {
        brandServicePort.save(brandRequestMapper.toBrand(brandRequest));
    }

    @Override
    public PageResponse<BrandResponse> getAllBrands(PaginationRequest paginationRequest) {
        return brandResponseMapper.toResponsePage(brandServicePort.getAllBrands(paginationRequestMapper.toPaginationData(paginationRequest)));
    }
}
