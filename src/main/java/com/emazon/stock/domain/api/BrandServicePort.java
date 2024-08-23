package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;

public interface BrandServicePort {
    void save(Brand brand);
    DomainPage<Brand> getAllBrands(int page, String col, boolean asc);
    // List<Brand> getAllCategories(Integer page, Integer size);
}
