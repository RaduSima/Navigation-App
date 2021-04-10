package com.company;

/**
 * Represents a vehicle of the moped type.
 */
abstract class Moped extends Vehicle {

    /**
     * No parameter constructor.
     * Sets the type and weight to the default ones of a moped.
     */
    protected Moped() {
        type = "Moped";
        weight = 1;
    }
}
