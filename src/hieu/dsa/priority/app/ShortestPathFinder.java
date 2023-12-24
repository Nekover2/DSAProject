package hieu.dsa.priority.app;

import java.util.*;
class ShortestPathFinder {
    private static List<String> findShortestPath(List<Node> graph, List<String> requiredNodes, String startNodeId) {
        List<String> shortestPath = null;

        // Find the starting node
        Node startNode = getNodeById(graph, startNodeId);

        // Perform modified BFS
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Initialize the queue with the start node
        queue.offer(Collections.singletonList(startNodeId));
        visited.add(startNodeId);

        while (!queue.isEmpty()) {
            List<String> currentPath = queue.poll();
            Node currentNode = getNodeById(graph, currentPath.get(currentPath.size() - 1));

            // Check if all required nodes have been visited
            if (visited.containsAll(requiredNodes)) {
                shortestPath = currentPath;
                break;
            }

            // Explore neighbors
            for (int i = 0; i < currentNode.neighbors.size(); i++) {
                Node neighbor = currentNode.neighbors.get(i);
                int distance = currentNode.distances.get(i);

                if (!visited.contains(neighbor.id)) {
                    visited.add(neighbor.id);

                    // Create a new path by appending the neighbor to the current path
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor.id);

                    queue.offer(newPath);
                }
            }
        }

        return shortestPath;
    }

    private static Node getNodeById(List<Node> graph, String id) {
        for (Node node : graph) {
            if (node.id.equals(id)) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Node> graph = new ArrayList<>();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");

        nodeA.neighbors.add(nodeB);
        nodeA.distances.add(10);
        nodeA.neighbors.add(nodeC);
        nodeA.distances.add(3);
        nodeA.neighbors.add(nodeD);
        nodeA.distances.add(20);

        nodeB.neighbors.add(nodeC);
        nodeB.distances.add(5);

        nodeC.neighbors.add(nodeD);
        nodeC.distances.add(5);

        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);

        List<String> requiredNodes = List.of("D");

        List<String> shortestPath = findShortestPath(graph, requiredNodes, "A");

        System.out.println("Shortest path: " + shortestPath);
    }
}