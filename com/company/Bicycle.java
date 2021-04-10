package com.company;

/**
 * Represents a bicycle.
 */
public class Bicycle extends Moped {

    /**
     * No parameter constructor.
     * Sets the cost to the default one of a bicycle.
     */
    public Bicycle() {
        cost = 1;
    }

    /**
     * Overriding method.
     * Computes the cost of a bicycle driving on a certain street.
     *
     * @param currentStreet A street that the bicycle will drive on.
     * @return The cost of a bicycle driving on a certain street.
     */
    @Override
    public int computeCost(Street currentStreet) {
        return cost * currentStreet.getCost() +
                currentStreet.getRestrictionsExtraCost();
    }
}
