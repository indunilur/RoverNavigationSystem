/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.service;

import com.cognizant.rover.navigation.control.MovementController;
import com.cognizant.rover.navigation.control.RotationController;
import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.exception.InvalidRoverException;
import com.cognizant.rover.navigation.model.Grid;
import com.cognizant.rover.navigation.model.Position;
import com.cognizant.rover.navigation.model.RegularGrid;
import com.cognizant.rover.navigation.model.Rover;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manage the handling of rover navigation
 */
@Service
public class NavigationServiceImpl implements NavigationService {

    private MovementController movementController;
    private RotationController rotationController;
    private Grid grid;
    private Map<String, Rover> rovers = new HashMap<>();

    public NavigationServiceImpl(MovementController movementController, RotationController rotationController) {
        this.movementController = movementController;
        this.rotationController = rotationController;
    }

    @Override
    public void addGrid(int width, int height) {

        grid = new RegularGrid(width, height);
    }

    @Override
    public Rover addRover(Position position) {

        if (!grid.isValidPosition(position)) {
            throw new InvalidCommandException("Invalid position in the grid.");
        }

        String roverId = UUID.randomUUID().toString();
        Rover rover = new Rover(roverId, position);
        rovers.put(roverId, rover);
        return rover;
    }

    @Override
    public void move(String roverID) {

        if (ObjectUtils.isEmpty(rovers.get(roverID))) {
            throw new InvalidRoverException(String.format("No existing rover with ID : %s", roverID));
        }

        Position position = movementController.move(rovers.get(roverID).getPosition(), 1);
        if (!grid.isValidPosition(position)) {
            throw new InvalidCommandException("Invalid position with the movement in grid.");
        }
        rovers.put(roverID, new Rover(roverID, position));
    }

    @Override
    public void rotateLeft(String roverID) {

        if (ObjectUtils.isEmpty(rovers.get(roverID))) {
            throw new InvalidRoverException(String.format("No existing rover with ID : %s", roverID));
        }

        Position position = rotationController.rotateLeft(rovers.get(roverID).getPosition());
        rovers.put(roverID, new Rover(roverID, position));
    }

    @Override
    public void rotateRight(String roverID) {

        if (ObjectUtils.isEmpty(rovers.get(roverID))) {
            throw new InvalidRoverException(String.format("No existing rover with ID : %s", roverID));
        }

        Position position = rotationController.rotateRight(rovers.get(roverID).getPosition());
        rovers.put(roverID, new Rover(roverID, position));
    }

    @Override
    public Rover getRover(String roverID) {

        return rovers.get(roverID);
    }
}
