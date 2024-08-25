package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

public interface BrandServicePort {
    void save(Brand brand);
    DomainPage<Brand> getAllBrands(PaginationData paginationData);
}
