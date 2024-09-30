package com.emazon.stock.adapters.driving.rest.v1.mapper.response;


import com.emazon.stock.adapters.driving.rest.v1.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponses(List<Category> categories);

    PageResponse<CategoryResponse> toResponsePage(DomainPage<Category> categories);
}
