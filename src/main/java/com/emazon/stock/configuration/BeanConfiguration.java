package com.emazon.stock.configuration;

import com.emazon.stock.adapters.driven.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock.adapters.driven.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.api.usecase.BrandUseCase;
import com.emazon.stock.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.adapters.driven.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock.adapters.driven.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

  }
