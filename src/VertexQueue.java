import java.util.ArrayList;

/**
 * Priority Queue of Vertices realized through a Min Heap
 *
 * @author Jordan Harris
 */
public class VertexQueue {
    private ArrayList<Vertex> vertexQueue;  // ArrayList holds the min heap

    /**
     * Constructor creates the priority queue
     */
    public VertexQueue() {
        vertexQueue = new ArrayList<Vertex>();
    }

    /**
     * Returns the size of the priority queue
     */
    public int getSize() {
        return vertexQueue.size();
    }

    /**
     * Removes and returns the minimum (head) value of the priority queue, then re-sorts
     * the queue.
     */
    public Vertex extractMin() {
        // Returns null if the size is less than or equal to 0
        if (vertexQueue.size() <= 0)
            return null;

        // Gets the minimum value, resorts the queue, then returns the value
        Vertex minValue = vertexQueue.get(0);
        vertexQueue.set(0, vertexQueue.get(vertexQueue.size() - 1));
        vertexQueue.remove(vertexQueue.size() - 1);
        minHeapify(0);
        return minValue;
    }

    /**
     * Adds a vertex to the queue, then re-orders the queue
     */
    public void add(Vertex vertex) {
        vertexQueue.add(vertex);
        minHeapify(0);
    }

    /**
     * Removes a vertex from the queue, then re-orders the queue
     */
    public void remove(Vertex vertex) {
        vertexQueue.remove(vertex);
        minHeapify(0);
    }

    /**
     * Orders the queue, starting from the bottom leaf, then working up to the root node
     */
    public void minHeapify(int index) {
        int parent = index;              // Parent node
        int leftChild = 2 * index + 1;   // Left child node
        int rightChild = 2 * index + 2;  // Right child node

        // If the loops hasn't gone outside the queue, then the parent node becomes the lesser
        // of the left child and the right child. The parent is swapped with the current index,
        // and the re-ordering is run recursively on the new parent.
        if (leftChild < vertexQueue.size() && lessThan(leftChild, parent))
            parent = leftChild;
        if (rightChild < vertexQueue.size() && lessThan(rightChild, parent))
            parent = rightChild;
        if (parent != index) {
            swap(parent, index);
            minHeapify(parent);
        }
    }

    /**
     * Swaps the parent node with the current index node
     */
    private void swap(int parent, int index) {
        Vertex tempParent = vertexQueue.get(parent);
        vertexQueue.set(parent, vertexQueue.get(index));
        vertexQueue.set(index, tempParent);
    }

    /**
     * Returns true if the child's minimum path distance is less than the parent's minumum
     * path distance.
     */
    public boolean lessThan(int child, int parent) {
        return vertexQueue.get(child).compareTo(vertexQueue.get(parent)) < 0;
    }
}
