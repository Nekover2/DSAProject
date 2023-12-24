package neko.dsa.graph;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class BusMap {

    private List<Node> map;

    private List<Node> requiredNodes;

    private List<ExtendedNode> allRequiredNodes = new ArrayList<>();
    List<ExtendedNode> getAllRequiredNodes1 = new ArrayList<>();
    private Node startNode;

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
        List<ExtendedNode> allRequiredNodes1 = new ArrayList<>();
        this.getAllRequiredNodes1 = allRequiredNodes1;
        //addRequiredNode(startNode);
        for(Node node : requiredNodes) {
            ExtendedNode extendedNode = new ExtendedNode(node.getId());

            allRequiredNodes1.add(extendedNode);
        }
        for(Node startNode : requiredNodes) {
            List<ExtendedEdge> extendedEdges = new ArrayList<>();
            for (Node endNode : requiredNodes) {
                if(startNode == endNode) {
                    continue;
                }
                List<String> shortPath = DijkstraAlgorithm.findShortestPath(map, startNode.getId(), endNode.getId());
                double shortDistance = DijkstraAlgorithm.getShortestDistance(map, shortPath);
//                if(shortDistance == Double.POSITIVE_INFINITY || shortDistance == 0) {
//                    continue;
//                }
                extendedEdges.add(new ExtendedEdge(ExtendedNode.getNodeById(allRequiredNodes1, endNode.getId()), shortDistance, shortPath));
            }

            allRequiredNodes.add(new ExtendedNode(startNode.getId(), extendedEdges));
        }
    }

    public List<ExtendedNode> getOptimal() {
        ExtendedGraph extendedGraph = new ExtendedGraph();
        for(ExtendedNode node : allRequiredNodes) {
            ExtendedNode tmpNode = new ExtendedNode(node.getId());
            extendedGraph.addNode(tmpNode);

            for (ExtendedEdge edge : node.getNeighbors()) {
                tmpNode.addNeighbor(edge.getTarget(), edge.getDistance(), edge.path);
            }

            tmpNode.addNeighbor(getAllRequiredNodes1.get(0), Double.POSITIVE_INFINITY, new ArrayList<>());
        }
//        extendedGraph.addNodes(allRequiredNodes);
        return TwoOptAlgorithm.ShortestPathFinder.findOptimalPath(allRequiredNodes.get(0), extendedGraph);
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


        List<ExtendedNode> optimalPath = busMap.getOptimal();


        ExtendedGraph graph1 = new ExtendedGraph();

        ExtendedNode node1 = new ExtendedNode("A");
        ExtendedNode node2 = new ExtendedNode("B");
        ExtendedNode node3 = new ExtendedNode("C");
        ExtendedNode node4 = new ExtendedNode("D");


        graph1.addNode(node1);
        graph1.addNode(node2);
        graph1.addNode(node3);
        graph1.addNode(node4);

//        node1.addNeighbor(node2, 10);
//        node1.addNeighbor(node3, 11);
      node1.addNeighbor(node4, 13);


        node1.addNeighbor(busMap.getAllRequiredNodes1.get(1), busMap.allRequiredNodes.get(0).getNeighbors().get(0).distance, busMap.allRequiredNodes.get(0).getNeighbors().get(0).path);
        node1.addNeighbor(busMap.allRequiredNodes.get(0).getNeighbors().get(1).target, busMap.allRequiredNodes.get(0).getNeighbors().get(1).distance, busMap.allRequiredNodes.get(0).getNeighbors().get(1).path);
        node1.addNeighbor(busMap.allRequiredNodes.get(0).getNeighbors().get(2).target, busMap.allRequiredNodes.get(0).getNeighbors().get(2).distance, busMap.allRequiredNodes.get(0).getNeighbors().get(2).path);
        System.out.println("node1:");
        for (ExtendedEdge edge : node1.getNeighbors()) {
            System.out.println(edge.getDistance() + " " + edge.getTarget().getId());
        }

        node2.addNeighbor(node1, 5);
        node2.addNeighbor(node3, 1);
        node2.addNeighbor(node4, 3);
//
        node3.addNeighbor(node1, 4);
        node3.addNeighbor(node2, 14);
        node3.addNeighbor(node4, 2);
//
        node4.addNeighbor(node1, 2);
        node4.addNeighbor(node2, 12);
        node4.addNeighbor(node3, 13);

        // Find the optimal path
        List<ExtendedNode> optimalPath1 = TwoOptAlgorithm.ShortestPathFinder.findOptimalPath(node1, graph1);

        System.out.println("extendedGraph");
        for(ExtendedNode node : graph1.getNodes()) {
            for (ExtendedEdge edge : node.getNeighbors()) {
                System.out.println(edge.getDistance() + " " + edge.getTarget().getId());
            }
            System.out.println("-----");
        }

        // Print the optimal path
        System.out.println("Optimal Path:");
        for (ExtendedNode node : optimalPath) {
            System.out.println(node.getId());
        }
    }
}
