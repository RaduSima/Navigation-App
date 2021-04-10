package com.company;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a connexion of streets as a graph.
 */
public class Graph {

    /**
     * The number of street intersections, represented as a string.
     */
    private int numNodes;

    /**
     * The way the streets are connected, represented as a map with string
     * keys that contains maps of streets with string keys.
     */
    private Map<String, Map<String, Street>> streetMap;

    /**
     * No parameter constructor.
     * Sets the number of nodes to 0 and allocates the street map to a new
     * object of type HashMap.
     */
    public Graph() {
        numNodes = 0;
        streetMap = new HashMap<>();
    }

    /**
     * One parameter constructor which sets the number of nodes to the
     * parameter's value and the street map to a default value.
     *
     * @param numNodes The number of nodes to be set, represented as an int.
     */
    public Graph(int numNodes) {
        this.numNodes = numNodes;
        streetMap = new HashMap<String, Map<String, Street>>(numNodes);

        for (int i = 0; i < numNodes; i++) {
            addNode("P" + i);
        }
    }

    /**
     * Gets the number of nodes.
     *
     * @return The number of nodes, represented as an int.
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Sets the number of nodes.
     *
     * @param numNodes The number of nodes to be set, represented as an int.
     */
    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    /**
     * Gets the street map.
     *
     * @return The street map, represented as a map of maps of streets.
     */
    public Map<String, Map<String, Street>> getStreetMap() {
        return streetMap;
    }

    /**
     * Sets the street map.
     *
     * @param streetMap The street map to be set.
     */
    public void setStreetMap(Map<String, Map<String, Street>> streetMap) {
        this.streetMap = streetMap;
    }

    /**
     * Adds a restriction to a street.
     *
     * @param startingPoint A starting point of a street, represented as a
     *                      string.
     * @param destination   An end point of a street, represented as a
     *                      *                      string.
     * @param res           A restriction to be added to the street from the
     *                      starting
     *                      point to the destination.
     */
    public void addRestriction(String startingPoint, String destination,
                               Restriction res) {
        Street street = getStreet(startingPoint, destination);
        if (street != null) street.increaseExtraCost(res);
    }

    /**
     * Reads the graph from an input file.
     *
     * @param sc An input scanner associated to an input file.
     */
    public void readGraph(Scanner sc) {
        int streetsNumber = sc.nextInt();
        int NodesNumber = sc.nextInt();
        setNumNodes(NodesNumber);

        for (int i = 0; i < streetsNumber; i++) {
            String startingPoint = sc.next();
            String destination = sc.next();
            int cost = sc.nextInt();
            int limitaGabarit = sc.nextInt();

            addNode(startingPoint);
            addStreet(startingPoint, destination,
                    new Street(cost, limitaGabarit));
        }
    }

    /**
     * Finds the shortest possible path for a vehicle to go from a starting
     * point to a destination and writes the nodes to the output file, in
     * order of traversal.
     *
     * @param vehicle       A vehicle that will be driven from starting point to
     *                      destination.
     * @param startingPoint A starting point from which the vehicle starts.
     * @param destination   A destination that the vehicle will reach.
     * @param outputWriter  An output writes associated with the output file
     *                      where the shortest path will be written to.
     */
    public void drive(Vehicle vehicle, String startingPoint, String destination,
                      PrintWriter outputWriter) {
        boolean[] added = new boolean[numNodes];
        int[] distances = new int[numNodes];
        int[] parents = new int[numNodes];

        int intStartingPoint = stringNodeToIntNode(startingPoint);
        parents[intStartingPoint] = -1;
        for (int i = 0; i < numNodes; i++)
            distances[i] = Integer.MAX_VALUE;
        distances[intStartingPoint] = 0;

        // Creates CUSTOM priority queue
        MyPriorityQueue pq = new MyPriorityQueue(numNodes);
        pq.offer(new Pair(distances[intStartingPoint], intStartingPoint));

        // Extracts nodes from the priority queue until it is empty.
        while (!pq.isEmpty()) {
            Pair extractedPair = pq.poll();
            int extractedNodeInt = extractedPair.getIntNode();
            if (!added[extractedNodeInt]) {
                added[extractedNodeInt] = true;

                iterateDest(extractedNodeInt, vehicle, added, distances,
                        parents, pq);
            }
        }

        printPath(distances, parents, destination, startingPoint, outputWriter);
    }

    /**
     * Private helper method.
     * Adds a node to the street map, represented my another map of streets
     * with keys of type string.
     *
     * @param startingPoint The key at which the node will be added,
     *                      represented as a string.
     */
    private void addNode(String startingPoint) {
        if (!streetMap.containsKey(startingPoint))
            streetMap.put(startingPoint, new HashMap<>());
    }

    /**
     * Private helper method.
     * Adds a street to the street map.
     *
     * @param startingPoint The starting point of the street, represented as
     *                      a string.
     * @param destination   The end point of the street, represented as a
     *                      string.
     * @param street        The street to be added to the map.
     */
    private void addStreet(String startingPoint, String destination,
                           Street street) {
        if (streetMap.containsKey(startingPoint)) {
            if (streetMap.get(startingPoint).containsKey(destination)) return;
            streetMap.get(startingPoint).put(destination, street);
        }
    }

    /**
     * Private helper method.
     * Gets a street from the street map.
     *
     * @param startingPoint The starting point of the street.
     * @param destination   The end point of the street.
     * @return The street between the starting point and the destination, of
     * null if one does not exist.
     */
    private Street getStreet(String startingPoint, String destination) {
        if (streetMap.containsKey(startingPoint))
            if (streetMap.get(startingPoint).containsKey(destination))
                return streetMap.get(startingPoint).get(destination);
        return null;
    }

    /**
     * Private helper method.
     * Iterates through adjacent nodes of a certain node and updates a
     * distances array representing the shortest distances between nodes.
     *
     * @param extractedNodeInt A node of which adjacent nodes will be
     *                         computed, represented as an int.
     * @param vehicle          The vehicle for which the shortest path is
     *                         computed.
     * @param added            An array of booleans that represent if certain
     *                        nodes have
     *                         been computed
     * @param distances        The current shortest distances between nodes,
     *                         represented as an array of integers.
     * @param parents          The current shortest path, represented as an
     *                         array of
     *                         integers.
     * @param pq               A priority queue in which nodes are stored
     *                         based on shortest
     *                         distance.
     */
    private void iterateDest(int extractedNodeInt, Vehicle vehicle,
                             boolean[] added, int[] distances, int[] parents,
                             MyPriorityQueue pq) {
        String extractedNodeString = intNodeToStringNode(extractedNodeInt);
        if (streetMap.containsKey(extractedNodeString)) {
            Map<String, Street> currentAdjList =
                    streetMap.get(extractedNodeString);

            // Iterates through all adjacent nodes and updates
            //shortest distances between nodes, if shorter ones are found.
            for (String currentDestinationString : currentAdjList.keySet()) {
                Street currentStreet = getStreet(extractedNodeString,
                        currentDestinationString);
                int currentDestinationInt =
                        stringNodeToIntNode(currentDestinationString);

                if (vehicle.getWeight() <= currentStreet.getWeightLimit()) {
                    if (!added[currentDestinationInt]) {

                        // Checks if distance needs an update or not
                        int newKey = distances[extractedNodeInt] +
                                vehicle.computeCost(currentStreet);
                        int currentKey = distances[currentDestinationInt];
                        if (currentKey > newKey) {
                            Pair p = new Pair(newKey, currentDestinationInt);
                            pq.offer(p);
                            parents[currentDestinationInt] = extractedNodeInt;
                            distances[currentDestinationInt] = newKey;
                        }
                    }
                }
            }
        }
    }

    /**
     * Private helper method.
     * Writes the shortest path between 2 points to an output file, or null
     * if it does not exist.
     *
     * @param distances     The shortest distances between nodes, represented as
     *                      an array of integers.
     * @param parents       The shortest path, represented as an array of
     *                      integers.
     * @param destination   A destination, represented as an int.
     * @param startingPoint A starting point, represented as an int.
     * @param outputWriter  The output writer associated with an output file.
     */
    private void printPath(int[] distances, int[] parents, String destination,
                           String startingPoint, PrintWriter outputWriter) {
        if (distances[stringNodeToIntNode(destination)] == Integer.MAX_VALUE)
            outputWriter.println(startingPoint + " " + destination + " null");
        else {
            printPathHelper(stringNodeToIntNode(destination), parents,
                    outputWriter);
            outputWriter.println(distances[stringNodeToIntNode(destination)]);
        }
    }

    /**
     * Private recursive helper method.
     * Iterates through a list of nodes representing the shortest path.
     *
     * @param destination  A destination node, represented as an integer.
     * @param parents      A list of shortest path nodes, represented as an
     *                     array
     *                     of integers.
     * @param outputWriter The output writer associated with an output file.
     */
    private void printPathHelper(int destination, int[] parents,
                                 PrintWriter outputWriter) {

        // Base case: source node has been processed
        if (destination == -1) return;
        printPathHelper(parents[destination], parents, outputWriter);
        outputWriter.print(intNodeToStringNode(destination) + " ");
    }

    /**
     * Private helper method.
     * Transforms an int representation of a node into a string
     * representation of a node.
     *
     * @param nodeIntValue An int representation of a node.
     * @return The string representation of a node.
     */
    private String intNodeToStringNode(int nodeIntValue) {
        return "P" + nodeIntValue;
    }


    /**
     * Private helper method.
     * Transforms a string representation of a node into an int representation
     * of a node.
     *
     * @param nodeStringValue A string representation of a node.
     * @return The int representation of a node.
     */
    private int stringNodeToIntNode(String nodeStringValue) {
        return Integer.parseInt(nodeStringValue.substring(1));
    }
}


