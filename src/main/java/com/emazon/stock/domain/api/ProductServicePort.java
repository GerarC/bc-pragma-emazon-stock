package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.pagination.DomainPage;

import java.util.List;

public interface ProductServicePort {
    void save(Product product);
    DomainPage<Product> getAllProducts(int page, String col, boolean asc);
    List<Category> getProductCategories(Long id);
}
