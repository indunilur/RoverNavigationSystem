/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.command;

import com.cognizant.rover.navigation.exception.InvalidCommandException;

import java.util.Arrays;
import java.util.List;

import static com.cognizant.rover.navigation.util.NavigationConstants.SPACE_REGEX;

/**
 * Command processor for grid size
 */
public class GridSizeProcessor implements CommandProcessor {

    /**
     * Validate the type of command values
     *
     * @param command_values command values
     */
    private static void validateType(String[] command_values) {

        try {
            Arrays.stream(command_values).forEach(value -> Integer.parseInt(value));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid values as coordinates for grid size.");
        }
    }

    /**
     * Validate the length of command values
     *
     * @param command_values command values
     */
    private static void validateLength(String[] command_values) {

        if (command_values.length != 2) {
            throw new InvalidCommandException("Invalid number of coordinates for grid size.");
        }
    }

    @Override
    public List<String> process(String command) {

        return Arrays.asList(command.split(SPACE_REGEX));
    }

    @Override
    public void validate(String command) {

        String[] command_values = command.split(SPACE_REGEX);
        validateLength(command_values);
        validateType(command_values);
    }
}
