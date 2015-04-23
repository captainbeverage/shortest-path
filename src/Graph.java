import java.util.*;

/**
 * Created by JT on 4/21/15.
 */
public class Graph {
    private static final int INFINITY = Integer.MAX_VALUE;
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
        return vertexMap.get(vertexName);
    }

    /**
     * Returns the vertex map
     */
    public Map<String, Vertex> getVertexMap() {
        return vertexMap;
    }

    /**
     * Creates an edge
     */
    public void addEdge(String sourceName, String destinationName, float weight) {
        Vertex sourceVertex = createVertex(sourceName);
        Vertex destinationVertex = createVertex(destinationName);
        sourceVertex.addEdge(destinationVertex, weight);
        destinationVertex.addEdge(sourceVertex, weight);
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
     * Returns infinity
     */
    public static int getInfinity() {
        return INFINITY;
    }

    /**
     * Prints the graph
     */
    public void print() {
        SortedSet<String> vertices = new TreeSet<String>(vertexMap.keySet());
        for(String vertexName : vertices) {
            System.out.println(vertexName);
            Vertex vertex = vertexMap.get(vertexName);
            vertex.sortEdges();
            vertex.printEdges();
        }
    }

    /**
     * Driver routine to print total distance.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath(String destinationName) {
        Vertex vertex = vertexMap.get(destinationName);
        if(vertex == null)
            throw new NoSuchElementException("Destination vertex not found");
        else if(vertex.getPathDistance() == INFINITY)
            System.out.println(destinationName + " is unreachable");
        else{
            System.out.print("(Distance is: " + vertex.getPathDistance() + ") ");
            printPath(vertex);
            System.out.println( );
        }
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath(Vertex destination) {
        if(destination.getPreviousVertex() != null) {
            printPath(destination.getPreviousVertex());
            System.out.print(" to ");
        }
        System.out.print(destination.getName());
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
