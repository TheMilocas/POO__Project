package graph;

import java.util.*;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named 1 through V.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the degree of a vertex, the number of vertices
 *  V in the graph, and the number of edges E in the graph.
 *  
 *  This implementation uses an adjacency-lists representation.

 *
 * @author Diogo Miranda
 * @author João Santos
 * @author Tomás Maia
 * @version 1.0
 */
public class Graph {
	
	private final int nodes;
	private ArrayList<Edge>[] adjacencyList;
	
	
    /**
     * Constructs a graph with the specified number of vertices.
     *
     * @param nodes the number of vertices in the graph
     */
    public Graph(int nodes) {
        this.nodes = nodes;
        adjacencyList =  new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
       
        
    }
    
    /**
     * Adds an edge between two vertices with a given weight.
     *
     * @param source the source vertex
     * @param destination the destination vertex
     * @param weight the weight of the edge
     */
    private void addEdge(int u, int v, int weight) {
    	adjacencyList[u-1].add(new Edge(u, v, weight));
        adjacencyList[v-1].add(new Edge(v, u, weight));
    }
    
    
    /**
     * Prints the graph with its edges and weights.
     */
    public void printGraph() {
        for (int i = 1; i < nodes+1; i++) {
            System.out.print("Node " + i + " -> ");
            ArrayList<Edge> list = adjacencyList[i-1];
            for (Edge edge : list) {
                System.out.print("(" + i + "-" + edge.getOtherNode() + ":" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }
    
    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int getNumNodes() {
        return nodes;
    }
    
    public boolean hasHamiltonian() { 	
    	LinkedList<Integer> path = new LinkedList<>();
    	LinkedList<Integer> unvisitedNodes = new LinkedList<>();
    	LinkedList<Integer> visitedNodes = new LinkedList<>();
    	
    	// Fills array with all the unvisited nodes 
    	for(int i = 1; i < nodes+1; i++) {
    		unvisitedNodes.add(i);
    	}    	
    	// For convenience, we assume the path starts in node 1
    	path.add(1);
    	visitedNodes.add(1);
    	unvisitedNodes.remove(Integer.valueOf(1));
       	
    	if(findHamiltonianCycle(1, path, unvisitedNodes, visitedNodes)) {
    		System.out.println("The graph has at least one Hamiltonian cycle: " + Arrays.toString(path.toArray()));
    		return true;
    	}

    	else {
    		System.out.println("The graph has no Hamiltonian cycle.");
    		return false;		  	
    	}
    }
    	
    private boolean findHamiltonianCycle(int position, LinkedList<Integer> path, LinkedList<Integer> unvisitedNodes, LinkedList<Integer> visitedNodes) {  	
        ArrayList<Edge> list = adjacencyList[position - 1];

        if (path.size() == nodes && unvisitedNodes.isEmpty() && visitedNodes.size() == nodes) {
            for (Edge edge : list) {
                if (edge.getOtherNode() == path.getFirst()) {
                	path.add(1);
                    return true; // Hammiltonian cycle found
                }
            }
        }

        for (Edge edge : list) {
            if (unvisitedNodes.contains(edge.getOtherNode())) {
                // add the node to the path, updates the lists, and proceed with the search
                path.add(edge.getOtherNode());
                visitedNodes.add(edge.getOtherNode());
                unvisitedNodes.remove(Integer.valueOf(edge.getOtherNode()));

                if (findHamiltonianCycle(edge.getOtherNode(), path, unvisitedNodes, visitedNodes))
                    return true;
                else {
                	// Backtracking: remove the last visited node from path and visitedNodes and mark it as unvisited again
                	unvisitedNodes.add(path.getLast());
                	visitedNodes.remove(Integer.valueOf(path.getLast()));
                	path.removeLast();
                }
            }
        }
        
        return false; // No Hamiltonian cycle found
    }
    
    // Build graph for -r
	public static void buildGraph(Graph graph, int numNodes, int maxWeight) {
		 
		 ArrayList<Integer> hamiltonianCycle = new ArrayList<>(numNodes+1); 

		 for(int i = 1; i < numNodes+1; i++) {
			 hamiltonianCycle.add(i);
		 }
		 
		 Collections.shuffle(hamiltonianCycle);
		 hamiltonianCycle.add(hamiltonianCycle.get(0)); // add the first element of the list to the end to create a cycle
	       
	     int node1;
	     int node2;
	     Random random = new Random();
	     int weight;
	     
	     // add the hamiltonian cycle to the graph structure
	     for(int i = 1; i < hamiltonianCycle.size(); i++) {
	    	 node1 = hamiltonianCycle.get(i-1);
	    	 node2 = hamiltonianCycle.get(i);
	    	 weight = random.nextInt(maxWeight)+1;
	    	 graph.addEdge(node1, node2, weight);
	     }
	     
	     int numEdges = numNodes + random.nextInt((numNodes*(numNodes-1)/2) - numNodes + 1); // random number of edges of the graph
	     int viable = 0;
	     
	     for(int i = 0; i < numEdges-numNodes; i++) {
	    	 
	    	 node1 = random.nextInt(numNodes)+1;
	    	 node2 = random.nextInt(numNodes)+1;
	    	 weight = random.nextInt(maxWeight)+1;
	    	 
	    	 while(viable != 1) {
		    	 // checks if node1 is equal to node2
		    	 if(node1 == node2) {
		    		 node1 = random.nextInt(numNodes)+1;
		    		 continue;
		    	 }
		    	 
		    	 for(Edge edge : graph.adjacencyList[node1-1]) {
		    		 // checks if the edge is already in the graph
		    		 if(edge.getOtherNode() == node2) {
		    			 if(graph.adjacencyList[node1-1].size() > graph.adjacencyList[node2-1].size()) {
		    				 viable=0;
		    				 node1 = random.nextInt(numNodes)+1;
		    				 break;
		    			 }	 
		    			 else if(graph.adjacencyList[node1-1].size() == graph.adjacencyList[node2-1].size()) {
		    				 if(graph.adjacencyList[node1-1].size() == numNodes) {
		    					 viable = 0;
		    			    	 node1 = random.nextInt(numNodes)+1;
		    			    	 node2 = random.nextInt(numNodes)+1;
		    			    	 break;
		    				 }
		    				 else {
		    					 viable = 0;
		    					 node1 = random.nextInt(numNodes)+1;
		    					 break;
		    				 }

		    			 }
		    			 else {
		    				 viable = 0;
		    				 node2 = random.nextInt(numNodes)+1;
		    				 break;
		    			 }

		    		 }
		    		 // it is viable to add an edge
		    		 else viable = 1;
		    	 }		    			    	 		    	 
	    	 }
	    	 // add the edge to the graph
	    	 graph.addEdge(node1, node2, weight); 	    	 
	     }
	     
	 }
	
	 // Build graph for -f  
	 public static void buildGraph(Graph graph, int numNodes, int[][] matrix) {	
	     
		 int weight;
	     
	     // Create the adjacency lists
	     for (int i = 0; i < matrix.length; i++) { 	
	         for (int j = i; j < matrix.length; j++) {
	             weight = matrix[i][j];
	         	if (weight != 0) {
	                 graph.addEdge(i+1, j+1, weight);
	             }
	         }
	     }

	 }

}