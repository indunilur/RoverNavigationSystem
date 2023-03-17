/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.command;

import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.model.CommandType;
import org.springframework.stereotype.Component;

/**
 * Factory with different command processors
 */
@Component
public class CommandProcessorFactory {

    private GridSizeProcessor gridSizeProcessor;
    private PositionProcessor positionProcessor;
    private NavigationProcessor navigationProcessor;

    public CommandProcessorFactory() {
        gridSizeProcessor = new GridSizeProcessor();
        positionProcessor = new PositionProcessor();
        navigationProcessor = new NavigationProcessor();
    }

    /**
     * Get the matching command processor from factory, for the given command type
     *
     * @param type command type
     * @return matching command processor
     */
    public CommandProcessor getCommandProcessor(CommandType type) {

        switch (type) {
            case SIZE:
                return gridSizeProcessor;
            case POSITION:
                return positionProcessor;
            case NAVIGATE:
                return navigationProcessor;
            default:
                throw new InvalidCommandException("Invalid command type.");
        }
    }
}
