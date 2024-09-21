package com.emazon.stock.adapters.driven.jpa.adapter;

import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.adapters.driven.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.adapters.driven.jpa.mapper.PaginationJPAMapper;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandPersistencePort {

    private final BrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public void save(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Brand getBrand(Long id) {
        return brandEntityMapper.toBrand(brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format(DomainConstants.BRAND_NOT_FOUND_ID, id)
        )));
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandEntityMapper.toBrand(brandRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(
                String.format(DomainConstants.BRAND_NOT_FOUND_NAME, name)
        )));
    }

    @Override
    public DomainPage<Brand> getAllBrands(PaginationData paginationData) {
        Pageable pageable = paginationJPAMapper.toJPA(paginationData).createPageable();
        Page<BrandEntity> returnBrands = brandRepository.findAll(pageable);
        return brandEntityMapper.toDomainPage(returnBrands);
    }
}
