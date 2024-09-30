package com.emazon.stock.adapters.driving.rest.v1.service.impl;

import com.emazon.stock.adapters.driving.rest.v1.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.request.filter.ProductFilterRequest;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.v1.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.v1.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.v1.mapper.request.ProductRequestMapper;
import com.emazon.stock.adapters.driving.rest.v1.mapper.request.filter.ProductFilterRequestMapper;
import com.emazon.stock.adapters.driving.rest.v1.mapper.response.ProductResponseMapper;
import com.emazon.stock.adapters.driving.rest.v1.service.ProductService;
import com.emazon.stock.domain.api.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductServicePort productServicePort;
    private final ProductRequestMapper productRequestMapper;
    private final PaginationRequestMapper paginationRequestMapper;
    private final ProductResponseMapper productResponseMapper;
    private final ProductFilterRequestMapper productFilterRequestMapper;


    @Override
    public void save(ProductRequest product) {
        productServicePort.save(productRequestMapper.toProduct(product));
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(ProductFilterRequest filter, PaginationRequest paginationRequest) {
        return productResponseMapper.toResponsePage(
                productServicePort.getAllProducts(
                        productFilterRequestMapper.toDomain(filter),
                        paginationRequestMapper.toPaginationData(paginationRequest)
                )
        );
    }

    @Override
    public List<ProductCategoryResponse> getProductCategories(Long id) {
        return productResponseMapper.toProductCategoryResponses(
                productServicePort.getProductCategories(id)
        );
    }

    @Override
    public void addSupply(Long id, ProductRequest productRequest) {
        productServicePort.addSupply(
                id,
                productRequestMapper.toProduct(productRequest)
        );
    }

    @Override
    public void removeSupply(Long id, ProductRequest productRequest) {
        productServicePort.removeSupply(
                id,
                productRequestMapper.toProduct(productRequest)
        );
    }

    @Override
    public ProductResponse getProduct(Long id) {
        return productResponseMapper.toResponse(
                productServicePort.getProduct(id)
        );
    }

    @Override
    public List<ProductResponse> getProducts(List<Long> ids) {
        return productResponseMapper.toResponses(
                productServicePort.getProducts(ids)
        );
    }
}
