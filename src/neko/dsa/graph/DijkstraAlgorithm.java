package neko.dsa.graph;

import java.util.*;

public class DijkstraAlgorithm {

    public static List<String> findShortestPath(List<Node> graph, String startNodeId, String endNodeId) {
        // Initialize distances and previous nodes
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Node> previousNodes = new HashMap<>();

        for (Node node : graph) {
            distances.put(node.getId(), Integer.MAX_VALUE);
            previousNodes.put(node.getId(), null);
        }

        // Set the distance of the start node to 0
        distances.put(startNodeId, 0);

        // Create a priority queue to store nodes based on their distances
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances.get(node.getId())));
        Set<String> visited = new HashSet<>();

        // Add the start node to the queue
        queue.offer(getNodeById(graph, startNodeId));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visited.add(currentNode.getId());

            if (currentNode.getId().equals(endNodeId)) {
                break;  // Reached the destination node, exit the loop
            }

            // Explore neighbors of the current node
            for (Edge edge : currentNode.getNeighbors()) {
                Node neighborNode = edge.target;
                int weight = edge.weight;

                if (!visited.contains(neighborNode.getId())) {
                    int newDistance = distances.get(currentNode.getId()) + weight;

                    if (newDistance < distances.get(neighborNode.getId())) {
                        distances.put(neighborNode.getId(), newDistance);
                        previousNodes.put(neighborNode.getId(), currentNode);
                        queue.offer(neighborNode);
                    }
                }
            }
        }

        // Reconstruct the shortest path
        List<String> shortestPath = new ArrayList<>();
        Node currentNode = getNodeById(graph, endNodeId);

        while (currentNode != null) {
            shortestPath.add(0, currentNode.getId());
            currentNode = previousNodes.get(currentNode.getId());
        }

        return shortestPath;
    }

    public static double getShortestDistance(List<Node> nodes, List<String> shortestPath) {
        double shortestDistance = 0;

        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Node currentNode = Node.getNodeById(nodes,shortestPath.get(i));
            Node nextNode = Node.getNodeById(nodes, shortestPath.get(i + 1));
            assert currentNode != null;
            double distance = currentNode.getDistanceTo(nextNode);
            shortestDistance += distance;
        }

        return shortestDistance == 0 ? Double.POSITIVE_INFINITY : shortestDistance;
    }
    private static Node getNodeById(List<Node> graph, String id) {
        for (Node node : graph) {
            if (node.getId().equals(id)) {
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

        nodeA.addNeighbor(nodeB, 10);
        //nodeA.addNeighbor(nodeC, 3);

        nodeB.addNeighbor(nodeC, 1);
        //nodeB.addNeighbor(nodeD, 2);

        //nodeC.addNeighbor(nodeB, 4);
        nodeC.addNeighbor(nodeD, 2);



        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);

        String startNodeId = "A";
        String endNodeId = "D";

        List<String> shortestPath = findShortestPath(graph, startNodeId, endNodeId);

        System.out.println("Shortest path: " + shortestPath);
    }
}
