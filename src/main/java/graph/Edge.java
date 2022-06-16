package graph;

public class Edge {

    private Vertex src;
    private Vertex destination ;

    private double distance ;


    protected Edge(Vertex src, Vertex destination) {
        this.src = src;
        this.destination = destination;
        distance =Math.sqrt(Math.pow(src.getX() - destination.getX(), 2.0) + Math.pow(src.getY() - destination.getY(), 2.0));

    }

    public Vertex getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }
}
