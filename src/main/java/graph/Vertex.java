package graph;

import UI.Point;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{
    @Override
    public String toString() {
        if(source == null){
            return "Vertex{" +
                    "index=" + index +
                    ",x=" + x +
                    ", y=" + y +
                    ", source=" + null +
                    ", cost=" + cost +
                    '}';
        }
        return "Vertex{" +
                "index=" + index +
                ",x=" + x +
                ", y=" + y +
                ", sourceIndex=" + source.getIndex() +
                ", cost=" + cost +
                '}';
    }

    private int x;
    private int y;
    private List<Edge> adjacent;

    private Point point;

    private int index;

    private Vertex source;
    private double cost;

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vertex getSource() {
        return source;
    }

    public double getCost() {
        return cost;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    private boolean isVisited;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
        adjacent = new ArrayList<>();
        isVisited = false;
        cost = Double.MAX_VALUE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Edge> getAdj() {
        return adjacent;
    }

    public void addEdge(Vertex adj){
        Edge edge = new Edge(this , adj);
        this.adjacent.add(edge);
    }


    @Override
    public int compareTo(Vertex o) {
        return Double.compare(this.getCost() , o.getCost());
    }

    public void resetPath() {
        this.isVisited = false;
        this.source = null;
        this.cost = Double.MAX_VALUE;
    }
}
