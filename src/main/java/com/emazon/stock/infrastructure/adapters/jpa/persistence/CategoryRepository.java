package com.emazon.stock.infrastructure.adapters.jpa.persistence;

import com.emazon.stock.infrastructure.adapters.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
