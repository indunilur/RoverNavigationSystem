/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model;

/**
 * Define and manage a regular grid
 */
public class RegularGrid implements Grid {

    int width;
    int height;

    public RegularGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isValidPosition(Position position) {

        return isIndexXValid(position.getCoordinate().getX()) && isIndexYValid(position.getCoordinate().getY());
    }

    /**
     * Check whether the x coordinate is valid
     *
     * @param xCoordinate
     * @return true if the x coordinate is valid
     */
    private boolean isIndexXValid(int xCoordinate) {

        return xCoordinate >= 0 && xCoordinate <= this.width;
    }

    /**
     * Check whether the y coordinate is valid
     *
     * @param yCoordinate
     * @return true if the y coordinate is valid
     */
    private boolean isIndexYValid(int yCoordinate) {

        return yCoordinate >= 0 && yCoordinate <= this.height;
    }
}
