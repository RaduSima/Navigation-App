package com.company;

/**
 * Reprezents a street.
 */
public class Street {
    /**
     * The cost of a street, not counting the restrictions, represented my an
     * int.
     */
    private int cost;

    /**
     * The weight limit of a street, represented by an int.
     */
    private int weightLimit;

    /**
     * The extra cost added to the street by the restrictions, represented as
     * an int.
     */
    private int restrictionsExtraCost;

    /**
     * No parameter constructor.
     * Sets the properties to their default values.
     */
    public Street() {

    }

    /**
     * Two parameter constructor.
     * Sets the cost and weight limit.
     * @param cost
     * @param weightLimit
     */
    public Street(int cost, int weightLimit) {
        this.cost = cost;
        this.weightLimit = weightLimit;
    }

    /**
     * Gets the cost.
     * @return The cost of the street, represented by an int.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost.
     * @param cost The cost to be set, represented by an int.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Gets the weight limit.
     * @return The weight limit, represented by an int.
     */
    public int getWeightLimit() {
        return weightLimit;
    }

    /**
     * Sets the weight limit.
     * @param weightLimit  The weight limit to be set, represented by an int.
     */
    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    /**
     * Gets the extra cost, added by the restrictions.
     * @return The extra cost, represented by an int.
     */
    public int getRestrictionsExtraCost() {
        return restrictionsExtraCost;
    }

    /**
     * Sets the extra cost, added by the restrictions.
     * @param restrictionsExtraCost  The extra cost to be set, represented by
     *                              an int.
     */
    public void setRestrictionsExtraCost(int restrictionsExtraCost) {
        this.restrictionsExtraCost = restrictionsExtraCost;
    }

    /**
     * Adds extra cost to the already existing extra cost.
     * @param res The extra cost to be added.
     */
    public void increaseExtraCost(Restriction res) {
        restrictionsExtraCost += res.getAdditionalCost();
    }
}
