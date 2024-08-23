package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.adapters.driven.jpa.utils.PageUtil;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandPersistencePort {

    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void save(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toBrand(brandRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Brand with name " + name + " not found")));
    }

    @Override
    public DomainPage<Brand> getAllBrands(int page, String col, boolean asc) {
        Pageable pageable = PageUtil.createPageable(page, col, asc);
        Page<BrandEntity> returnBrands = brandRepository.findAll(pageable);
        return brandEntityMapper.toDomainPage(returnBrands);
    }
}
