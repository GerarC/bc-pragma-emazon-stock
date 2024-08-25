package com.emazon.stock.adapters.driven.jpa.mapper;


import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    @Named("brandWithoutProducts")
    @Mapping(target = "products", ignore = true)
    Brand withoutProducts(BrandEntity source);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toProduct(ProductEntity productEntity);

    @IterableMapping(qualifiedByName = "brandWithoutProducts")
    List<Brand> toBrands(List<BrandEntity> brandEntities);

    @Mapping(target = "page", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "count", source = "numberOfElements")
    @Mapping(target = "totalCount", source = "totalElements")
    @Mapping(target = "content", source = "content")
    @IterableMapping(qualifiedByName = "brandWithoutProducts")
    DomainPage<Brand> toDomainPage(Page<BrandEntity> brandEntities);
}
