package BusSystem;

import BusSystem.*;
import neko.dsa.graph.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        // Creating 20 nodes for the map
//        List<Node> mapNodes = createNodes();
//
//        // Creating a bus with an initial node position
//        bus busSystem = new bus("A");
//
//        // Creating 16 passengers with different characteristics and destinations
//        List<Passenger> passengerList = createPassengers();
//
//        // Simulating the movement of the bus based on the passenger list and the map
//        busSystem.move(passengerList, mapNodes);
//
//        // Displaying the final position or status of the bus or any other relevant information
//        // ...
//
//        // You can add more functionality to display or manage the bus, passengers, or any other aspects of the system

    }

    private static List<Node> createNodes() {
        List<Node> mapNodes = new ArrayList<>();

        // Creating 20 nodes (you can adjust the connections as needed)
        for (char c = 'A'; c <= 'T'; c++) {
            mapNodes.add(new Node(String.valueOf(c)));
        }

        // Establishing connections between nodes (adjust connections as needed)
        mapNodes.get(0).addNeighbor(mapNodes.get(1), 10);
        mapNodes.get(1).addNeighbor(mapNodes.get(2), 5);
        // ... (add more connections between nodes)

        return mapNodes;
    }

    private static List<Passenger> createPassengers() {
        List<Passenger> passengerList = new ArrayList<>();

        // Creating 16 passengers with different destinations
        for (int i = 1; i <= 16; i++) {
            // Creating passengers with different characteristics and destinations
            Passenger passenger = new Passenger(
                    String.format("%03d", i),
                    i % 2 == 0, // Alternating pregnant status for demonstration
                    20 + i, // Incrementing age for each passenger
                    "A", // All passengers start at node A
                    String.valueOf((char) ('A' + i)) // Each passenger's destination is a different node
                    // You can modify the destination logic as needed based on your requirements
                    // For example, distribute destinations among nodes or set specific destinations
                    // based on a pattern or condition.
                    , System.currentTimeMillis());

            passengerList.add(passenger);
        }
        return passengerList;
    }
}
