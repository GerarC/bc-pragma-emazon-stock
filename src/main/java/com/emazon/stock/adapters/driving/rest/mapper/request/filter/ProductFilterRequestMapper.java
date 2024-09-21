package com.emazon.stock.adapters.driving.rest.mapper.request.filter;

import com.emazon.stock.adapters.driving.rest.dto.request.filter.ProductFilterRequest;
import com.emazon.stock.domain.utils.filter.ProductFilter;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductFilterRequestMapper {
    ProductFilter toDomain(ProductFilterRequest productFilterRequest);
}
