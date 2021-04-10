package com.company;

/**
 * Represents a pair of a street node and a distance.
 * Implements the comparable interface.
 */
public class Pair implements Comparable<Pair>{

    /**
     * A distance, represented as an int.
     */
    private int distance;

    /**
     * A street node, represented as an int.
     */
    private int intNode;

    /**
     * No parameter constructor.
     * Sets the properties to their default values.
     */
    public Pair() {

    }

    /**
     * Two parameter constructor.
     * Sets the distance and the node.
     * @param distance A distance, represented as an int.
     * @param node A street node, represented as an int.
     */
    public Pair(int distance, int node) {
        this.distance = distance;
        this.intNode = node;
    }

    /**
     * Gets the distance.
     * @return The distance, represented as an int.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets the distance.
     * @param distance A distance to be set, represented as an int.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Gets the street node
     * @return The street node, represented as an int.
     */
    public int getIntNode() {
        return intNode;
    }

    /**
     * Sets the street node.
     * @param node A street node to be set, represented as an int.
     */
    public void setIntNode(int node) {
        this.intNode = node;
    }

    /**
     * Overridden method from the comparable interface.
     * Sets an ordering of objects of type Pair, based on distances.
     * @param pair An object of type Pair to which the comparison will be made.
     * @return The ordering of the elements, represented as an int.
     */
    @Override
    public int compareTo(Pair pair) {
        return distance - pair.getDistance();
    }
}
