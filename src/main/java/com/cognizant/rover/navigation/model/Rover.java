/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model;

/**
 * Define a Rover
 */
public class Rover {

    private String roverID;
    private Position position;

    public Rover(String roverID, Position position) {
        this.roverID = roverID;
        this.position = position;
    }

    public String getRoverID() {
        return roverID;
    }

    public void setRoverID(String roverID) {
        this.roverID = roverID;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
