package com.emazon.stock.domain.exceptions;

import com.emazon.stock.domain.utils.DomainConstants;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String product) {
        super(String.format(DomainConstants.NOT_ENOUGH_STOCK_QUANTITY_MESSAGE, product));
    }
}
