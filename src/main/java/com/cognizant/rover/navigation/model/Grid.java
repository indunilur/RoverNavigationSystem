/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model;

/**
 * Define a Grid
 */
public interface Grid {

    /**
     * Check whether a given position is valid as per the grid size
     *
     * @param position position to check
     * @return true, if valid position
     */
    boolean isValidPosition(Position position);
}
