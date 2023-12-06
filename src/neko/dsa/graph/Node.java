package neko.dsa.graph;

import java.util.List;

public class Node {
    int id; // id of this node
    boolean type; // true: city, false: crossroad
    int x, y; // coordinates

    double cost;

    List<Node> neighbors; // neighbors of this node, that mean the roads that connect to this node

    public Node(int id, boolean type, int x, int y) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Node(int id, boolean type, int x, int y, List<Node> neighbors) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.neighbors = neighbors;
    }

    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public void addNeighbors(List<Node> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    public void removeNeighbor(Node neighbor) {
        this.neighbors.remove(neighbor);
    }

    public void removeNeighbors(List<Node> neighbors) {
        this.neighbors.removeAll(neighbors);
    }

    public void clearNeighbors() {
        this.neighbors.clear();
    }

    public List<Node> getNeighbors() {
        return this.neighbors;
    }

    public boolean isCity() {
        return this.type;
    }

    public boolean isCrossroad() {
        return !this.type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCity() {
        this.type = true;
    }

    public void setCrossroad() {
        this.type = false;
    }

    public double getDistance(Node node) {
        return Math.sqrt(Math.pow(this.x - node.x, 2) + Math.pow(this.y - node.y, 2));
    }

    public static double getDistance(Node node1, Node node2) {
        return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
