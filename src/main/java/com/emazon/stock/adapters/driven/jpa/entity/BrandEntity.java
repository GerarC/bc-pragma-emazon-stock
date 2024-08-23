package com.emazon.stock.adapters.driven.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "brand")
public class BrandEntity {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 127)
    private String description;
}
