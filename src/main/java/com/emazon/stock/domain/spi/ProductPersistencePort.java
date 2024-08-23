package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.DomainPage;

public interface ProductPersistencePort {
    void save(Product product);
    Product getCategory(Long id);
    Product getCategoryByName(String name);
    DomainPage<Product> getAllCategories(int page, String col, boolean asc);
}
