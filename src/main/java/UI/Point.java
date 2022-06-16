package UI;

import graph.Vertex;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static controller.Controller.destinationPoint;
import static controller.Controller.sourcePoint;

public class Point extends Circle {

    private Vertex vertex;

    public Vertex getVertex() {
        return vertex;
    }

    public Point(Vertex vertex) {
        super(vertex.getX() + 3, vertex.getY() + 3, 3);
        this.vertex = vertex;
    }

    public void setAsSource(){
        if(sourcePoint != null){
            sourcePoint.setDefault();

        }
        sourcePoint = this;
        this.setFill(Color.GREEN);
        this.setRadius(5);
    }
    public void setAsTarget(){
        if(destinationPoint != null){
          destinationPoint.setDefault();
        }
        destinationPoint = this;
        this.setFill(Color.RED);
        this.setRadius(5);
    }

    private void setDefault() {
        this.setFill(Color.BLACK);
        this.setRadius(3);

    }
}
