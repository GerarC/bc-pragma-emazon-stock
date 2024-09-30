package com.emazon.stock.adapters.driving.rest.v1.service;

import com.emazon.stock.adapters.driving.rest.v1.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;

public interface BrandService {
    void save(BrandRequest brandRequest);
    PageResponse<BrandResponse> getAllBrands(PaginationRequest paginationRequest);
}
