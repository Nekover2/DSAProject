package neko.dsa.graph;
import java.util.ArrayList;
import java.util.List;
public class TwoOptAlgorithm {

    public static List<Node> twoOpt(List<Node> graph, String startNodeId) {
List<Node> result = new ArrayList<>();
        Node startNode = null;
        for (Node node : graph) {
            if (node.getId().equals(startNodeId)) {
                startNode = node;
                break;
            }
        }
        if (startNode == null) {
            return result;
        }
        result.add(startNode);
        Node currentNode = startNode;
        while (true) {
            Edge minEdge = null;
            for (Edge edge : currentNode.getNeighbors()) {
                if (result.contains(edge.target)) {
                    continue;
                }
                if (minEdge == null || edge.distance < minEdge.distance) {
                    minEdge = edge;
                }
            }
            if (minEdge == null) {
                break;
            }
            result.add(minEdge.target);
            currentNode = minEdge.target;
        }
        return result;
    }

    private static Node getNodeById(List<Node> graph, String id) {
        for (Node node : graph) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }
}
