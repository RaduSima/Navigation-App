package com.company;

/**
 * Represents a Motorcycle
 */
public class Motorcycle extends Moped {

    /**
     * No parameter constructor.
     * Sets the cost to the default one of a motorcycle.
     */
    public Motorcycle() {
        cost = 2;
    }

    /**
     * Overriding method.
     * Computes the cost of a motorcycle driving on a certain street.
     *
     * @param currentStreet A street that the motorcycle will drive on.
     * @return The cost of a motorcycle driving on a certain street.
     */
    @Override
    public int computeCost(Street currentStreet) {
        return cost * currentStreet.getCost() +
                currentStreet.getRestrictionsExtraCost();
    }
}
