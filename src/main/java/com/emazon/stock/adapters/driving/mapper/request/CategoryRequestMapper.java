package com.emazon.stock.adapters.driving.mapper.request;


import com.emazon.stock.adapters.driving.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.dto.response.CategoryResponse;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
