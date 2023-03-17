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
 * Test cases for testing the RotationController
 */
@RunWith(MockitoJUnitRunner.class)
public class RotationControllerTest {

    @InjectMocks
    RotationController rotationController;

    @Test
    public void testRotateLeft_East() {

        Position position = rotationController.rotateLeft(new Position(new Coordinate(5, 5), Direction.E));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.N, position.getDirection());
    }

    @Test
    public void testRotateLeft_West() {

        Position position = rotationController.rotateLeft(new Position(new Coordinate(5, 5), Direction.W));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.S, position.getDirection());
    }

    @Test
    public void testRotateLeft_North() {

        Position position = rotationController.rotateLeft(new Position(new Coordinate(5, 5), Direction.N));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.W, position.getDirection());
    }

    @Test
    public void testRotateLeft_South() {

        Position position = rotationController.rotateLeft(new Position(new Coordinate(5, 5), Direction.S));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.E, position.getDirection());
    }

    @Test
    public void testRotateRight_East() {

        Position position = rotationController.rotateRight(new Position(new Coordinate(5, 5), Direction.E));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.S, position.getDirection());
    }

    @Test
    public void testRotateRight_West() {

        Position position = rotationController.rotateRight(new Position(new Coordinate(5, 5), Direction.W));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.N, position.getDirection());
    }

    @Test
    public void testRotateRight_North() {

        Position position = rotationController.rotateRight(new Position(new Coordinate(5, 5), Direction.N));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.E, position.getDirection());
    }

    @Test
    public void testRotateRight_South() {

        Position position = rotationController.rotateRight(new Position(new Coordinate(5, 5), Direction.S));
        assertEquals(5, position.getCoordinate().getX());
        assertEquals(5, position.getCoordinate().getY());
        assertEquals(Direction.W, position.getDirection());
    }
}
