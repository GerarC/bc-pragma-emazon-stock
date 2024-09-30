package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.filter.ProductFilter;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

import java.util.List;

public interface ProductPersistencePort {
    void save(Product product);
    DomainPage<Product> getAllProducts(ProductFilter filter, PaginationData paginationData);
    List<Category> getProductCategories(Long id);
    void addSupply(Long id, Product product);
    void removeSupply(Long id, Product product);
    Product getProduct(Long id);
}
