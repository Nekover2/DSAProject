package neko.dsa.graph;
import java.util.*;

public class TwoOptAlgorithm {

    static class ShortestPathFinder {
        public static List<ExtendedNode> findOptimalPath(ExtendedNode startNode, ExtendedGraph graph) {
            int numNodes = graph.nodes.size();

            // Generate an initial solution using the Nearest Neighbor algorithm

            // Apply the 2-opt algorithm to improve the initial solution
            List<ExtendedNode> currentPath = generateInitialPath(startNode, graph);
            double currentDistance = calculateTotalDistance(currentPath);

            boolean improvement = true;
            while (improvement) {
                improvement = false;

                for (int i = 1; i < numNodes - 1; i++) {
                    for (int j = i + 1; j < numNodes; j++) {
                        List<ExtendedNode> newPath = twoOptSwap(currentPath, i, j);
                        double newDistance = calculateTotalDistance(newPath);

                        if (newDistance < currentDistance) {
                            currentPath = newPath;
                            currentDistance = newDistance;
                            improvement = true;
                        }
                    }
                }
            }

            return currentPath;
        }

        private static List<ExtendedNode> generateInitialPath(ExtendedNode startNode, ExtendedGraph graph) {
            List<ExtendedNode> unvisitedNodes = new ArrayList<>(graph.nodes);
            unvisitedNodes.remove(startNode);

            List<ExtendedNode> initialPath = new ArrayList<>();
            initialPath.add(startNode);

            ExtendedNode currentNode = startNode;

            while (!unvisitedNodes.isEmpty()) {
                ExtendedNode nearestNeighbor = null;
                double minDistance = Double.MAX_VALUE;

                for (ExtendedEdge edge : currentNode.getNeighbors()) {
                    if (unvisitedNodes.contains(edge.getTarget())) {
                        if (edge.getDistance() < minDistance) {
                            nearestNeighbor = edge.getTarget();
                            minDistance = edge.getDistance();
                        }
                    }
                }

                if (nearestNeighbor != null) {
                    initialPath.add(nearestNeighbor);
                    unvisitedNodes.remove(nearestNeighbor);
                    currentNode = nearestNeighbor;
                } else {
                    break;
                }
            }

            initialPath.add(startNode); // Return to the starting node

            return initialPath;
        }

        private static List<ExtendedNode> twoOptSwap(List<ExtendedNode> path, int i, int j) {
            List<ExtendedNode> newPath = new ArrayList<>(path.subList(0, i));
            List<ExtendedNode> reversedSegment = new ArrayList<>(path.subList(i, j + 1));
            for (int k = reversedSegment.size() - 1; k >= 0; k--) {
                newPath.add(reversedSegment.get(k));
            }
            newPath.addAll(path.subList(j + 1, path.size()));
            return newPath;
        }

        private static double calculateTotalDistance(List<ExtendedNode> path) {
            double totalDistance = 0;

            for (int i = 0; i < path.size() - 1; i++) {
                ExtendedNode currentNode = path.get(i);
                ExtendedNode nextNode = path.get(i + 1);

                for (ExtendedEdge edge : currentNode.getNeighbors()) {
                    if (edge.getTarget() == nextNode) {
                        totalDistance += edge.getDistance();
                        break;
                    }
                }
            }

            return totalDistance;
        }
    }


        public static void main(String[] args) {
            // Create a sample graph
            ExtendedGraph graph = new ExtendedGraph();

            ExtendedNode node1 = new ExtendedNode("1");
            ExtendedNode node2 = new ExtendedNode("2");
            ExtendedNode node3 = new ExtendedNode("3");
            ExtendedNode node4 = new ExtendedNode("4");


            graph.addNode(node1);
            graph.addNode(node2);
            graph.addNode(node3);
            graph.addNode(node4);

            node1.addNeighbor(node2, 10);
            node1.addNeighbor(node3, 15);
            node1.addNeighbor(node4, 20);

            node2.addNeighbor(node1, 10);
            node2.addNeighbor(node3, 35);
            node2.addNeighbor(node4, 25);

            node3.addNeighbor(node1, 15);
            node3.addNeighbor(node2, 35);
            node3.addNeighbor(node4, 30);

            node4.addNeighbor(node1, 20);
            node4.addNeighbor(node2, 25);
            node4.addNeighbor(node3, 30);

            // Find the optimal path
            List<ExtendedNode> optimalPath = ShortestPathFinder.findOptimalPath(node1, graph);

            // Print the optimal path
            System.out.println("Optimal Path:");
            for (ExtendedNode node : optimalPath) {
                System.out.println(node.getId());
            }
        }
}
