package com.emazon.stock.adapters.driven.jpa.persistence;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAll(Specification<ProductEntity> specs, Pageable pageable);
}
