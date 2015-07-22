import java.util.Comparator;

/**
 * Edge Comparator compares the names of the destination vertices in 2 given edges
 * in order to sort them alphabetically.
 *
 * @author Jordan Harris
 */
public class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge edge1, Edge edge2) {
        // Gets the names of the destination vertices in the 2 edges
        String edge1Name = edge1.getDestinationVertex().getName();
        String edge2Name = edge2.getDestinationVertex().getName();

        // Compares the names
        if (edge1Name.compareTo(edge2Name) > 0)
            return 1;
        else if(edge1Name.compareTo(edge2Name) < 0)
            return -1;
        else
            return 0;
    }
}
