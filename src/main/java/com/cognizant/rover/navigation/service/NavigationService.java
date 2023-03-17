/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.service;

import com.cognizant.rover.navigation.model.Position;
import com.cognizant.rover.navigation.model.Rover;

/**
 * Define the handling of rover navigation
 */
public interface NavigationService {

    /**
     * Set a grid with specified width and height
     *
     * @param width  width of the grid
     * @param height height of the grid
     */
    void addGrid(int width, int height);

    /**
     * Add rover into the grid at the given position
     *
     * @param position position to put the rover
     * @return rover ID
     */
    Rover addRover(Position position);

    /**
     * Move rover with the specified ID, forward one grid point and maintain the same heading
     *
     * @param roverID rover ID
     */
    void move(String roverID);

    /**
     * Spin the rover with the specified ID, 90 degrees left
     *
     * @param roverID rover ID
     */
    void rotateLeft(String roverID);

    /**
     * Spin the rover with the specified ID, 90 degrees right
     *
     * @param roverID rover ID
     */
    void rotateRight(String roverID);

    /**
     * Retrieve the rover with the specified ID
     *
     * @param roverID rover ID
     * @return
     */
    Rover getRover(String roverID);
}
