/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.exception;

/**
 * Exception for handling invalid rover
 */
public class InvalidRoverException extends RuntimeException {

    private static final long serialVersionUID = 771882295356278119L;

    public InvalidRoverException(String message) {
        super(message);
    }

    public InvalidRoverException(String message, Throwable cause) {
        super(message, cause);
    }
}
