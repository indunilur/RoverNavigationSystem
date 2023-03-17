/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.api;

import com.cognizant.rover.navigation.exception.InvalidRoverException;
import com.cognizant.rover.navigation.model.CommandType;
import com.cognizant.rover.navigation.model.ControlSignal;
import com.cognizant.rover.navigation.model.Coordinate;
import com.cognizant.rover.navigation.model.Direction;
import com.cognizant.rover.navigation.model.Position;
import com.cognizant.rover.navigation.model.Rover;
import com.cognizant.rover.navigation.model.RoverCommand;
import com.cognizant.rover.navigation.model.response.RoverResponse;
import com.cognizant.rover.navigation.service.CommandProcessingService;
import com.cognizant.rover.navigation.service.NavigationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for handling rover navigation
 */
@RestController
@RequestMapping("/api")
public class RoverNavigationRestController {

    private NavigationService navigationService;
    private CommandProcessingService commandProcessingService;

    public RoverNavigationRestController(@Qualifier("navigationServiceImpl") NavigationService navigationService,
                                         @Qualifier("commandProcessingService") CommandProcessingService commandProcessingService) {
        this.navigationService = navigationService;
        this.commandProcessingService = commandProcessingService;
    }

    @PostMapping("/grid")
    public ResponseEntity addGrid(@RequestBody RoverCommand roverCommand) {

        List<String> commands = commandProcessingService.handleCommand(CommandType.SIZE, roverCommand.getCommand());
        navigationService.addGrid(Integer.parseInt(commands.get(0)), Integer.parseInt(commands.get(1)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/rover")
    public ResponseEntity addRover(@RequestBody RoverCommand roverCommand) {

        List<String> commands = commandProcessingService.handleCommand(CommandType.POSITION, roverCommand.getCommand());
        Coordinate coordinate = new Coordinate(Integer.parseInt(commands.get(0)), Integer.parseInt(commands.get(1)));
        Rover rover = navigationService.addRover(new Position(coordinate, Direction.valueOf(commands.get(2))));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(new RoverResponse(rover)));
    }

    @PutMapping("/rover")
    public ResponseEntity navigateRover(@RequestBody RoverCommand roverCommand) {

        List<String> commands = commandProcessingService.handleCommand(CommandType.NAVIGATE, roverCommand.getCommand());
        commands.stream().forEach(x -> executeNavigation(roverCommand.getRoverId(), x));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rover/{roverId}")
    public ResponseEntity getRoverPosition(@PathVariable String roverId) {

        Rover rover = navigationService.getRover(roverId);
        if (ObjectUtils.isEmpty(rover)) {
            throw new InvalidRoverException(String.format("Rover with ID : %s not found", roverId));
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(new RoverResponse(rover)));
    }

    /**
     * Execute navigation of the rover
     *
     * @param roverId           rover ID
     * @param controlSignalName control signal name
     */
    private void executeNavigation(String roverId, String controlSignalName) {

        switch (ControlSignal.valueOf(controlSignalName)) {
            case M:
                navigationService.move(roverId);
                break;
            case L:
                navigationService.rotateLeft(roverId);
                break;
            case R:
                navigationService.rotateRight(roverId);
                break;
        }
    }
}
