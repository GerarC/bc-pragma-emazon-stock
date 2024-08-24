package com.emazon.stock.adapters.driven.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 127)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products;
}
