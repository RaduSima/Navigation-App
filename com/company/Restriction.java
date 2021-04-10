package com.company;

/**
 * Represents a street restriction.
 */
public class Restriction {

    /**
     * The type of restriction, represented as a string.
     */
    private String type;

    /**
     * The cost of the restriction, represented as an int.
     */
    private int additionalCost;

    /**
     * No parameter constructor.
     * Sets the properties to their default values.
     */
    public Restriction() {

    }


    /**
     * Two parameter constructor.
     * Sets the type and the cost.
     *
     * @param type           The type of restriction that will be set,
     *                       represented as
     *                       a string.
     * @param additionalCost The cost of the restriction that will be set,
     *                       represented as an int.
     */
    public Restriction(String type, int additionalCost) {
        this.type = type;
        this.additionalCost = additionalCost;
    }

    /**
     * Gets the restriction type.
     *
     * @return A restriction type, represented as a string.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the restriction type.
     *
     * @param type The restriction type to be set, represented as a string.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the restriction cost.
     *
     * @return A restriction cost, represented as an int.
     */
    public int getAdditionalCost() {
        return additionalCost;
    }

    /**
     * Sets the cost of a restriction.
     *
     * @param additionalCost The cost of the restriction to be set,
     *                       represented as an int.
     */
    public void setAdditionalCost(int additionalCost) {
        this.additionalCost = additionalCost;
    }
}
