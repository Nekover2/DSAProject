package BusSystem;

import neko.dsa.graph.DijkstraAlgorithm;
import neko.dsa.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Passenger implements Comparable<Passenger> {
    private String id;
    private boolean pregnantStatus;
    private int age;
    private String Location;
    private String destination;
    private long timeRegistered;
    private int priority;
    private DijkstraAlgorithm findShortestPath;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        if (age >= 65) {
            priority += 15;
        }
        if (age >= 50) {
            priority += 10;
        }
        if (isPregnantStatus()) {
            priority += 20;
        }
        return priority;
    }

    public Passenger(String id, boolean pregnantStatus, int age, String location, String destination, long timeRegistered) {
        this.id = id;
        this.pregnantStatus = pregnantStatus;
        this.age = age;
        Location = location;
        this.destination = destination;
        this.timeRegistered = timeRegistered;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPregnantStatus(boolean pregnantStatus) {
        this.pregnantStatus = pregnantStatus;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTimeRegistered(long timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public String getId() {
        return id;
    }

    public boolean isPregnantStatus() {
        return pregnantStatus;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return Location;
    }

    public String getDestination() {
        return destination;
    }

    public long getTimeRegistered() {
        return timeRegistered;
    }


    @Override
    public int compareTo(Passenger other) {
        return Integer.compare(this.getPriority(), other.getPriority());
    }
    // LẤY TỪ BÀI CHUNG.
    public List<String> getPath(DijkstraAlgorithm dijkstra, List<Node> graph, String location, String destination) {
         List<String> path = new ArrayList<String>();
         path = dijkstra.findShortestPath(graph, location, destination);
         return path;
    }
}
