package graph;

import UI.Point;
import UI.PointClickHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    private List<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public Graph(int seed, int size, PointClickHandler pointClickHandler) {
        vertices = new ArrayList<>();
        Random random = new Random(seed);

        for (int i = 0; i < size; i++) {
            Vertex vertex = new Vertex(random.nextInt(820), random.nextInt(465));
            this.addVertix(vertex);
            vertex.setIndex(i);

            //make a GUI point for the vertex
            Point point = new Point(vertex);
            point.addEventHandler(MouseEvent.MOUSE_CLICKED, pointClickHandler);
            vertex.setPoint(point);

        }
        for (int i = 0; i < size; i++) {
            Vertex vertex = vertices.get(i);
            List<Integer> adjacent = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int edgeIndex = random.nextInt(size);
                //make sure the destination vertex is not the same as the source
                //make sure it does not repeat the same adjacent twice
                while (edgeIndex == i || adjacent.contains(edgeIndex)) {
                    edgeIndex = random.nextInt(size);
                }
                adjacent.add(edgeIndex);
                vertex.addEdge(vertices.get(edgeIndex));
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void addVertix(Vertex vertex){
        this.vertices.add(vertex);
    }

    public void resetPath(){
        for(int i = 0 ; i < vertices.size() ; i++){
            Vertex vertex = vertices.get(i);
            vertex.resetPath();
        }
    }

    @Override
    public String toString() {
        String s =  "Graph:\n";
        for(int i = 0; i < vertices.size() ; i++){
            s += i+" :\n";
            Vertex vertex = vertices.get(i);
            for(int j = 0 ; j < vertex.getAdj().size() ; j++){
                Edge edge = vertex.getAdj().get(j);
                s += "to: " + edge.getDestination().getIndex() + " at cost: " + edge.getDistance() +"\n";
            }
        }
        return s;
    }
}
