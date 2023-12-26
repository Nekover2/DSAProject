import neko.dsa.graph.*;
import java.util.*;
import BusSystem.*;

import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(initMap("src/Map.ini"));

//        for(Node node : initMap("src/Map.ini")) {
//            for(Edge des : node.getNeighbors()) {
//                System.out.println(node.getId() + " " + des.getTarget().getId() + " " + des.getDistance());
//            }
//        }

        List<Node> map = initMap("src/Map.ini");
        //Bus mainBus = initBus(map.get(0));

        //Add List of passengers
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("001", false, 20, "A", "B", System.currentTimeMillis()));
        passengers.add(new Passenger("002", false, 20, "A", "B", System.currentTimeMillis()));
        passengers.add(new Passenger("003", false, 20, "A", "Z", System.currentTimeMillis()));
        passengers.add(new Passenger("004", false, 20, "C", "Z", System.currentTimeMillis()));

        //Add passengers to bus

//        for (Passenger passenger : passengers) {
//            mainBus.pickUpPassenger(passenger);
//        }

        List<Node> requiredNodes = new ArrayList<>();
        requiredNodes.add(Node.getNodeById(map, "A"));
        requiredNodes.add(Node.getNodeById(map, "B"));
        requiredNodes.add(Node.getNodeById(map, "Z"));

        BusMap busMap = new BusMap(map, requiredNodes);

        // init BusMap to calculate the shortest path
//        BusMap busMap = new BusMap(map, convertStringToNode(getAllPassengersPlace(mainBus), map));
        System.out.println(busMap.getShortestPath());
        //Mỗi lần add hành khách thì sẽ có thêm điểm đón và trả vào trong requiredNodes
    }


    /**
     * Initialize the map from file
     */
    static  List<Node> initMap(String filename) throws FileNotFoundException {

        List<Node> result = new ArrayList<>();
        //read file, structure the map
        File maps = new File(filename);

        Scanner sc = new Scanner(maps);
        String listOfNodes = sc.nextLine();

        String[] nodes = listOfNodes.split(" ");
        for(String node : nodes) {
            result.add(new Node(node));
        }

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] node = line.split(" ");
            Node startNode = Node.getNodeById(result, node[0]);
            Node endNode = Node.getNodeById(result, node[1]);
            assert startNode != null;
            startNode.addNeighbor(endNode, Double.parseDouble(node[2]));
        }

        return result;
    }

    /**
     * Init the bus
     */
    static Bus initBus(Node startNode) {
        return new Bus(startNode.getId());
    }

    /**
     * Get all passengers' current place and destination
     * @return List of passengers' current place and destination
     */
    static List<String> getAllPassengersPlace(Bus bus) {
        Passenger[] passengers = bus.getPassengers();

        List<String> result = new ArrayList<>();

        for (Passenger passenger : passengers) {
            result.add(passenger.getLocation());
            result.add(passenger.getDestination());
        }

        return result;
    }

    static List<Node> convertStringToNode(List<String> list, List<Node> map) {
        List<Node> result = new ArrayList<>();
        for(String node : list) {
            result.add(Node.getNodeById(map, node));
        }
        return result;
    }
}
