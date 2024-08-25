package com.emazon.stock.adapters.driving.rest.mapper.request;


import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRequestMapper {
    Product toProduct(ProductRequest productRequest);
}
