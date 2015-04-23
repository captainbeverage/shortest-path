import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by JT on 4/21/15.
 */
public class GraphDriver {
    public static void main(String [] args) throws IOException {
        // Create a graph
        Graph graph = new Graph();

        // Create a graph file
        Scanner graphFile = generateGraphFile();

        // Build graph from file
        buildGraphFromFile(graph, graphFile);

        // Add and remove some edges
        graph.addEdge("Cool", "Thing", 4);
        graph.addEdge("Cool", "Duke", 4);
        graph.addEdge("Cool", "Duke", 5);
        graph.addEdge("Woodward", "Thing", 4);
        graph.removeEdge("Health", "Belk");
        graph.removeEdge("Health", "Education");
        graph.removeEdge("Health", "Woodward");

        // Print graph
        graph.print();
    }

    // Generates a graph file
    public static Scanner generateGraphFile() throws IOException {
        Scanner input = new Scanner(System.in);
        String filename = getFilename(input);
        FileReader file = new FileReader(filename);
        return new Scanner(file);
    }

    // Gets the filename to read from the user and checks to see if it exists
    public static String getFilename(Scanner input) throws IOException {
        System.out.print("Enter the name of the file to read: ");
        String inputFilename = input.nextLine();
        File file = new File(inputFilename);
        while(!file.exists()) {
            System.out.println("Error... File not found.");
            System.out.print("Enter the name of the file to read: ");
            inputFilename = input.nextLine();
            file = new File(inputFilename);
        }
        return inputFilename;
    }

    public static void buildGraphFromFile(Graph graph, Scanner graphFile) {
        // Read the edges and insert
        String line;
        while(graphFile.hasNextLine()) {
            line = graphFile.nextLine();
            StringTokenizer string = new StringTokenizer(line);

            try {
                if(string.countTokens() != 3 ) {
                    System.err.println("Skipping ill-formatted line " + line);
                    continue;
                }
                String sourceVertexName = string.nextToken();
                String destinationVertexName = string.nextToken();
                float weight = Float.parseFloat(string.nextToken());
                graph.addEdge(sourceVertexName, destinationVertexName, weight);
            }
            catch(NumberFormatException exception) {
                System.err.println("Skipping ill-formatted line " + line);
            }
        }
        System.out.println("File read...");
    }

    /**
     * Process a request; return false if end of file.
     */
    public static boolean processRequest(Scanner in, Graph g) {
        try {
            System.out.print( "Enter start node: " );
            String startName = in.nextLine( );

            System.out.print( "Enter destination node: " );
            String destName = in.nextLine( );

            g.printPath( destName );
        }
        catch(NoSuchElementException e) {
            return false;
        }
        catch(GraphException e) {
            System.err.println( e );
        }
        return true;
    }
}
