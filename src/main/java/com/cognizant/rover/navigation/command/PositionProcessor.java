/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.command;

import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.model.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.cognizant.rover.navigation.util.NavigationConstants.SPACE_REGEX;

/**
 * Command processor for positioning
 */
public class PositionProcessor implements CommandProcessor {

    /**
     * Validate the type of direction in command values
     *
     * @param command_values command values
     */
    private static void validateDirectionType(String[] command_values) {

        if (!Arrays.stream(Direction.values()).anyMatch(direction -> direction.name().equals(command_values[2]))) {
            throw new InvalidCommandException("Invalid values as direction for rover positioning.");
        }
    }

    /**
     * Validate the type of coordinates in command values
     *
     * @param command_values command values
     */
    private static void validateCoordinateType(String[] command_values) {

        try {
            IntStream.range(0, command_values.length - 1).forEach(index -> Integer.parseInt(command_values[index]));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid values as coordinates for rover positioning.");
        }
    }

    /**
     * Validate the length of command values
     *
     * @param command_values command values
     */
    private static String[] validateLength(String[] command_values) {

        if (command_values.length != 3) {
            throw new InvalidCommandException("Invalid number of command values for rover positioning.");
        }
        return command_values;
    }

    @Override
    public List<String> process(String command) {

        return Arrays.asList(command.split(SPACE_REGEX));
    }

    @Override
    public void validate(String command) {

        String[] command_values = command.split(SPACE_REGEX);
        validateLength(command_values);
        validateCoordinateType(command_values);
        validateDirectionType(command_values);
    }
}
