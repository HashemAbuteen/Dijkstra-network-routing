package UI;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PointClickHandler implements EventHandler<MouseEvent> {
    Boolean source = true;



    @Override
    public void handle(MouseEvent event) {
        Point point = (Point) event.getSource();
        if(source) {
            point.setAsSource();
            Controller.source.setText(point.getVertex().getIndex()+"");

        }
        else {
            point.setAsTarget();
            Controller.target.setText(point.getVertex().getIndex()+"");
        }
        source = !source;
    }
}
