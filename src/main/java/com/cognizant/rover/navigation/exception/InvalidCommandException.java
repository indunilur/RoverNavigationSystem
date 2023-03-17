/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.exception;

/**
 * Exception for handling invalid commands
 */
public class InvalidCommandException extends RuntimeException {

    private static final long serialVersionUID = 771882295356278119L;

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
