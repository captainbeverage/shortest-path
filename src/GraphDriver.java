import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Driver for the Shortest Path Graph program.
 *
 * @author Jordan Harris
 */
public class GraphDriver {
    public static void main(String [] args) throws IOException {
        // Create a graph
        Graph graph = new Graph();

        // Create a graph file and build a graph from that file
        Scanner graphFile = generateGraphFile();
        buildGraphFromFile(graph, graphFile);

        // Run graph commands
        graphCommands(graph);
    }

    /**
     * Gets user input to run graph commands
     */
    public static void graphCommands(Graph graph) {
        // Gets user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter command: ");
        String command = input.nextLine();

        // Continues to get input from the user until the user enters 'exit'
        while(!command.equals("exit")) {
            processCommand(graph, command);
            System.out.print("Enter command: ");
            command = input.nextLine();
        }
        System.out.println("Exiting program...");
    }

    /**
     * Pareses the user's input, then executes the graph function
     */
    private static void processCommand(Graph graph, String command) {
        // Gets tokens from the input string in order to parse input
        StringTokenizer string = new StringTokenizer(command);
        String function = string.nextToken();

        // Checks the parsed input to execute graph commands
        if(function.equals("print"))
            graph.print();
        else if(function.equals("reachable"))
            graph.reachable();
        else if(string.countTokens() >= 2) {
            String firstName = string.nextToken();
            String secondName = string.nextToken();
            if(function.equals("path"))
                graph.findShortestPath(firstName, secondName);
            else if(function.equals("addedge")) {
                try {
                    float weight = Float.parseFloat(string.nextToken());
                    graph.addEdge(firstName, secondName, weight);
                    System.out.println("Edge added");
                } catch(NoSuchElementException e) {
                    System.out.println("Need to add edge weight");
                }
            } else if(function.equals("deleteedge")) {
                graph.removeEdge(firstName, secondName);
                System.out.println("Edge removed");
            }
            else
                System.out.println("Bad command");
        } else
            System.out.println("Bad command");
    }

    /**
     * Generates a graph file
     */
    public static Scanner generateGraphFile() throws IOException {
        Scanner input = new Scanner(System.in);
        String filename = getFilename(input);
        FileReader file = new FileReader(filename);
        return new Scanner(file);
    }

    /**
     * Gets the filename to read from the user and checks to see if it exists
     */
    public static String getFilename(Scanner input) throws IOException {
        System.out.print("Enter the name of the file to read graph data: ");
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

    /**
     * Reads each edge from the file then adds that edge to the graph
     */
    public static void buildGraphFromFile(Graph graph, Scanner graphFile) {
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
                graph.addEdge(destinationVertexName, sourceVertexName, weight);
            }
            catch(NumberFormatException exception) {
                System.err.println("Skipping ill-formatted line " + line);
            }
        }
        System.out.println("File read...");
        System.out.println("Graph built...");
    }
}
