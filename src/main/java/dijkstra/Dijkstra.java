package dijkstra;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void compute(Vertex source, Vertex target) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        source.setCost(0);
        queue.add(source);
        while (!queue.isEmpty() && !target.isVisited()){
            Vertex vertex = queue.poll();
            vertex.setVisited(true);
            for(int i = 0 ; i < vertex.getAdj().size() ; i++){
                Vertex adj = vertex.getAdj().get(i).getDestination();
                if(!adj.isVisited()){
                    double cost = vertex.getAdj().get(i).getDistance() + vertex.getCost();
                    if(cost < adj.getCost()){
                        adj.setCost(cost);
                        adj.setSource(vertex);
                        if(queue.contains(adj)){
                            queue.remove(adj);
                        }
                        queue.add(adj);
                    }
                }
            }
        }

    }

    public static List<Vertex> getPath(Vertex source , Vertex target ){
        if(target.getSource() == null) {
            return null;
        }
        List<Vertex> path =  new ArrayList<>();
        path.add(target);
        Vertex vertex = target;
        while (vertex.getSource() != null){
            vertex = vertex.getSource();
            path.add(vertex);
        }
        List<Vertex> path2 =  new ArrayList<>();
        for(int i = path.size()-1 ; i >=0 ; i --) {
            path2.add(path.get(i));
        }
        return path2;
    }

}
