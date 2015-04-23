import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JT on 4/21/15.
 */
public class Vertex implements Comparable<Vertex> {
    private String name;                                  // Vertex name
    private List<Edge> adjListOfEdges;                    // Adjacent edges
    private Vertex previousVertex;                        // Previous vertex on shortest path
    private float minDistance = Float.POSITIVE_INFINITY;  // Distance of path

    public Vertex(String name) {
        this.name = name;
        adjListOfEdges = new LinkedList<Edge>();
        reset();
    }

    public int compareTo(Vertex otherVertex) {
        return Float.compare(getMinDistance(), otherVertex.getMinDistance());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setMinDistance(int pathDistance) {
        this.minDistance = pathDistance;
    }

    public float getMinDistance() {
        return minDistance;
    }

    public void addEdge(Vertex vertex, float weight) {
        if(!containsEdge(vertex))
            adjListOfEdges.add(new Edge(vertex, weight));
    }

    public void removeEdge(Vertex vertex) {
        Edge edge = getEdge(vertex);
        if(edge != null)
            adjListOfEdges.remove(edge);
    }

    public Edge getEdge(Vertex destinationVertex) {
        for(int i = 0; i < adjListOfEdges.size(); i++) {
            if(adjListOfEdges.get(i).getDestinationVertex().equals(destinationVertex))
                return adjListOfEdges.get(i);
        }
        return null;
    }

    public List<Edge> getAdjListOfEdges() {
        return adjListOfEdges;
    }

    public boolean containsEdge(Vertex destinationVertex) {
        return adjListOfEdges.contains(getEdge(destinationVertex));
    }

    public void reset() {
        setMinDistance(Graph.getInfinity());
        previousVertex = null;
    }

    public void sortEdges() {
        Collections.sort(getAdjListOfEdges(), new EdgeComparator());
    }

    public void printEdges() {
        int numberOfEdges = getAdjListOfEdges().size();
        if(numberOfEdges > 0) {
            for(int i = 0; i < numberOfEdges; i++) {
                Edge edge = getAdjListOfEdges().get(i);
                String connectedVertex = edge.getDestinationVertex().getName();
                float weightOfEdge = edge.getWeight();
                System.out.println("  " + connectedVertex + " " + weightOfEdge);
            }
        } else
            System.out.println("  ...");
    }
}
