package com.inc.xy.locator.service.exceptions;

public class ErrorDetails {

    private String errorCode;
    private String message;
    private String pathInfo;
    private String timestamp;

    public ErrorDetails(String errorCode, String message, String pathInfo, String timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.pathInfo = pathInfo;
        this.timestamp = timestamp;
    }
}
