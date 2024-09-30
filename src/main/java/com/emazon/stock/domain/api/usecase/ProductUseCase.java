package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.ProductServicePort;
import com.emazon.stock.domain.exceptions.*;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.BrandPersistencePort;
import com.emazon.stock.domain.spi.CategoryPersistencePort;
import com.emazon.stock.domain.spi.ProductPersistencePort;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.domain.utils.filter.ProductFilter;
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
        if (product.getBrand() == null) throw new EmptyFieldException(DomainConstants.Field.BRAND.toString());
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
    public DomainPage<Product> getAllProducts(ProductFilter filter, PaginationData paginationData) {
        return productPersistencePort.getAllProducts(filter, paginationData);
    }

    @Override
    public List<Category> getProductCategories(Long id) {
        return productPersistencePort.getProductCategories(id);
    }

    @Override
    public void addSupply(Long id, Product product) {
        Product originalProduct = productPersistencePort.getProduct(id);
        originalProduct.setQuantity(product.getQuantity() + originalProduct.getQuantity());
        productPersistencePort.save(originalProduct);
    }

    @Override
    public void removeSupply(Long id, Product product) {
        Product originalProduct = productPersistencePort.getProduct(id);
        long newQuantity = originalProduct.getQuantity() - product.getQuantity();
        if (newQuantity < 0) throw new NotEnoughStockException(originalProduct.getName());
        originalProduct.setQuantity(newQuantity);
        productPersistencePort.save(originalProduct);
    }

    @Override
    public Product getProduct(Long id) {
        return productPersistencePort.getProduct(id);
    }

    @Override
    public List<Product> getProducts(List<Long> ids) {
        return ids.stream().map(productPersistencePort::getProduct).toList();
    }
}
