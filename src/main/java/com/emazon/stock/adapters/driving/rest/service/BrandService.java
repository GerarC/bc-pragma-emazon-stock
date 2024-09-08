package com.emazon.stock.adapters.driving.rest.service;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;

public interface BrandService {
    void save(BrandRequest brandRequest);
    PageResponse<BrandResponse> getAllBrands(PaginationRequest paginationRequest);
}
