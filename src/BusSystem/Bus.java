package BusSystem;

import neko.dsa.graph.BusMap;
import neko.dsa.graph.DijkstraAlgorithm;
import neko.dsa.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private Passenger[] seatList;
    private String currentNode;
    BusMap busMap;
    int indexOfSeatList = 0;
    private OrderedArrayMaxPQ<Passenger> priorityQueue;
    private DijkstraAlgorithm dijkstraAlgorithm;

    public Bus(String currentNode) {
        this.dijkstraAlgorithm = new DijkstraAlgorithm();
        this.priorityQueue = new OrderedArrayMaxPQ<>(16);
        seatList = priorityQueue.getPq();
        this.currentNode = currentNode;
    }

    public Bus(List<Node> map, int maxCapacity, String currentNode) {
        this.dijkstraAlgorithm = new DijkstraAlgorithm();
        this.priorityQueue = new OrderedArrayMaxPQ<>(maxCapacity);
        seatList = priorityQueue.getPq();
        this.busMap = new BusMap(map, new ArrayList<>());
        this.busMap.addRequiredNode(Node.getNodeById(map, currentNode));
        this.currentNode = currentNode;
        this.busMap = new BusMap(map, null);
    }

    public Passenger[] getPassengers() {
        return seatList;
    }

    private void printPickupNotification(String passengerId) {
        System.out.println("Bus picked up Passenger " + passengerId);
    }

    // Method to print notifications for moving to a different node
    private void printMoveNotification(String nextNode) {
        System.out.println("Bus moved to Node " + nextNode);
    }

    public void pickUpPassenger(Passenger passenger) {
        if (this.currentNode.equals(passenger.getLocation())) {
            // Print notification that the bus picked up the passenger
            printPickupNotification(passenger.getId());

            // Add the passenger to the bus using the priorityQueue
            addPassenger(passenger, this.priorityQueue);
        }
    }


    public void addPassenger(Passenger passenger, OrderedArrayMaxPQ<Passenger> priorityQueue) {
        busMap.addRequiredNode(Node.getNodeById(busMap.getMap(), passenger.getDestination()));
        busMap.addRequiredNode(Node.getNodeById(busMap.getMap(), passenger.getLocation()));
        try {
            busMap.getOptimal();
        } catch (Exception e) {
            System.out.println("Cannot find the optimal path");
        }
        seatList[indexOfSeatList] = priorityQueue.insert(passenger);
    }

    public void moveToTheFirstPassenger(Passenger passenger) {
    }

    // MỖI PASSENGER CÓ MỘT PATH RIÊNG, VỚI PASSENGER A THÌ PATH NÀY LÀ KHOẢNG CÁCH TỪ CHỖ A ĐỨNG
    // ĐẾN ĐỊA ĐIỂM A MUỐN ĐẾN. HÀM NÀY GIẢ SỬ XE ĐANG ĐỨNG TẠI VỊ TRÍ KHÁCH ĐẦU TIÊN NÊN PHẢI CÓ
    // HÀM ĐỂ XE DI CHUYỂN TỪ ĐỊA ĐIỂM ĐẦU TIÊN CỦA XE ĐẾN VỊ TRÍ KHÁCH ĐẦU TIÊN.
//    public void move(List<Passenger> passengerList) {
//        moveToTheFirstPassenger(passengerList.get(0));
//
//        for (int i = 0; i < passengerList.size(); i++)
//        {
//            String[] path = passengerList.get(i).getPath();
//            for (int j = 0; j < path.length; j++) {
//                this.currentNode = path[i];
//                if (this.currentNode == passengerList.get(i).getLocation()) {
//                    this.addPassenger(passengerList.get(i), this.priorityQueue);
//                }
//            }
//            List<String> anotherPath = this.moveBetweenNonPath(this.currentNode, passengerList.get(i + 1).getLocation());
//            for (int k = 0; k < anotherPath.size(); k++) {
//                this.currentNode = anotherPath.get(k);
//            }
//        }
//    }
    // HÀM NÀY LẤY 1 LIST CÁC PASSENGER ĐỂ DI CHUYỂN XE BUÝT QUA CÁC QUÃNG ĐƯỜNG ỨNG VỚI MỖI
    // PASSENGER, NGOÀI RA CẦN THÊM List<Node> graph ĐỂ THỰC HIỆN THUẬT TOÁN CỦA CHUNG
//    public void move(List<Passenger> passengerList, List<Node> graph) {
//        moveToTheFirstPassenger(passengerList.get(0));
//
//        for (Passenger passenger : passengerList) {
//            this.pickUpPassenger(passenger);
//            //                                                       CẦN FIX
//            List<String> path = passenger.getPath(dijkstraAlgorithm, graph, passenger.getLocation(), passenger.getDestination());
//            // Iterate over the passenger's path
//            for (String node : path) {
//                this.currentNode = node;
//                this.printMoveNotification(node);
//            }
//
//            // Logic for moving between non-path nodes
//            List<String> anotherPath = moveBetweenNonPath(dijkstraAlgorithm, graph, this.currentNode, );
//            for (String node : anotherPath) {
//                // Move the bus to the non-path node
//                this.currentNode = node; // Update currentNode accordingly
//            }
//        }
//    }
    public void move(List<Passenger> passengerList, List<Node> graph) {
        moveToTheFirstPassenger(passengerList.get(0));
        String startLocation = this.currentNode;

        for (int i = 0; i < passengerList.size(); i++) {
            Passenger passenger = passengerList.get(i);

            this.pickUpPassenger(passenger);

            List<String> path = passenger.getPath(dijkstraAlgorithm, graph, startLocation, passenger.getDestination());

            // Iterate over the passenger's path
            for (String node : path) {
                this.currentNode = node;
                this.printMoveNotification(node);
            }

            if (i < passengerList.size() - 1) {
                Passenger nextPassenger = passengerList.get(i + 1);
                List<String> anotherPath = moveBetweenNonPath(dijkstraAlgorithm, graph, this.currentNode, nextPassenger.getLocation());

                // Logic for moving between non-path nodes
                for (String node : anotherPath) {
                    // Move the bus to the non-path node
                    this.currentNode = node;
                    this.printMoveNotification(node);
                }
                startLocation = this.currentNode;
            }
        }
    }


    public List<String> moveBetweenNonPath(DijkstraAlgorithm dijkstra, List<Node> graph, String location, String destination) {
        return dijkstra.findShortestPath(graph, location, destination);
    }

    public void addPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            this.addPassenger(passenger, this.priorityQueue);
        }
    }


//    public List<Node> moveBetweenNonPath(Node start, Node end) {
//
//    }
}
