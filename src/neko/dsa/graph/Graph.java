package neko.dsa.graph;

import java.util.List;

public class Graph {
    private final List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

    public void addEdge(Node source, Node target, double distance) {
        source.addNeighbor(target, distance);
    }

    public void removeEdge(Node source, Node target) {
        source.removeNeighbor(target);
    }

    public void removeEdge(Node source, String targetId) {
        source.removeNeighbor(targetId);
    }


    public double getDistance(Node source, Node target) {
        for (Edge edge : source.getNeighbors()) {
            if (edge.target == target) {
                return edge.distance;
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
