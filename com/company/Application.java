package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main logic class.
 * Connects all other classes together.
 */
public class Application {

    /**
     * An input scanner, created from an input file
     */
    private Scanner inputScanner;

    /**
     * An output writer, created from an output file.
     */
    private PrintWriter outputWriter;

    /**
     * A streets graph, which represents the connexions between the streets.
     */
    private Graph streetsGraph;

    /**
     * A vehicle map, which contains all the different types of vehicles.
     */
    private Map<String, Vehicle> vehicleMap;

    /**
     * No parameter constructor.
     * Initializes the input scanner and the output writer from the correct
     * input/output files, reads the graph from the input file and
     * initialises the vehicle list.
     */
    public Application() {
        initFiles();

        streetsGraph = new Graph();
        streetsGraph.readGraph(inputScanner);

        initVehicles();
    }

    /**
     * Gets the street graph.
     *
     * @return A graph representing the way the streets are mapped out.
     */
    public Graph getStreetsGraph() {
        return streetsGraph;
    }

    /**
     * Sets the street graph.
     *
     * @param streetsGraph A streets graph that will be set.
     */
    public void setStreetsGraph(Graph streetsGraph) {
        this.streetsGraph = streetsGraph;
    }

    /**
     * Gets the input scanner.
     *
     * @return The input scanner.
     */
    public Scanner getInputScanner() {
        return inputScanner;
    }

    /**
     * Sets the input scanner.
     *
     * @param inputScanner An input scanner that will be set.
     */
    public void setInputScanner(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    /**
     * Gets the output writer.
     *
     * @return The output writer.
     */
    public PrintWriter getOutputWriter() {
        return outputWriter;
    }

    /**
     * Sets the output writer.
     *
     * @param outputWriter An output scanner that will be set.
     */
    public void setOutputWriter(PrintWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Gets the vehicle map;
     *
     * @return The vehicle map.
     */
    public Map<String, Vehicle> getVehicleMap() {
        return vehicleMap;
    }

    /**
     * Sets the vehicle map.
     *
     * @param vehicleMap A vehicle map that will be set.
     */
    public void setVehicleMap(Map<String, Vehicle> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    /**
     * Reads the input file for commands and command parameters and runs the
     * corresponding graph methods for each command.
     */
    public void run() {

        while (inputScanner.hasNext()) {
            String command = inputScanner.next();
            if (command.equals("accident") || command.equals("trafic") ||
                    command.equals("blocaj")) {
                streetsGraph.addRestriction(inputScanner.next(),
                        inputScanner.next(),
                        new Restriction(command, inputScanner.nextInt()));
            } else if (command.equals("drive")) {
                String vehicleType = inputScanner.next();
                String startingPoint = inputScanner.next();
                String destination = inputScanner.next();
                Vehicle vehicle = getVehicle(vehicleType);
                if (vehicle != null) streetsGraph
                        .drive(vehicle, startingPoint, destination,
                                outputWriter);
            }
        }

        outputWriter.close();
    }

    /**
     * Private helper method.
     * Gets a vehicle for the vehicle map, at a certain key.
     *
     * @param key A string representing the key at which the vehicle will be
     *            returned.
     * @return The vehicle at the key.
     */
    private Vehicle getVehicle(String key) {
        if (vehicleMap.containsKey(key)) return vehicleMap.get(key);
        return null;
    }

    /**
     * Private helper method.
     * Initializes the input scanner and the output writer from the default
     * files.
     */
    private void initFiles() {
        try {
            inputScanner = new Scanner(new File("map.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File outputFile = new File("map.out");
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter("map.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputWriter = new PrintWriter(fw);
    }

    /**
     * Private helper method.
     * Initializes the vehicle map with the 4 default vehicles.
     */
    private void initVehicles() {
        vehicleMap = new HashMap<>();
        vehicleMap.put("b", new Bicycle());
        vehicleMap.put("m", new Motorcycle());
        vehicleMap.put("a", new Car());
        vehicleMap.put("c", new Truck());
    }
}
