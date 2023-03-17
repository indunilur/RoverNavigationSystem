/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model;

/**
 * Define the different directions on the grid that rover can rotate
 */
public enum Direction {

    N("NORTH"),
    S("SOUTH"),
    E("EAST"),
    W("WEST");

    Direction(String directionName) {
    }
}
