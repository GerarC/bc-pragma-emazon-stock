package com.emazon.stock.adapters.driving.rest.mapper.request;


import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
