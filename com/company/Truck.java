package com.company;

/**
 * Represents a truck.
 */
public class Truck extends Vehicle {

    /**
     * No parameter constructor
     * Sets the type, weight and cost to the default ones of a truck.
     */
    public Truck() {
        type = "Autoutilitar";
        weight = 3;
        cost = 6;
    }

    /**
     * Overriding method.
     * Computes the cost of a truck driving on a certain street.
     *
     * @param currentStreet A street that the truck will drive on.
     * @return The cost of a truck driving on a certain street.
     */
    @Override
    public int computeCost(Street currentStreet) {
        return cost * currentStreet.getCost() +
                currentStreet.getRestrictionsExtraCost();
    }
}
