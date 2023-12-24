package neko.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class ExtendedNode {
    private final String id;
    private final List<ExtendedEdge> neighbors;

    ExtendedNode(String id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    ExtendedNode(String id, List<ExtendedEdge> neighbors) {
        this.id = id;
        this.neighbors = neighbors;
    }

    public String getId() {
        return id;
    }

    public List<ExtendedEdge> getNeighbors() {
        return neighbors;
    }


    public void removeNeighbor(ExtendedNode target) {
        neighbors.removeIf(edge -> edge.target == target);
    }

    public void removeNeighbor(String targetId) {
        neighbors.removeIf(edge -> edge.target.getId().equals(targetId));
    }

    public double getDistanceTo(ExtendedNode target) {
        for (ExtendedEdge edge : neighbors) {
            if (edge.target == target) {
                return edge.distance;
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    public void addNeighbor(ExtendedNode target, double distance) {
        neighbors.add(new ExtendedEdge(target, distance));
    }

    public void addNeighbor(ExtendedNode target, double distance, List<String> path) {
        neighbors.add(new ExtendedEdge(target, distance, path));
    }

    static ExtendedNode getNodeById(List<ExtendedNode> nodes, String id) {
        for (ExtendedNode node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }
}
