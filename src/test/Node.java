package test;

class Node {
    private int id;
    private double[] coordinates;

    public Node(int id, double[] coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}

