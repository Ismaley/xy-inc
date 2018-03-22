package com.inc.xy.locator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public static class ErrorMessages {
        public static final String DUPLICATED_NAME = "duplicated name";
        public static final String DUPLICATED_COORDINATES = "duplicated coordinates";
        public static final String INVALID_NAME = "name cannot be null or empty";
        public static final String INVALID_COORDINATES = "coordinates must not be null and must be a positive number";
        public static final String INVALID_SEARCH_PARAM = "search param must have positive coordinates and radius greater than 0";
    }

}
