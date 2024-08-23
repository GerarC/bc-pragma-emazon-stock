package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.BrandServicePort;
import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainPage;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;

public class BrandUseCase implements BrandServicePort {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void save(Brand brand) {
        if(brand.getName().trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if(brand.getDescription().trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(brand.getName().trim().length() > DomainConstants.NAME_LENGTH_LIMIT)
            throw new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}));
        if(brand.getDescription().trim().length() > DomainConstants.NAME_LENGTH_LIMIT)
            throw new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.BRAND_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}));

        try {
            brandPersistencePort.getBrandByName(brand.getName());
            throw new EntityAlreadyExistsException(Brand.class.getSimpleName(), brand.getName());
        } catch (EntityNotFoundException e) {
            brandPersistencePort.save(brand);
        }
    }

    @Override
    public DomainPage<Brand> getAllBrands(int page, String col, boolean asc) {
        return brandPersistencePort.getAllBrands(page, col, asc);
    }
}
