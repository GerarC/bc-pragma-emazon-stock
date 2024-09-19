package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

import java.util.List;

public interface ProductServicePort {
    void save(Product product);
    DomainPage<Product> getAllProducts(PaginationData paginationData);
    List<Category> getProductCategories(Long id);
    void addSupply(Long id, Product product);
    Product getProduct(Long id);
    List<Product> getProducts(List<Long> ids);
}
