package com.company;

/**
 * Represents a car.
 */
public class Car extends Vehicle {

    /**
     * No parameter constructor
     * Sets the type, weight and cost to the default ones of a car.
     */
    public Car() {
        type = "Autovehicul";
        weight = 2;
        cost = 4;
    }

    /**
     * Overriding method.
     * Computes the cost of a car driving on a certain street.
     *
     * @param currentStreet A street that the car will drive on.
     * @return The cost of a car driving on a certain street.
     */
    @Override
    public int computeCost(Street currentStreet) {
        return cost * currentStreet.getCost() +
                currentStreet.getRestrictionsExtraCost();
    }
}
