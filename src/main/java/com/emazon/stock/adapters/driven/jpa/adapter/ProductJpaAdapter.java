package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.ProductEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.ProductRepository;
import com.emazon.stock.adapters.driven.jpa.utils.PageUtil;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.ProductPersistencePort;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public DomainPage<Product> getAllProducts(PaginationData paginationData) {
        Pageable pageable = PageUtil.createPageable(paginationJPAMapper.toJPA(paginationData));
        Page<ProductEntity> returnProducts = productRepository.findAll(pageable);
        return productEntityMapper.toDomainPage(returnProducts);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        // TODO: UH6
        return List.of();
    }
}
