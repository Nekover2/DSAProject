package neko.dsa.graph;

import java.util.List;

public class Graph {
    private List<Node> nodes;

    public Graph() {}

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }
}
