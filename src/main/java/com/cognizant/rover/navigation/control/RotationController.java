/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.control;

import com.cognizant.rover.navigation.model.Direction;
import com.cognizant.rover.navigation.model.Position;
import org.springframework.stereotype.Component;

/**
 * Control the rotation of a rover
 */
@Component
public class RotationController {

    /**
     * Spin the rover 90 degrees left
     *
     * @param currentPosition current position of rover
     * @return new position
     */
    public Position rotateLeft(Position currentPosition) {

        Direction newDirection = null;
        switch (currentPosition.getDirection()) {
            case E:
                newDirection = Direction.N;
                break;
            case W:
                newDirection = Direction.S;
                break;
            case N:
                newDirection = Direction.W;
                break;
            case S:
                newDirection = Direction.E;
                break;
        }
        return new Position(currentPosition.getCoordinate(), newDirection);
    }

    /**
     * Spin the rover 90 degrees right
     *
     * @param currentPosition current position of rover
     * @return new position
     */
    public Position rotateRight(Position currentPosition) {

        Direction newDirection = null;
        switch (currentPosition.getDirection()) {
            case E:
                newDirection = Direction.S;
                break;
            case W:
                newDirection = Direction.N;
                break;
            case N:
                newDirection = Direction.E;
                break;
            case S:
                newDirection = Direction.W;
                break;
        }
        return new Position(currentPosition.getCoordinate(), newDirection);
    }
}
