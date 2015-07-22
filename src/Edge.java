/**
 * Edge Class stores destination vertex and weight of edge.
 *
 * @author Jordan Harris
 */
public class Edge {
    private Vertex destinationVertex;
    private float weight;

    /**
     * Constructor initializes destination vertex and weight of edge.
     */
    public Edge(Vertex destinationVertex, float weight) {
        this.destinationVertex = destinationVertex;
        this.weight = weight;
    }

    /**
     * Returns the destination vertex
     */
    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    /**
     * Sets the weight of the destination vertex
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Returns the weight of the edge
     */
    public float getWeight() {
        return weight;
    }
}
