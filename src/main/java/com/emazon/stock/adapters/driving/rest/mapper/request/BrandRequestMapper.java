package com.emazon.stock.adapters.driving.rest.mapper.request;


import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
}
