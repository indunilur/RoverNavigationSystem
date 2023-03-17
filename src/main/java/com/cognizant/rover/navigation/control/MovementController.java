/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.control;

import com.cognizant.rover.navigation.model.Coordinate;
import com.cognizant.rover.navigation.model.Position;
import org.springframework.stereotype.Component;

/**
 * Control the movement of a rover
 */
@Component
public class MovementController {

    /**
     * Move the rover from current position to the new position
     *
     * @param currentPosition current position of rover
     * @param noOfGridPoints  Number of grid points to be moved
     * @return new position
     */
    public Position move(Position currentPosition, int noOfGridPoints) {

        switch (currentPosition.getDirection()) {
            case E:
                return new Position(new Coordinate(currentPosition.getCoordinate().getX() + noOfGridPoints,
                        currentPosition.getCoordinate().getY()), currentPosition.getDirection());
            case W:
                return new Position(new Coordinate(currentPosition.getCoordinate().getX() - noOfGridPoints,
                        currentPosition.getCoordinate().getY()), currentPosition.getDirection());
            case N:
                return new Position(new Coordinate(currentPosition.getCoordinate().getX(),
                        currentPosition.getCoordinate().getY() + noOfGridPoints), currentPosition.getDirection());
            case S:
                return new Position(new Coordinate(currentPosition.getCoordinate().getX(),
                        currentPosition.getCoordinate().getY() - noOfGridPoints), currentPosition.getDirection());
            default:
                return currentPosition;
        }
    }
}
