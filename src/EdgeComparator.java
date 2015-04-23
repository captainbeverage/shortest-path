import java.util.Comparator;

/**
 * Created by JT on 4/22/15.
 */
public class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge edge1, Edge edge2) {
        String edge1Name = edge1.getDestinationVertex().getName();
        String edge2Name = edge2.getDestinationVertex().getName();
        if(edge1Name.compareTo(edge2Name) > 0)
            return 1;
        else if(edge1Name.compareTo(edge2Name) < 0)
            return -1;
        else
            return 0;
    }
}
