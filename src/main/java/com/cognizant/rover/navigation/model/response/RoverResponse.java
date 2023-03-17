/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model.response;

import com.cognizant.rover.navigation.model.Rover;

import static com.cognizant.rover.navigation.util.NavigationConstants.SPACE_REGEX;

/**
 * Success response with rover details
 */
public class RoverResponse {

    private String roverID;
    private String position;

    public RoverResponse(Rover rover) {

        this.roverID = rover.getRoverID();
        this.position = new StringBuilder().append(rover.getPosition().getCoordinate().getX())
                .append(" ").append(rover.getPosition().getCoordinate().getY()).append(" ")
                .append(rover.getPosition().getDirection().name()).toString();
    }

    public String getRoverID() {
        return roverID;
    }

    public String getPosition() {
        return position;
    }
}
