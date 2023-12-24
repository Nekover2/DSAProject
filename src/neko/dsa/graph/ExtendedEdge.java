package neko.dsa.graph;

import java.util.List;

public class ExtendedEdge {
    public int weight;
    ExtendedNode target;
    double distance;

    List<String> path;

    ExtendedEdge(ExtendedNode target, double distance) {
        this.target = target;
        this.distance = distance;
    }

    ExtendedEdge(ExtendedNode target, double distance, List<String> path) {
        this.target = target;
        this.distance = distance;
        this.path = path;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public ExtendedNode getTarget() {
        return target;
    }

    public void setTarget(ExtendedNode target) {
        this.target = target;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


}