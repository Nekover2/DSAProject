package neko.dsa.bus;

import neko.dsa.graph.BusMap;
import neko.dsa.graph.Node;

import java.util.List;
import java.util.PriorityQueue;

public class Bus {

    String startingNodeId;
    List<Passenger> passengerList;
    BusMap busMap;
    int currentCapacity = 0;
    PriorityQueue<Passenger> priorityQueue;
    int maxCapacity;
    public Bus(String startingNodeId, List<Passenger> passengerList, BusMap busMap) {
        this.passengerList = passengerList;
        this.busMap = busMap;
        this.startingNodeId = startingNodeId;
    }

    public Bus(String startingNodeId, List<Passenger> passengerList, List<Node> map, int maxCapacity) {
        this.passengerList = passengerList;
        this.busMap = new BusMap(map);
        this.maxCapacity = maxCapacity;
        this.startingNodeId = startingNodeId;
        busMap.addRequiredNode(Node.getNodeById(map,startingNodeId));
        priorityQueue = new PriorityQueue<>(maxCapacity);

        //busMap.addRequiredNode(Node.getNodeById(map,passenger.getDestination()));
        priorityQueue.addAll(passengerList);
    }

    public void addPassenger(Passenger passenger) {
        if (currentCapacity < maxCapacity) {
            priorityQueue.add(passenger);
            currentCapacity++;
        } else {
            if (passenger.getPriority() > priorityQueue.peek().getPriority()) {
                priorityQueue.poll();
                priorityQueue.add(passenger);
            }
        }
    }

    public Passenger removePassenger() {
        return priorityQueue.poll();
    }

    public List<Passenger> getCurrentAcceptedPassengers() {
        return List.copyOf(priorityQueue);
    }

    public List<String> getOptimalPath() {
        for(Passenger passenger : priorityQueue) {
            busMap.addRequiredNode(Node.getNodeById(busMap.getMap(),passenger.getDestination()));
            busMap.addRequiredNode(Node.getNodeById(busMap.getMap(),passenger.getPickupLocation()));
        }

        for (Node node: busMap.getRequiredNodes()) {
            System.out.println(node.getId());
        }
        return busMap.getShortestPath();
    }

    public List<String> getPassengerPath(Passenger passenger) {
        List<String> path = busMap.getShortestPath();
        int index = path.indexOf(passenger.getPickupLocation());
        int endIndex = path.indexOf(passenger.getDestination());
        return path.subList(index,endIndex);
    }

    public double getEstimatedWaitTime(Passenger passenger) {
        return busMap.getDistance(busMap.getRequiredNodes().get(0), Node.getNodeById(busMap.getMap(), passenger.getDestination())) / 60;
    }
}
