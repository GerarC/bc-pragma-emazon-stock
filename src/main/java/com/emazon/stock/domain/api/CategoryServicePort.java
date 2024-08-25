package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

public interface CategoryServicePort {
    void save(Category category);
    Category getCategory(Long id);
    DomainPage<Category> getAllCategories(PaginationData paginationData);
}
