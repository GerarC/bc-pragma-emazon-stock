package com.emazon.stock.adapters.driving.rest.service.impl;

import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.mapper.request.PaginationRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.request.ProductRequestMapper;
import com.emazon.stock.adapters.driving.rest.mapper.response.ProductResponseMapper;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
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


    @Override
    public void save(ProductRequest product) {
        productServicePort.save(productRequestMapper.toProduct(product));
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(PaginationRequest paginationRequest) {
        return productResponseMapper.toResponsePage(
                productServicePort.getAllProducts(
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
