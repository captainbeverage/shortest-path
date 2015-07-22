import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Runs Dijkstra's Algorithm to compute the minimum weight path from a source vertex to a
 * destination vertex. Uses the computePaths method to calculate all the minimum paths from
 * the source vertex to all possible destination vertices. Once that has been completed, the
 * getShortestPathTo method can be run to calculate backwards from the destination vertex to
 * the source vertex, the minimum path.
 *
 * @author Jordan Harris
 */
public class Dijkstra {
    /**
     * Computes the minimum path distance from the source vertex to all destination vertices.
     */
    public static void computePaths(Vertex sourceVertex) {
        // Sets the minimum distance to the source vertex as 0
        sourceVertex.setMinDistance(0);

        // Creates a new priority queue using the VertexQueue class
        VertexQueue vertexQueue = new VertexQueue();

        // Adds the source vertex to the priority queue
        vertexQueue.add(sourceVertex);

        // While the queue is not empty, it continues to find more edges
        while(vertexQueue.getSize() != 0) {
            // Extracts the minimum value from the queue
            Vertex u = vertexQueue.extractMin();

            // Loops over every edge in the adjacency list of edges
            for(Edge edge: u.getAdjListOfEdges()) {
                // Gets the destination vertex
                Vertex v = edge.getDestinationVertex();

                // Gets the current distance through u
                float distanceThroughU = u.getMinDistance() + edge.getWeight();

                // If the distance through u is less than the current minimum distance,
                // the minimum distance is reset to u. The priority queue is updated
                // to re-order the new values
                if(distanceThroughU < v.getMinDistance()) {
                    vertexQueue.remove(v);
                    v.setMinDistance(distanceThroughU);
                    v.setPreviousVertex(u);
                    vertexQueue.add(v);
                }
            }
        }
    }

    /**
     * Calculates and returns the shortest path from a source vertex to a destination vertex
     * once all the shortest paths have been calculated for the source vertex.
     */
    public static List<Vertex> getShortestPathTo(Vertex destinationVertex) {
        List<Vertex> path = new ArrayList<Vertex>();  // List to hold the path

        // Loops through the parent vertices of the destination vertex and adds each vertex to
        // the path
        for(Vertex vertex = destinationVertex; vertex != null; vertex = vertex.getPreviousVertex())
            path.add(vertex);

        // Reverses the order of the path to show from source to destination
        Collections.reverse(path);
        return path;
    }
}
