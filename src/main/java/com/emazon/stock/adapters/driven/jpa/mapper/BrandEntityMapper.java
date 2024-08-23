package com.emazon.stock.adapters.driven.jpa.mapper;


import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    List<Brand> toCategories(List<BrandEntity> brandEntities);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    DomainPage<Brand> toDomainPage(Page<BrandEntity> brandEntities);
}
