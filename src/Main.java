import neko.dsa.graph.*;
import neko.dsa.bus.*;
import java.util.*;


import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(initMap("src/Map.ini"));

//        for(Node node : initMap("src/Map.ini")) {
//            for(Edge des : node.getNeighbors()) {
//                System.out.println(node.getId() + " " + des.getTarget().getId() + " " + des.getDistance());
//            }
//        }

        Bus mainBus = new Bus("A", new ArrayList<>(), initMap("src/Map.ini"), 5);


        //Add List of passengers
        List<Passenger> passengers = new ArrayList<>();

        passengers.add(new Passenger(true, 20, "A", "B", 0));
        passengers.add(new Passenger(false, 10, "A", "C", 1));
        passengers.add(new Passenger(false, 90, "A", "Z", 5));
        passengers.add(new Passenger(false, 50, "B", "C", 2));
        passengers.add(new Passenger(false, 50, "B", "Z", 3));
        passengers.add(new Passenger(false, 50, "C", "Z", 4));
        passengers.add(new Passenger(false, 50, "C", "Z", 6));
        //Add passengers to bus
        for (Passenger passenger : passengers) {
            mainBus.addPassenger(passenger);
        }

        //Print out the optimal path
        System.out.println(mainBus.getOptimalPath());
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

    static List<Node> convertStringToNode(List<String> list, List<Node> map) {
        List<Node> result = new ArrayList<>();
        for(String node : list) {
            result.add(Node.getNodeById(map, node));
        }
        return result;


    }
}
