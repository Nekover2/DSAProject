package neko.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String id;
    private final List<Edge> neighbors;

    public Node(String id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    Node(String id, List<Edge> neighbors) {
        this.id = id;
        this.neighbors = neighbors;
    }

    public String getId() {
        return id;
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node target, double distance) {
        neighbors.add(new Edge(target, distance));
    }

    public void removeNeighbor(Node target) {
        neighbors.removeIf(edge -> edge.target == target);
    }

    public void removeNeighbor(String targetId) {
        neighbors.removeIf(edge -> edge.target.getId().equals(targetId));
    }

    public double getDistanceTo(Node target) {
        for (Edge edge : neighbors) {
            if (edge.target == target) {
                return edge.distance;
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    static Node getNodeById(List<Node> nodes, String id) {
        for (Node node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }
}
