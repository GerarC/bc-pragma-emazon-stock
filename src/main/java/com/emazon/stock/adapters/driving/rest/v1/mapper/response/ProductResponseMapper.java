package com.emazon.stock.adapters.driving.rest.v1.mapper.response;


import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductResponse;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponses(List<Product> products);

    PageResponse<ProductResponse> toResponsePage(DomainPage<Product> brands);

    List<String> categoryNames(List<Category> categories);

    default String category(Category category){
        return category.getName();
    }

    default String brand(Brand brand){
        return brand.getName();
    }

    List<ProductCategoryResponse> toProductCategoryResponses(List<Category> categories);
}
