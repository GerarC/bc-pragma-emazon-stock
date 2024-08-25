package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

public interface BrandPersistencePort {
    void save(Brand brand);
    Brand getBrand(Long id);
    Brand getBrandByName(String name);
    DomainPage<Brand> getAllBrands(PaginationData paginationData);
}
