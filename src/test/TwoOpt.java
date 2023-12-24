package test;
import java.util.*;
class TwoOpt {

    public static List<Node> twoOpt(List<Node> nodes) {
        // Initialize the tour with a random permutation of the nodes
        List<Node> tour = new ArrayList<>(nodes);
        Collections.shuffle(tour);

        // While there are still improvements to be made
        boolean improved = true;
        while (improved) {
            improved = false;

            // For each pair of edges in the tour
            for (int i = 0; i < tour.size() - 1; i++) {
                for (int j = i + 1; j < tour.size(); j++) {
                    // Reverse the order of the cities between the two edges
                    List<Node> newTour = new ArrayList<>(tour);
                    Collections.reverse(newTour.subList(i + 1, j));

                    // Calculate the distance of the new tour
                    double newTourDistance = calculateDistance(newTour);

                    // If the new tour is shorter than the old tour, replace the old tour with the new tour
                    double oldTourDistance = calculateDistance(tour);
                    if (newTourDistance < oldTourDistance) {
                        tour = newTour;
                        improved = true;
                    }
                }
            }
        }

        return tour;
    }

    private static double calculateDistance(List<Node> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            Node node1 = tour.get(i);
            Node node2 = tour.get(i + 1);
            double dx = node1.getCoordinates()[0] - node2.getCoordinates()[0];
            double dy = node1.getCoordinates()[1] - node2.getCoordinates()[1];
            distance += Math.sqrt(dx * dx + dy * dy);
        }
        return distance;
    }

    public static void main(String[] args) {
        // Create the list of nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(1, new double[]{0, 0}));
        nodes.add(new Node(2, new double[]{1, 1}));
        nodes.add(new Node(3, new double[]{2, 2}));
        nodes.add(new Node(4, new double[]{3, 3}));

        // Find the shortest tour of the nodes using the 2-opt algorithm
        List<Node> shortestTour = twoOpt(nodes);

        // Print the shortest tour
        for (Node node : shortestTour) {
            System.out.println(node.getId());
        }
    }
}