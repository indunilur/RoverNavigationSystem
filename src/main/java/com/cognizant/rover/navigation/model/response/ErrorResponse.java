/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model.response;

import org.springframework.http.HttpStatus;

/**
 * Custom error response
 */
public class ErrorResponse {

    private HttpStatus status;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {

        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
