package neko.dsa.graph;

public class Edge {
    public int weight;
    Node target;
    double distance;

    Edge(Node target, double distance) {
        this.target = target;
        this.distance = distance;
    }


    Edge(Node target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    Edge(Node target, int weight, double distance) {
        this.target = target;
        this.weight = weight;
        this.distance = distance;
    }

    public int getWeight() {
        return weight;
    }

    public double getDistance() {
        return distance;
    }

    public Node getTarget() {
        return target;
    }
}