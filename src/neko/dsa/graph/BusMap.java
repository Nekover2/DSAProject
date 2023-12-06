package neko.dsa.graph;

import java.util.*;

public class BusMap {
    // This is the map of the current destination, estimated by the A* algorithm.
    Node startNode; // the node the bus is starting from
    Graph graph; // the graph of the map
    List<Node> requiredNodes; // required nodes in the map (the node the bus must go through)
    public BusMap() {}

    public BusMap(Node startNode, Graph graph, List<Node> requiredNodes) {
        this.startNode = startNode;
        this.graph = graph;
        this.requiredNodes = requiredNodes;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setRequiredNodes(List<Node> requiredNodes) {
        this.requiredNodes = requiredNodes;
    }

    public Node getStartNode() {
        return this.startNode;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public List<Node> getRequiredNodes() {
        return this.requiredNodes;
    }

    public void addRequiredNode(Node requiredNode) {
        this.requiredNodes.add(requiredNode);
    }

    public void addRequiredNodes(List<Node> requiredNodes) {
        this.requiredNodes.addAll(requiredNodes);
    }

    public void removeRequiredNode(Node requiredNode) {
        this.requiredNodes.remove(requiredNode);
    }

    public void removeRequiredNodes(List<Node> requiredNodes) {
        this.requiredNodes.removeAll(requiredNodes);
    }

    public void clearRequiredNodes() {
        this.requiredNodes.clear();
    }

    public List<Node> findPath() {
        // Find the shortest path from the start node to the required nodes.
        List<Node> path = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.cost));
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        for(Node node : graph.getNodes()) {
            distance.put(node, Double.MAX_VALUE);
            previous.put(node, null);
        }

        startNode.cost = 0;
        queue.add(startNode);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(requiredNodes.contains(node)) {
                path.add(node);
                while(previous.get(node) != null) {
                    node = previous.get(node);
                    path.add(node);
                }
                break;
            }
            for(Node neighbor : node.getNeighbors()) {
                double newCost = node.cost + distance.get(node) + distance.get(neighbor);
                if(newCost < distance.get(neighbor)) {
                    distance.put(neighbor, newCost);
                    previous.put(neighbor, node);
                    neighbor.cost = newCost;
                    queue.add(neighbor);
                }
            }


        }

        // Reconstruction the path from the start node to the required nodes.
        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = requiredNodes.isEmpty() ? startNode : null;
        while (currentNode != null) {
            shortestPath.add(currentNode);
            currentNode = previous.get(currentNode);
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
