package com.emazon.stock.adapters.driven.jpa.persistence;

import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
