package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.pagination.PaginationData;

import static com.emazon.stock.domain.utils.ValidationUtils.validateDescription;
import static com.emazon.stock.domain.utils.ValidationUtils.validateName;

public class BrandUseCase implements BrandServicePort {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void save(Brand brand) {
        validateName(brand.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(brand.getDescription(), DomainConstants.BRAND_DESCRIPTION_LENGTH_LIMIT);

        try {
            brandPersistencePort.getBrandByName(brand.getName());
            throw new EntityAlreadyExistsException(Brand.class.getSimpleName(), brand.getName());
        } catch (EntityNotFoundException e) {
            brandPersistencePort.save(brand);
        }
    }

    @Override
    public DomainPage<Brand> getAllBrands(PaginationData paginationData) {
        return brandPersistencePort.getAllBrands(paginationData);
    }
}
