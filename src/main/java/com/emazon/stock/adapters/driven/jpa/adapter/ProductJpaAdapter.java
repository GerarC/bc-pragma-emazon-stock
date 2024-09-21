package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.ProductEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.ProductRepository;
import com.emazon.stock.adapters.driven.jpa.specification.ProductSpecificationBuilder;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.ProductPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.filter.ProductFilter;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
    public DomainPage<Product> getAllProducts(ProductFilter filter, PaginationData paginationData) {
        Specification<ProductEntity> specs = ProductSpecificationBuilder.filterBy(filter);
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<ProductEntity> returnProducts = productRepository.findAll(specs, pageable);
        return productEntityMapper.toDomainPage(returnProducts);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format(DomainConstants.PRODUCT_NOT_FOUND_MESSAGE, id)));
        return categoryEntityMapper.toCategories(productEntity.getCategories().stream().toList());
    }

    @Override
    public void addSupply(Long id, Product product) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(String.format(DomainConstants.PRODUCT_NOT_FOUND_MESSAGE, id)));
        productEntity.setQuantity(productEntity.getQuantity() + product.getQuantity());
        productRepository.save(productEntity);
    }

    @Override
    public Product getProduct(Long id) {
        return productEntityMapper.toProduct(
                productRepository.findById(id).orElseThrow(()
                        -> new EntityNotFoundException(String.format(DomainConstants.PRODUCT_NOT_FOUND_MESSAGE, id)))
        );
    }
}
