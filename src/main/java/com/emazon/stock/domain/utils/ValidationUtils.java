package com.emazon.stock.domain.utils;

import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;

public class ValidationUtils {
    private ValidationUtils() {
    }

    public static void validateName(String name, int limit) throws EmptyFieldException, OutOfBoundsException {
        if (name.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if (name.trim().length() > limit)
            throw new OutOfBoundsException(DomainConstants.Field.NAME.toString() + " " + limit + " " + DomainConstants.CHARS_LIMIT_REACHED_MESSAGE);
    }

    public static void validateDescription(String description, int limit) throws EmptyFieldException, OutOfBoundsException {
        if(description.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(description.trim().length() > limit)
            throw new OutOfBoundsException(DomainConstants.Field.DESCRIPTION.toString() + " " + limit + " " + DomainConstants.CHARS_LIMIT_REACHED_MESSAGE);

    }
}
