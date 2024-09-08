package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.ProductServicePort;
import com.emazon.stock.domain.exceptions.DuplicatedProductCategoryException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.NotEnoughCategoriesException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.spi.ProductPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.pagination.DomainPage;
import com.emazon.stock.domain.utils.pagination.PaginationData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.emazon.stock.domain.utils.ValidationUtils.*;

public class ProductUseCase implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final BrandPersistencePort brandPersistencePort;

    public ProductUseCase(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort, BrandPersistencePort brandPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void save(Product product) {
        validateName(product.getName(), DomainConstants.NAME_LENGTH_LIMIT);
        validateDescription(product.getDescription(), DomainConstants.BRAND_DESCRIPTION_LENGTH_LIMIT);
        validatePrice(product.getPrice());
        validateQuantity(product.getQuantity());
        if(product.getBrand() == null) throw new EmptyFieldException(DomainConstants.Field.BRAND.toString());
        brandPersistencePort.getBrand(product.getBrand().getId());

        if (product.getCategories() == null || product.getCategories().isEmpty())
            throw new NotEnoughCategoriesException(DomainConstants.PRODUCT_CATEGORY_INSUFFICIENT_MESSAGE);

        HashSet<Long> categoryIds = new HashSet<>();
        AtomicInteger prevSize = new AtomicInteger();
        product.getCategories().forEach(category -> {
            categoryPersistencePort.getCategory(category.getId());
            categoryIds.add(category.getId());
            if (categoryIds.size() == prevSize.get())
                throw new DuplicatedProductCategoryException(DomainConstants.PRODUCT_CATEGORY_DUPLICATED_MESSAGE);
            prevSize.set(categoryIds.size());
        });

        if (categoryIds.size() > DomainConstants.FIELD_CATEGORIES_LIMIT)
            throw new OutOfBoundsException(DomainConstants.CATEGORIES_LIMIT_REACHED_MESSAGE);

        productPersistencePort.save(product);
    }

    @Override
    public DomainPage<Product> getAllProducts(PaginationData paginationData) {
        return productPersistencePort.getAllProducts(paginationData);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        return productPersistencePort.getProductCategories(id);
    }

    @Override
    public void addSupply(Long id, Product product){
        productPersistencePort.addSupply(id, product);
    }
}
