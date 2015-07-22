import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Vertex contains a vertex name, list of adjacent edges, predecessor vertex,
 *
 * @author Jordan Harris
 */
public class Vertex implements Comparable<Vertex> {
    private String name;                                  // Vertex name
    private List<Edge> adjListOfEdges;                    // Adjacent edges
    private Vertex previousVertex;                        // Previous vertex on shortest path
    private float minDistance = Float.POSITIVE_INFINITY;  // Minimum distance of path
    private boolean visited;                              // Vertex has been visited

    /**
     * Constructor initializes the name, creates a new list of adjacent edges and resets
     * the previous vertex to null, minimum distance of path to infinity, and visited to
     * false.
     */
    public Vertex(String name) {
        this.name = name;
        adjListOfEdges = new LinkedList<Edge>();
        reset();
    }

    /**
     * Required for Comparable interface, which allows ordering of vertices when comparing the distance of two
     * vertices, for use with Dijkstra's Algorithm.
     */
    public int compareTo(Vertex otherVertex) {
        return Float.compare(getMinDistance(), otherVertex.getMinDistance());
    }

    /**
     * Returns the name of the vertex
     */
    public String getName() {
        return name;
    }

    /**
     * Returns if the vertex has been visited
     */
    public boolean visited() {
        return visited;
    }

    /**
     * Sets the visited property to true
     */
    public void setVisited() {
        visited = true;
    }

    /**
     * Sets the predecessor vertex of the current vertex
     */
    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    /**
     * Returns the predecessor vertex of the current vertex
     */
    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    /**
     * Sets the minimum path distance for the current vertex pack to the source vertex
     */
    public void setMinDistance(float pathDistance) {
        this.minDistance = pathDistance;
    }

    /**
     * Returns the minimum path distance for the current vertex back to the source vertex
     */
    public float getMinDistance() {
        return minDistance;
    }

    /**
     * If the vertex does not contain an edge with the new vertex, it adds the edge to the
     * adjacency list of edges. If the edge already exists, the weight is set to the given weight.
     */
    public void addEdge(Vertex vertex, float weight) {
        if(!containsEdge(vertex))
            adjListOfEdges.add(new Edge(vertex, weight));
        else
            getEdge(vertex).setWeight(weight);
    }

    /**
     * If the edge exists, the edge is removed from the adjacency list
     */
    public void removeEdge(Vertex vertex) {
        Edge edge = getEdge(vertex);
        if(edge != null)
            adjListOfEdges.remove(edge);
    }

    /**
     * Searches through the list of edges for the given vertex. If the edge is found, it is
     * returned. Otherwise, null is returned.
     */
    public Edge getEdge(Vertex destinationVertex) {
        for(int i = 0; i < adjListOfEdges.size(); i++) {
            if(adjListOfEdges.get(i).getDestinationVertex().equals(destinationVertex))
                return adjListOfEdges.get(i);
        }
        return null;
    }

    /**
     * Returns the adjacency list of edges
     */
    public List<Edge> getAdjListOfEdges() {
        return adjListOfEdges;
    }

    /**
     * Returns if the adjacency list of edges contains a given edge
     */
    public boolean containsEdge(Vertex destinationVertex) {
        return adjListOfEdges.contains(getEdge(destinationVertex));
    }

    /**
     * Resets the minimum path distance to infinity, predecessor vertex to null,
     * and visited to false.
     */
    public void reset() {
        setMinDistance(Float.POSITIVE_INFINITY);
        previousVertex = null;
        visited = false;
    }

    /**
     * Sorts the edges by name using the Edge Comparator class
     */
    public void sortEdges() {
        Collections.sort(getAdjListOfEdges(), new EdgeComparator());
    }

    /**
     * Loops through the adjacency list of edges and prints the name of each destination vertex
     * along with the weight of the edge.
     */
    public void printEdges() {
        int numberOfEdges = getAdjListOfEdges().size();
        for(int i = 0; i < numberOfEdges; i++) {
            Edge edge = getAdjListOfEdges().get(i);
            String connectedVertex = edge.getDestinationVertex().getName();
            float weightOfEdge = edge.getWeight();
            System.out.println("  " + connectedVertex + " " + weightOfEdge);
        }
    }
}
