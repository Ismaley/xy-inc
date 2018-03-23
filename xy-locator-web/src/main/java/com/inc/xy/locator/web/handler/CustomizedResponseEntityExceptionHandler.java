package com.inc.xy.locator.web.handler;

import com.google.gson.Gson;
import com.inc.xy.locator.service.exceptions.BusinessException;
import com.inc.xy.locator.service.exceptions.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Gson gson = new Gson();


    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<String> handleUserNotFoundException(BusinessException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getErrorCode(), ex.getMessage(), ((ServletWebRequest) request).getRequest().getPathInfo(), LocalDateTime.now().toString());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.BAD_REQUEST);
    }
}
