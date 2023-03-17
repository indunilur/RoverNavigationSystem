/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.command;

import java.util.List;

/**
 * Define processor for different commands
 */
public interface CommandProcessor {

    /**
     * Process the given command
     *
     * @param command command
     * @return list of separated elements in the command
     */
    List<String> process(String command);

    /**
     * Validate the given command
     *
     * @param command command
     */
    void validate(String command);
}
