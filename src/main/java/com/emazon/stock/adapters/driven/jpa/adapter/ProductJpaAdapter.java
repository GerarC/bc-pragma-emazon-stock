package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.ProductEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.ProductRepository;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
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
    private final CategoryEntityMapper categoryEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public DomainPage<Product> getAllProducts(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<ProductEntity> returnProducts = productRepository.findAll(pageable);
        return productEntityMapper.toDomainPage(returnProducts);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return categoryEntityMapper.toCategories(productEntity.getCategories().stream().toList());
    }

    @Override
    public void addSupply(Long id, Product product) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        productEntity.setQuantity(productEntity.getQuantity() + product.getQuantity());
        productRepository.save(productEntity);
    }
}
