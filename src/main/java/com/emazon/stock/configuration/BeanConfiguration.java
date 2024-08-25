package com.emazon.stock.configuration;

import com.emazon.stock.adapters.driven.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock.adapters.driven.jpa.adapter.ProductJpaAdapter;
import com.emazon.stock.adapters.driven.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.ProductEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.adapters.driven.jpa.persistence.ProductRepository;
import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.api.ProductServicePort;
import com.emazon.stock.domain.api.usecase.BrandUseCase;
import com.emazon.stock.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.domain.api.usecase.ProductUseCase;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.adapters.driven.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import com.emazon.stock.domain.spi.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper, paginationJPAMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper, paginationJPAMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public ProductPersistencePort productPersistencePort() {
        return new ProductJpaAdapter(productRepository, productEntityMapper, paginationJPAMapper);
    }

    @Bean
    public ProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }

  }
