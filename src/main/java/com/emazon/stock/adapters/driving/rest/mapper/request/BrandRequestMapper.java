package com.emazon.stock.adapters.driving.rest.mapper.request;


import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.domain.model.Brand;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
}
