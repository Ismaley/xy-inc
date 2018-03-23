package com.inc.xy.locator.service.exceptions;

import java.time.LocalDateTime;
import java.util.Date;

public class ErrorDetails {

    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorDetails(String errorCode, String message, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }
}
