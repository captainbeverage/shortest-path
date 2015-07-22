import java.util.*;

/**
 * Graph class maintains a HashMap of all vertices in the graph. Executes all
 * graph commands - Add Edge, Delete Edge, Get Shortest Path, Print Reachable
 * Vertices, and Print Graph.
 *
 * @author Jordan Harris
 */
public class Graph {
    private Map<String, Vertex> vertexMap = new HashMap<String,Vertex>( );

    /**
     * Add a new vertex to the graph.
     */
    private void addVertex(Vertex vertex) {
        vertexMap.put(vertex.getName(), vertex);
    }

    /**
     * Returns a vertex by name
     */
    public Vertex getVertex(String vertexName) {
        if(vertexName.contains(vertexName))
            return vertexMap.get(vertexName);
        else
            return null;
    }

    /**
     * Creates an edge
     */
    public void addEdge(String sourceName, String destinationName, float weight) {
        Vertex sourceVertex = createVertex(sourceName);
        Vertex destinationVertex = createVertex(destinationName);
        sourceVertex.addEdge(destinationVertex, weight);
    }

    /**
     * Removes an edge
     */
    public void removeEdge(String sourceName, String destinationName) {
        Vertex sourceVertex = createVertex(sourceName);
        Vertex destinationVertex = createVertex(destinationName);
        sourceVertex.removeEdge(destinationVertex);
    }

    /**
     * Creates a vertex
     */
    private Vertex createVertex(String vertexName) {
        if(!vertexMap.containsKey(vertexName)) {
            Vertex vertex = new Vertex(vertexName);
            addVertex(vertex);
            return vertex;
        }
        return vertexMap.get(vertexName);
    }

    /**
     * Prints the graph alphabetically
     */
    public void print() {
        // Sorts the hash map with a TreeSet
        SortedSet<String> vertices = new TreeSet<String>(vertexMap.keySet());

        // Loops through the vertices and prints out each edge
        for(String vertexName: vertices) {
            System.out.println(vertexName);
            Vertex vertex = vertexMap.get(vertexName);
            vertex.sortEdges();
            vertex.printEdges();
        }
        System.out.println();
    }

    /**
     * Calculates the shortest path using Dijkstra's Algorithm. Clears all the predecessor data and shortest
     * path data currently held by each Vertex. Gets the source vertex and destination vertex from the two
     * strings entered by the user. If the vertices exist, it computes the shortest paths to all vertices from
     * the source vertex, then it finds the shortest path from the destination vertex back to the source
     * vertex.
     */
    public void findShortestPath(String sourceVertexName, String destinationVertexName) {
        // Clears the predecessors and minimum path distance currently held with each vertex
        clearAll();

        // Gets the two vertices from the strings given
        Vertex sourceVertex = getVertex(sourceVertexName);
        Vertex destinationVertex = getVertex(destinationVertexName);

        // If either of the vertices do not exist, no path exists
        if(sourceVertex == null || destinationVertex == null)
            System.out.println("No path exists from " + sourceVertexName + " to " + destinationVertexName);
        else {
            // Computes the minimum paths to all vertices from the source vertex, then gets the shortest path
            // to the destination vertex and adds each vertex to a list. That list is then printed.
            Dijkstra.computePaths(sourceVertex);
            List<Vertex> path = Dijkstra.getShortestPathTo(destinationVertex);
            printPath(path, destinationVertex);
            System.out.println();
        }
    }

    /**
     * Prints the shortest path by printing the name of each vertex in the path list, then printing the
     * minimum path distance to the destination vertex
     */
    private void printPath(List<Vertex> path, Vertex destinationVertex) {
        for(int i = 0; i < path.size(); i++)
            System.out.print(path.get(i).getName() + " ");
        System.out.println(destinationVertex.getMinDistance());
    }

    /**
     * Calculates all reachable vertices using Breadth First Search
     */
    public void reachable() {
        // Gets a sorted set of all the vertices in the graph
        SortedSet<String> vertices = new TreeSet<String>(vertexMap.keySet());

        // Loops through all the vertices in the graph
        for(String vertexName: vertices) {
            Queue<Vertex> queue = new LinkedList<Vertex>();       // Holds a queue for running BFS
            List<Edge> reachableList = new LinkedList<Edge>();  // Holds a list of all the reachable vertices
            Vertex vertex = getVertex(vertexName);           // Gets the current vertex

            // If the vertex is null, returns
            if(vertex == null)
                return;

            // Sets visited to true for the vertex and adds the vertex to the end of the queue
            vertex.setVisited();
            queue.add(vertex);

            // Runs BFS to find every vertex that is reachable from the current vertex
            findReachable(queue, reachableList);

            // Prints out the name of the current vertex
            System.out.println(vertexName);

            // Prints all the reachable vertices
            printReachable(reachableList);

            // Clears the reachable data for the current vertex in order to run the algorithm on the
            // next vertex
            clearAll();
        }
        System.out.println();
    }

    /**
     * Runs BFS on the graph for a given vertex and adds each found vertex to the reachable list. Marks
     * all visited vertices as visited.
     */
    private void findReachable(Queue<Vertex> queue, List<Edge> reachableList) {
        // While the queue isn't empty (there are still vertices to visit)
        while(!queue.isEmpty()) {
            // Removes the vertex from the front of the queue
            Vertex r = queue.remove();

            // Loops over all of the adjacent edges of the current vertex
            for(Edge n: r.getAdjListOfEdges()) {
                // If the vertex hasn't been visited, it adds it to the queue and the reachable list,
                // and marks the vertex as visited
                if(!n.getDestinationVertex().visited()) {
                    queue.add(n.getDestinationVertex());
                    n.getDestinationVertex().setVisited();
                    reachableList.add(n);
                }
            }
        }
    }

    /**
     * Sorts the reachable list, then loops through it and prints all of the verties in the list
     */
    private void printReachable(List<Edge> reachableList) {
        Collections.sort(reachableList, new EdgeComparator());
        for(int i = 0; i < reachableList.size(); i++)
            System.out.println("  " + reachableList.get(i).getDestinationVertex().getName());
    }

    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll() {
        for(Vertex vertex : vertexMap.values())
            vertex.reset();
    }
}
