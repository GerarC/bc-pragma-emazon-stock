package com.emazon.stock.adapters.driving.rest.mapper.response;


import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toResponse(Brand brand);

    List<BrandResponse> toResponses(List<Brand> brands);

    ResponsePage<BrandResponse> toResponsePage(DomainPage<Brand> brands);
}
