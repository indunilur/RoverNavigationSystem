/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.exception;

import com.cognizant.rover.navigation.model.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NavigationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleInvalidCommand(InvalidCommandException ex, WebRequest request) {

        ErrorResponse apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleInvalidRover(InvalidRoverException ex, WebRequest request) {

        ErrorResponse apiError = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }
}
