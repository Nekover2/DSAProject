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

    /**
     * Add a passenger to the bus, if the bus is full, replace the passenger with the lowest priority
     * @param passenger the passenger to be added
     */
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

    /**
     * Remove a passenger from the bus
     * @return the passenger to be removed
     */
    public Passenger removePassenger() {
        return priorityQueue.poll();
    }

    /**
     * Get the current accepted passengers
     * @return the current accepted passengers
     */
    public List<Passenger> getCurrentAcceptedPassengers() {
        return List.copyOf(priorityQueue);
    }

    /**
     * Get the optimal path for the bus
     * @return the optimal path for the bus
     */
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

    /**
     * Check if the passenger is acceptable by the bus
     * @param passenger the passenger to be checked
     * @return true if the passenger is acceptable by the bus
     */
    public boolean isAcceptable(Passenger passenger) {
        return this.priorityQueue.contains(passenger);
    }

    /**
     * Get the travel path of the passenger
     * @param passenger the passenger to be checked
     * @return The travel path of the passenger
     */
    public List<String> getPassengerPath(Passenger passenger) {
        if (!isAcceptable(passenger)) {
            throw new IllegalArgumentException("Passenger is not in the bus");
        }
        List<String> path = busMap.getShortestPath();
        int index = path.indexOf(passenger.getPickupLocation());
        int endIndex = path.indexOf(passenger.getDestination());
        return path.subList(index,endIndex);
    }

    /**
     * Get the estimated wait time for the passenger
     * @param passenger the passenger to be checked
     * @return the estimated wait time for the passenger
     */
    public double getEstimatedWaitTime(Passenger passenger) {
        //check if passenger is in the bus
        if (!isAcceptable(passenger)) {
            throw new IllegalArgumentException("Passenger is not in the bus");
        }
        return busMap.getDistance(busMap.getRequiredNodes().get(0), Node.getNodeById(busMap.getMap(), passenger.getDestination())) / 60;
    }
}
