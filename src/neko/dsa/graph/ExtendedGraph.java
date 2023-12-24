package neko.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class ExtendedGraph {
    List<ExtendedNode> nodes;

    ExtendedGraph() {
        this.nodes = new ArrayList<>();
    }

    public List<ExtendedNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ExtendedNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(ExtendedNode node) {
        nodes.add(node);
    }

    public void removeNode(ExtendedNode node) {
        nodes.remove(node);
    }

    public void addNodes(List<ExtendedNode> nodes) {
        this.nodes.addAll(nodes);
    }

}
