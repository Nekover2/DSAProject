package hieu.dsa.priority.app;

import java.util.ArrayList;
import java.util.List;

class Node {
    String id;
    List<Node> neighbors;
    List<Integer> distances;

    Node(String id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
        this.distances = new ArrayList<>();
    }
}
