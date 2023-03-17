/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.service;

import com.cognizant.rover.navigation.command.CommandProcessor;
import com.cognizant.rover.navigation.command.CommandProcessorFactory;
import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.model.CommandType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Service for initial processing of commands
 */
@Service
public class CommandProcessingService {

    private CommandProcessorFactory commandProcessFactory;

    public CommandProcessingService(CommandProcessorFactory commandProcessFactory) {
        this.commandProcessFactory = commandProcessFactory;
    }

    /**
     * Initial processing of different type of commands
     *
     * @param type    command type
     * @param command input command
     * @return list of separated elements in the command
     */
    public List<String> handleCommand(CommandType type, String command) {

        if (StringUtils.isEmpty(command)) {
            throw new InvalidCommandException("No command has specified.");
        }

        CommandProcessor commandProcessor = commandProcessFactory.getCommandProcessor(type);
        commandProcessor.validate(command);
        return commandProcessor.process(command);
    }
}
