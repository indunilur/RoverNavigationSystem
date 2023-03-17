/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.command;

import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.model.ControlSignal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command processor for navigation
 */
public class NavigationProcessor implements CommandProcessor {

    private static final List<String> controlSignals = Arrays.stream(ControlSignal.values())
            .map(y -> y.name()).collect(Collectors.toList());

    @Override
    public List<String> process(String command) {

        return command.chars().mapToObj(e -> (char) e).map(e -> e.toString()).collect(Collectors.toList());
    }

    @Override
    public void validate(String command) {

        if (!command.chars().mapToObj(value -> (char) value).collect(Collectors.toList())
                .stream().allMatch(value -> controlSignals.contains(value.toString()))) {
            throw new InvalidCommandException("Invalid values as control signals for rover navigation.");
        }
    }
}
