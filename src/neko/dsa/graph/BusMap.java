package neko.dsa.graph;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BusMap {

    private List<Node> map;

    private List<Node> requiredNodes;

    private List<ExtendedNode> allRequiredNodes = new ArrayList<>();
    List<ExtendedNode> getAllRequiredNodes1 = new ArrayList<>();
    private Node startNode;

    List<List<Double>> distanceMatrix = new ArrayList<>();
    List<List<List<String>>> pathMatrix = new ArrayList<>();

    public BusMap(List<Node> map, List<Node> requiredNodes) {
        this.map = map;
        this.requiredNodes = requiredNodes;
        allRequiredNodes = new ArrayList<>();
    }

    public List<Node> getMap() {
        return map;
    }

    public void setMap(List<Node> map) {
        this.map = map;
    }

    public List<Node> getRequiredNodes() {
        return requiredNodes;
    }

    public void setRequiredNodes(List<Node> requiredNodes) {
        this.requiredNodes = requiredNodes;
    }

    public void addRequiredNode(Node node) {
        requiredNodes.add(node);
    }

    public void dijkstraAllRequiredNodes() {
        this.getAllRequiredNodes1 = new ArrayList<>();
        //addRequiredNode(startNode);
        for(Node startNode : requiredNodes) {

            List<Double> tmp = new ArrayList<>();
            List<List<String>> tmp1 = new ArrayList<>();
            for (Node endNode : requiredNodes) {
                if(startNode == endNode) {
                    tmp.add(0.0);
                    tmp1.add(new ArrayList<>());
                    continue;
                }
                List<String> shortPath = DijkstraAlgorithm.findShortestPath(map, startNode.getId(), endNode.getId());
                double shortDistance = DijkstraAlgorithm.getShortestDistance(map, shortPath);
//                if(shortDistance == Double.POSITIVE_INFINITY || shortDistance == 0) {
//                    continue;
//                }

                tmp.add(shortDistance);
                tmp1.add(shortPath);
            }
            distanceMatrix.add(tmp);
            pathMatrix.add(tmp1);

        }
    }

    public List<ExtendedNode> getOptimal() {
        ExtendedGraph extendedGraph = new ExtendedGraph();
        for (Node requiredNode : requiredNodes) {
            ExtendedNode extendedNode = new ExtendedNode(requiredNode.getId());
            extendedGraph.addNode(extendedNode);
        }

        for(int i = 0; i < requiredNodes.size(); i++) {
            ExtendedNode extendedNode = extendedGraph.nodes.get(i);
            for(int j = 0; j < requiredNodes.size(); j++) {
                if(i == j) {
                    continue;
                }
                ExtendedNode extendedNode1 = extendedGraph.nodes.get(j);
                extendedNode.addNeighbor(extendedNode1, distanceMatrix.get(i).get(j), pathMatrix.get(i).get(j));
            }
        }

        return TwoOptAlgorithm.ShortestPathFinder.findOptimalPath(extendedGraph.nodes.get(0), extendedGraph);
    }


    public List<String> getShortestPath() {
        this.dijkstraAllRequiredNodes();
        List<ExtendedNode> optimal = this.getOptimal();
        List<String> shortestPath = new ArrayList<>();
        shortestPath.add(optimal.get(0).getId());
        for(int i = 0; i < optimal.size() - 1; i++) {
            ExtendedNode currentNode = optimal.get(i);
            ExtendedNode nextNode = optimal.get(i + 1);

            for(ExtendedEdge edge : currentNode.getNeighbors()) {

                if(edge.getTarget() == nextNode) {
                    shortestPath.remove(shortestPath.size() - 1);
                    shortestPath.addAll(edge.getPath());
                    break;
                }
            }
        }

        System.out.println("Shortest Path: " + shortestPath);
        return shortestPath;
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

        nodeD.addNeighbor(nodeA, 2);

        graph.add(nodeA);
        graph.add(nodeB);
        graph.add(nodeC);
        graph.add(nodeD);


        List<Node> requiredNodes = new ArrayList<>();

        requiredNodes.add(nodeA);
        requiredNodes.add(nodeB);
        requiredNodes.add(nodeC);
        requiredNodes.add(nodeD);

        BusMap busMap = new BusMap(graph, requiredNodes);

        busMap.startNode = nodeA;
        busMap.dijkstraAllRequiredNodes();

        for(ExtendedNode node : busMap.allRequiredNodes) {
            for (ExtendedEdge edge : node.getNeighbors()) {
                System.out.println(edge.getDistance() + " " + edge.getTarget().getId());
            }
            System.out.println("-----");
        }




//        List<ExtendedNode> optimal1 = busMap.getOptimal();
        busMap.getShortestPath();
//        for(ExtendedNode node : optimal1) {
//            System.out.println(node.getId());
//        }
    }
}
