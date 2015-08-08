# shortest-path

### Overview
This Java program constructs a graph based on nodes with weighted edges. You can add edges, remove edges, find the shortest path from one node to another (uding Dijkstra's Algorithm), and print the graph.

*** How to install
```git clone https://github.com/jtharris-dev/shortest-path.git```
Compile and run in any IDE for Java

### Initial Instructions
 - When you first run the program, it constructs an initial graph based on an input file. An example file has been provided: input.txt. Each line in the file has a pair of nodes with a value to indicate the weight of the edge between them. Simply edit this file, or make your own. When the program begins, it will ask you for the input file. Simply enter the filename you would like to use.

### Commands
Add an edge with two vertices and an edge weight
```addedge <vertex1> <vertex2> <weight>```

Remove an edge
```deleteedge <vertex1> <vertex2>```

Find the shortest path from one vertex to another
```path <starting vertex> <ending vertex>```

Print the graph
```print```

Print every node, and all of the nodes that that node can reach
```reachable```

Quit the program
```quit```