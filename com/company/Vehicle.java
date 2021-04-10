package com.company;

/**
 * Represents a general vehicle.
 * Class that all the vehicle type classes extend.
 */
public abstract class Vehicle {

    /**
     * The vehicle type, represented as a string
     */
    protected String type;

    /**
     * The weight of the vehicle, represented as an int.
     */
    protected int weight;

    /**
     * The cost of the vehicle on a street, represented as an int.
     */
    protected int cost;

    /**
     * No parameter constructor.
     */
    protected Vehicle() {

    }

    /**
     * Gets the type of vehicle.
     *
     * @return A type of vehicle, of type string.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of a vehicle.
     *
     * @param type A string representing the type of vehicle that will be set.
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Gets the weight of a vehicle.
     *
     * @return An int representing the weight of a vehicle.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight of a vehicle.
     *
     * @param weight An int representing the weight that will be set.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the cost of a vehicle on a street.
     *
     * @return An int representing the cost of a vehicle on a street.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of a vehicle on a street.
     *
     * @param cost An int representing the cost of a vehicle on a street that
     *             will be set.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Abstract method, is overridden in the type of vehicles classes.
     * Computes the cost of a vehicle on a certain street, taking in
     * consideration the extra cost of the street, added by restrictions.
     *
     * @param currentStreet A street that the vehicle will drive on.
     * @return The cost of the vehicle driving on a certain street.
     */
    public abstract int computeCost(Street currentStreet);
}
