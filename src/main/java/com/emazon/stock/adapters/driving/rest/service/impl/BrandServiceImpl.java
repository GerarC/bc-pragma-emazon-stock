package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.adapters.driving.rest.mapper.request.BrandRequestMapper;
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

    @Override
    public void save(BrandRequest brandRequest) {
        brandServicePort.save(brandRequestMapper.toBrand(brandRequest));
    }

    @Override
    public ResponsePage<BrandResponse> getAllBrands(int page, String col, boolean asc) {
        return brandResponseMapper.toResponsePage(brandServicePort.getAllBrands(page, col, asc));
    }
}
