/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.control;

import com.cognizant.rover.navigation.model.Coordinate;
import com.cognizant.rover.navigation.model.Direction;
import com.cognizant.rover.navigation.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for testing the MovementController
 */
@RunWith(MockitoJUnitRunner.class)
public class MovementControllerTest {

    @InjectMocks
    MovementController movementController;

    @Test
    public void testMove_East() {

        Position position = movementController.move(new Position(new Coordinate(5, 5), Direction.E), 1);
        assertEquals(6, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.E, position.getDirection());
    }

    @Test
    public void testMove_West() {

        Position position = movementController.move(new Position(new Coordinate(5, 5), Direction.W), 1);
        assertEquals(4, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.W, position.getDirection());
    }

    @Test
    public void testMove_North() {

        Position position = movementController.move(new Position(new Coordinate(5, 5), Direction.N), 1);
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(6, position.getCoordinate().getY());
        assertEquals(Direction.N, position.getDirection());
    }

    @Test
    public void testMove_South() {

        Position position = movementController.move(new Position(new Coordinate(5, 5), Direction.S), 1);
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(4, position.getCoordinate().getY());
        assertEquals(Direction.S, position.getDirection());
    }
}
