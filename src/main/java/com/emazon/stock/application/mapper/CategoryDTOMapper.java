package com.emazon.stock.application.mapper;


import com.emazon.stock.application.dto.CategoryDTO;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDTOMapper {
    CategoryDTO toDto(Category category);

    Category toCategory(CategoryDTO categoryDTO);

    List<Category> toCategories(List<CategoryDTO> categoryDTOS);
}
