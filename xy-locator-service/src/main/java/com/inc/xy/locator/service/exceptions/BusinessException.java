package com.inc.xy.locator.service.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    @Getter
    private String errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode.code;
    }

    public static class ErrorCode {

        public final String code;
        public final String message;

        public static final ErrorCode DUPLICATED_NAME = new ErrorCode("DUPLICATED_NAME", "this point name has already been taken");
        public static final ErrorCode INVALID_NAME = new ErrorCode("INVALID_NAME", "name cannot be null or empty");
        public static final ErrorCode INVALID_COORDINATES = new ErrorCode("INVALID_COORDINATES", "coordinates must not be null and must be a positive number");
        public static final ErrorCode INVALID_SEARCH_PARAM = new ErrorCode("INVALID_SEARCH_PARAM", "search param must have positive coordinates and radius greater than 0");

        private ErrorCode(final String code, final String message) {
            this.code = code;
            this.message = message;
        }
    }

}
