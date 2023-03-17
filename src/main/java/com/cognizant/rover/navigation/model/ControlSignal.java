/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.model;

/**
 * Define the types of control signals
 */
public enum ControlSignal {

    L("LEFT"),
    R("RIGHT"),
    M("MOVE");

    ControlSignal(String signalName) {
    }
}
