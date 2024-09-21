package com.emazon.stock.adapters.driven.jpa.specification;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.domain.utils.filter.ProductFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecificationBuilder {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CATEGORIES = "categories";
    private static final String BRAND = "brand";

    private ProductSpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<ProductEntity> filterBy(ProductFilter filter) {
        if (filter == null) return null;
        return Specification.where(hasIds(filter.getIds()))
                .and(hasName(filter.getName()))
                .and(hasBrand(filter.getBrand()))
                .and(hasCategory(filter.getCategory()));
    }

    private static Specification<ProductEntity> hasIds(List<Long> ids) {
        return ids == null || ids.isEmpty()
                ? null
                : Specification.anyOf(ids.stream().map(ProductSpecificationBuilder::hasId).toList());
    }

    private static Specification<ProductEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? criteriaBuilder.conjunction()
                        : criteriaBuilder.equal(root.get(ID), id);
    }

    private static Specification<ProductEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.get(NAME), "%" + name + "%");
    }

    private static Specification<ProductEntity> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null || category.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.join(CATEGORIES)
                        .get(NAME), "%" + category + "%");

    }

    private static Specification<ProductEntity> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                brand == null || brand.isEmpty()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(root.join(BRAND)
                        .get(NAME), "%" + brand + "%");

    }
}
