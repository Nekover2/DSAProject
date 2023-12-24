package neko.dsa.graph;

public class Edge {
    public int weight;
    Node target;
    double distance;

    Edge(Node target, double distance) {
        this.target = target;
        this.distance = distance;
    }
}