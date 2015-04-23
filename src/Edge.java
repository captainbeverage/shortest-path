/**
 * Created by JT on 4/21/15.
 */
public class Edge {
    private Vertex destinationVertex;
    private float weight;

    public Edge(Vertex destinationVertex, float weight) {
        this.destinationVertex = destinationVertex;
        this.weight = weight;
    }

    public void setDestinationVertex(Vertex destinationVertex) {
        this.destinationVertex = destinationVertex;
    }

    public Vertex getDestinationVertex() {
        return destinationVertex;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
