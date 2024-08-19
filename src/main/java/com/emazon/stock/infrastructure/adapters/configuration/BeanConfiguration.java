package com.emazon.stock.infrastructure.adapters.configuration;

import com.emazon.stock.domain.api.CategoryServicePort;
import com.emazon.stock.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.infrastructure.adapters.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock.infrastructure.adapters.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastructure.adapters.jpa.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public CategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

  }
