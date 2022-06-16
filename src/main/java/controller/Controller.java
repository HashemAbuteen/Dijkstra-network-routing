package controller;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import UI.Arrow;
import UI.Point;
import UI.PointClickHandler;
import dijkstra.Dijkstra;
import graph.Graph;
import graph.Vertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import javax.swing.*;

import static javafx.application.Platform.exit;

public class Controller {
    public static TextField source;
    public static TextField target;

    public static Point sourcePoint;
    public static Point destinationPoint;

    private Graph graph;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private CheckBox ShowEdges;
    @FXML
    private Pane NetworkPane;

    @FXML
    private TextField SeedTF;

    @FXML
    private TextField SizeTF;

    @FXML
    private Button GenerateBtn;

    @FXML
    private TextField SourceNodeTF;

    @FXML
    private TextField TargetNodeTF;

    @FXML
    private Button ComputeCostBtn;

    @FXML
    private TextField TotalPathCostTF;

    @FXML
    void ComputeCost(ActionEvent event) {
        int size = Integer.parseInt(SizeTF.getText());
        NetworkPane.getChildren().clear();
        for (int i = 0; i < size; i++) {
            Point point = graph.getVertices().get(i).getPoint();
            NetworkPane.getChildren().add(point);
        }
        selectShowEdges(event);

        Vertex source = sourcePoint.getVertex();
        Vertex target = destinationPoint.getVertex();
        graph.resetPath();
        Dijkstra.compute(source, target);


        System.out.println(graph);
        System.out.println(Dijkstra.getPath(source, target));

        List<Vertex> path = Dijkstra.getPath(source, target);


        if (path != null) {
            for (int i = 0; i < path.size() - 1; i++) {
                Arrow arrow = new Arrow(path.get(i).getX(), path.get(i).getY(), path.get(i + 1).getX(), path.get(i + 1).getY());

                arrow.getMainLine().setStroke(Color.CYAN);
                arrow.getMainLine().setStrokeWidth(5.0f);
                arrow.getHeadB().setStroke(Color.CYAN);
                Label l = new Label();
                l.setText((int) path.get(i + 1).getPoint().getVertex().getCost() + "");
                l.setLayoutX(arrow.getX2());
                l.setLayoutY(arrow.getY2());
                arrow.setHeadAVisible(false);
                NetworkPane.getChildren().add(l);

                NetworkPane.getChildren().addAll(arrow);

            }

            TotalPathCostTF.setText((int) path.get(path.size() - 1).getCost() + "");
        } else {
            TotalPathCostTF.setText("Unreachable");
        }
    }


    @FXML
    void Generate(ActionEvent event) {
        NetworkPane.getChildren().clear();

        //get seed and size from the GUI
        int seed = Integer.parseInt(SeedTF.getText());
        int size = Integer.parseInt(SizeTF.getText());

        if (size > (int)(NetworkPane.getWidth()*NetworkPane.getHeight())/36) {
            Component f = null;
            JOptionPane.showMessageDialog(f,"too much network routers the size should be less then ="+(NetworkPane.getWidth()*NetworkPane.getHeight())/36);
            exit();
        }
        PointClickHandler pointClickHandler = new PointClickHandler();

        //make the graph
        graph = new Graph(seed, size, pointClickHandler);

        //Add all points in the graph to the UI
        for (int i = 0; i < size; i++) {
            Point point = graph.getVertices().get(i).getPoint();
            NetworkPane.getChildren().add(point);
        }


    }

    @FXML
    void selectShowEdges(ActionEvent event) {
        int size = Integer.parseInt(SizeTF.getText());
        if (ShowEdges.isSelected()) {
            for (int i = 0; i < size; i++) {
                Vertex vertex = graph.getVertices().get(i);

                for (int j = 0; j < vertex.getAdj().size(); j++) {
                    Vertex adj = vertex.getAdj().get(j).getDestination();
                    Arrow arrow = new Arrow(vertex.getX(), vertex.getY(), adj.getX(), adj.getY());
                    arrow.setHeadAVisible(false);
                    NetworkPane.getChildren().add(arrow);
                }

            }
        } else {
            NetworkPane.getChildren().clear();
            for (int i = 0; i < size; i++) {
                Point point = graph.getVertices().get(i).getPoint();
                NetworkPane.getChildren().add(point);
            }
        }
    }

    @FXML
    void setSourceNode(ActionEvent event) {
        Point point = graph.getVertices().get(Integer.parseInt(SourceNodeTF.getText())).getPoint();
        point.setAsSource();

    }

    @FXML
    void setTargetNode(ActionEvent event) {
        Point point = graph.getVertices().get(Integer.parseInt(TargetNodeTF.getText())).getPoint();
        point.setAsTarget();
    }

    @FXML
    void initialize() {
        assert NetworkPane != null : "fx:id=\"NetworkPane\" was not injected: check your FXML file 'main.fxml'.";
        assert SeedTF != null : "fx:id=\"SeedTF\" was not injected: check your FXML file 'main.fxml'.";
        assert SizeTF != null : "fx:id=\"SizeTF\" was not injected: check your FXML file 'main.fxml'.";
        assert GenerateBtn != null : "fx:id=\"GenerateBtn\" was not injected: check your FXML file 'main.fxml'.";
        assert SourceNodeTF != null : "fx:id=\"SourceNodeTF\" was not injected: check your FXML file 'main.fxml'.";
        assert TargetNodeTF != null : "fx:id=\"TargetNodeTF\" was not injected: check your FXML file 'main.fxml'.";
        assert ComputeCostBtn != null : "fx:id=\"ComputeCostBtn\" was not injected: check your FXML file 'main.fxml'.";
        assert TotalPathCostTF != null : "fx:id=\"TotalPathCostTF\" was not injected: check your FXML file 'main.fxml'.";

        target = TargetNodeTF;
        source = SourceNodeTF;
    }
}
