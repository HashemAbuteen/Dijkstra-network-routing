# dijkstra-network-routing

* This project implements Dijkstra’s algorithm to find paths through a graph representing a network routing problem.

* generates a graph/network with a specified number of points, for a provided random seed.

* When you hit “Generate” the framework for this project generates a random set of nodes, V, each with 3 randomly selected edges to other nodes. 
  The edges are directed and the edge cost is the Euclidean distance between the nodes. Thus, all nodes will have an out-degree of 3, but 
  no predictable value for in-degree.
  ![image](https://user-images.githubusercontent.com/88089822/174052108-7f1e97fc-5935-4065-bc05-ba96506f3961.png)
  
* Shoe Edges check box drwas all the edges in the graph.

* After generating, clicking on a node (or entering its index in the appropriate box) will highlight the source in green,
  and clicking another (or, again entering its index in the box) will highlight the destination in red. Each click alternates between the two.

* After these nodes are selected you can hit “Compute”, it draws the shortest path starting from the source node 
  and following all intermediate nodes until the destination node.

* In the "Total Path Cost" box, display the total path cost. If the destination cannot be reached “unreachable” will be printedt in the total cost box.

 ![image](https://user-images.githubusercontent.com/88089822/174052237-2185a664-4bc7-44a6-8bc7-89629c44883f.png)
 
 # Build & Run
 To Build and run the app use the following commands
 
<code>mvn compile</code>

<code>mvn package</code>

<code>java -jar .\target\network-routing-1.0-SNAPSHOT.jar</code>
