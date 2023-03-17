/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.service;

import com.cognizant.rover.navigation.control.MovementController;
import com.cognizant.rover.navigation.control.RotationController;
import com.cognizant.rover.navigation.exception.InvalidCommandException;
import com.cognizant.rover.navigation.exception.InvalidRoverException;
import com.cognizant.rover.navigation.model.Coordinate;
import com.cognizant.rover.navigation.model.Direction;
import com.cognizant.rover.navigation.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test cases for testing the NavigationService
 */
@RunWith(MockitoJUnitRunner.class)
public class NavigationServiceTest {

    @InjectMocks
    NavigationServiceImpl navigationService;

    @Mock
    MovementController movementController;

    @Mock
    RotationController rotationController;

    @Before
    public void init() {
        navigationService = new NavigationServiceImpl(movementController, rotationController);
        navigationService.addGrid(5, 6);
    }

    @Test(expected = InvalidCommandException.class)
    public void testAddRover_invalidPosition() {

        navigationService.addRover(new Position(new Coordinate(10, 10), Direction.N));
    }

    @Test(expected = InvalidRoverException.class)
    public void testMove_invalidRoverID() {

        navigationService.move("1");
    }

    @Test(expected = InvalidRoverException.class)
    public void testRotateLeft_invalidRoverID() {

        navigationService.rotateLeft("1");
    }

    @Test(expected = InvalidRoverException.class)
    public void testRotateRight_invalidRoverID() {

        navigationService.rotateRight("1");
    }
}
