package BusSystem;

import neko.dsa.graph.Node;

public class Passenger implements Comparable<Passenger> {
    private String id;
    private boolean pregnantStatus;
    private int age;
    private Node Location;
    private Node destination;
    private long timeRegistered;
    private int priority;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public Passenger(String id, boolean pregnantStatus, int age, Node location, Node destination, long timeRegistered) {
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

    public void setLocation(Node location) {
        Location = location;
    }

    public void setDestination(Node destination) {
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

    public Node getLocation() {
        return Location;
    }

    public Node getDestination() {
        return destination;
    }

    public long getTimeRegistered() {
        return timeRegistered;
    }


    @Override
    public int compareTo(Passenger o) {
        return 0;
    }
    // LẤY TỪ BÀI CHUNG.
    public Node[] getPath() {

    }
}
