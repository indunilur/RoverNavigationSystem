/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.service;

import com.cognizant.rover.navigation.command.CommandProcessorFactory;
import com.cognizant.rover.navigation.command.GridSizeProcessor;
import com.cognizant.rover.navigation.command.NavigationProcessor;
import com.cognizant.rover.navigation.command.PositionProcessor;
import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.model.CommandType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test cases for testing the CommandProcessingService
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandProcessingServiceTest {

    @InjectMocks
    private CommandProcessingService commandProcessingService;

    @Mock
    private CommandProcessorFactory commandProcessorFactory;

    @Before
    public void init() {
        when(commandProcessorFactory.getCommandProcessor(CommandType.SIZE)).thenReturn(new GridSizeProcessor());
        when(commandProcessorFactory.getCommandProcessor(CommandType.POSITION)).thenReturn(new PositionProcessor());
        when(commandProcessorFactory.getCommandProcessor(CommandType.NAVIGATE)).thenReturn(new NavigationProcessor());
    }

    @Test
    public void testHandleCommand_size() {

        List<String> commands = commandProcessingService.handleCommand(CommandType.SIZE, "3 4");
        assertEquals(2, commands.size());
        assertEquals("3", commands.get(0));
        assertEquals("4", commands.get(1));
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_size_invalidLength() {

        commandProcessingService.handleCommand(CommandType.SIZE, "3 4 5");
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_size_invalidValues() {

        commandProcessingService.handleCommand(CommandType.SIZE, "3NM 5%@");
    }

    @Test
    public void testHandleCommand_position() {

        List<String> commands = commandProcessingService.handleCommand(CommandType.POSITION, "3 5 N");
        assertEquals(3, commands.size());
        assertEquals("3", commands.get(0));
        assertEquals("5", commands.get(1));
        assertEquals("N", commands.get(2));
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_position_invalidLength() {

        commandProcessingService.handleCommand(CommandType.POSITION, "3 4 5 5");
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_position_nonNumericCoordinates() {

        commandProcessingService.handleCommand(CommandType.POSITION, "3NM 4r N");
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_position_invalidDirection() {

        commandProcessingService.handleCommand(CommandType.POSITION, "2 3 SW");
    }

    @Test
    public void testHandleCommand_navigation() {

        List<String> commands = commandProcessingService.handleCommand(CommandType.NAVIGATE, "MMRL");
        assertEquals(4, commands.size());
        assertEquals("M", commands.get(0));
        assertEquals("M", commands.get(1));
        assertEquals("R", commands.get(2));
        assertEquals("L", commands.get(3));
    }

    @Test(expected = InvalidCommandException.class)
    public void testHandleCommand_navigation_invalidControlSignals() {

        commandProcessingService.handleCommand(CommandType.NAVIGATE, "MMFL");
    }
}
