package graph;

/**
* The {@code Edge} class represents a weighted edge in a Weighted Graph and is
* a class to store edges of the weighted graph.
*
* The Edge has properties such as source node, destination node and weigth.
* Each edge consists of two integers (naming the two vertices) and a real-value weight. 
* The data type provides methods for accessing the two endpoints of the edge and
* the weight.
* 
*
* @author Diogo Miranda
* @author João Santos
* @author Tomás Maia
* @version 1.0
*/

public class Edge {
	
	private final int u;
	private final int v;
	private final int weight;
	
	/**
     * Initializes an edge between vertices {@code src} and {@code dest} of
     * the given {@code weight}.
     *
     * @param  src one vertex
     * @param  dest the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if either {@code src} or {@code dest}
     *         is a negative integer
     */
    public Edge(int u, int v, int weight) {
        if (u < 0) throw new IllegalArgumentException("Node index must be a non-negative integer");
        if (v < 0) throw new IllegalArgumentException("Node index must be a non-negative integer");
        
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    
    /**
     * Returns one endpoint of this edge.
     *
     * @return one endpoint of this edge
     */
    public int getNode() {
        return u;
    }

    /**
     * Returns the opposite endpoint of this edge.
     *
     * @return the other endpoint of this edge.
     */
    public int getOtherNode() {
        return v;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the weight of the edge
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("Edge:%d-%d | Weight: %d", u, v, weight);
    }
}

