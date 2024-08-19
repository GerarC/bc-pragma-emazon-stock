package com.emazon.stock.infrastructure.adapters.jpa.mapper;


import com.emazon.stock.domain.model.Category;
import com.emazon.stock.infrastructure.adapters.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

    List<Category> toCategories(List<CategoryEntity> categoryEntities);
}
