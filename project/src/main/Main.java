package main;

import graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
        
		    int command = readCommand(args);
		    
		    // Criar um metodo chamado operador para lidar com o output do comando
		    if(command == -1)
		    	return; // Exit
		    // Do -r	
		    else if(command == 0) {
		    	
	            int numNodes = Integer.parseInt(args[1]);
	            int maxWeight = Integer.parseInt(args[2]);
	            int nestNode = Integer.parseInt(args[3]);
	            double alpha = Double.parseDouble(args[4]);
	            double beta = Double.parseDouble(args[5]);
	            double delta = Double.parseDouble(args[6]);
	            double eta = Double.parseDouble(args[7]);
	            double rho = Double.parseDouble(args[8]);
	            double gamma = Double.parseDouble(args[9]);
	            int colonySize = Integer.parseInt(args[10]);
	            double tau = Double.parseDouble(args[11]);
	            
	            Graph graph = new Graph(numNodes);
	            Graph.buildGraph(graph, numNodes, maxWeight);
	            
	            // Print the graph
	            System.out.println("Generated GRAPH: ");
	            graph.printGraph();
	            
	            
		    }
		    // Do -f
		    else {
		    	String inputFile = args[1];
                int numNodes = 0; // Declaration of numNodes outside of try block
                Scanner scanner = null; // Declaration of scanner outside of try block
		    	
	        	try {
	        		
	        		File file = new File(inputFile);
	                scanner = new Scanner(file);

	                // Read the first line containing the simulation parameters
	                String parametersLine = scanner.nextLine();
	                String[] parameters = parametersLine.split("\\s+");
	                

	                numNodes = Integer.parseInt(parameters[0]);
	                int nestNode = Integer.parseInt(parameters[1]);
	                double alpha = Double.parseDouble(parameters[2]);
	                double beta = Double.parseDouble(parameters[3]);
	                double delta = Double.parseDouble(parameters[4]);
	                double eta = Double.parseDouble(parameters[5]);
	                double rho = Double.parseDouble(parameters[6]);
	                double gamma = Double.parseDouble(parameters[7]);
	                int colonySize = Integer.parseInt(parameters[8]);
	                double tau = Double.parseDouble(parameters[9]);  	
	                }
	        	catch (FileNotFoundException e) {
	        		System.out.println("Input file not found: " + inputFile);
	        		return; // Exit
	        	}
	        	catch (NumberFormatException e) {
                    System.out.println("Invalid data types for parameters!");
                    scanner.close();
                    return; // Exit
        		}

	                // Initialize the objects
	                Graph graph = new Graph(numNodes);
	                int[][] matrix = new int[numNodes][numNodes];

	                // Read the weight matrix containing the graph structure and store it in a matrix
	                for (int i = 0; i < numNodes; i++) {
	                    String[] weights = scanner.nextLine().split("\\s+");
	                    if (weights.length != numNodes) {
	                        System.out.println("Invalid structure. The graph structure doesn't follow the specified number of nodes.");
	                        scanner.close();
	                        return; // Exit
	                    }
	                	
	                    for (int j = i; j < numNodes; j++) {
	                        int weight = Integer.parseInt(weights[j]);
	                        matrix[i][j] = weight;
	                    }
	                }
	                
	                scanner.close(); // End of File
	                
	                Graph.buildGraph(graph, numNodes, matrix);
	                
	                // Print the graph
	                System.out.println("Input file GRAPH: ");
	                graph.printGraph();
	                
		    }
	}

	private static int readCommand(String[] args) {
		
		if (args.length == 0) {
            System.out.println("Invalid command. Please provide the necessary parameters.");
            return -1;
        }
		if (args[0].equals("-r")) {
            if (args.length != 12) {
                System.out.println("Invalid number of parameters for random mode.");
                return -1;
            }
            else {
            		try {
                        int param1 = Integer.parseInt(args[1]);
                        int param2 = Integer.parseInt(args[2]);
                        int param3 = Integer.parseInt(args[3]);
                        double param4 = Double.parseDouble(args[4]);
                        double param5 = Double.parseDouble(args[5]);
                        double param6 = Double.parseDouble(args[6]);
                        double param7 = Double.parseDouble(args[7]);
                        double param8 = Double.parseDouble(args[8]);
                        double param9 = Double.parseDouble(args[9]);
                        int param10 = Integer.parseInt(args[10]);
                        double param11 = Double.parseDouble(args[11]);
                        }
            		catch (NumberFormatException e) {
                        System.out.println("Invalid data types for parameters!");
                        return -1;
            		}
            	return 0;
            }
		}
		if (args[0].equals("-f")) {
        	if(args.length != 2) {
                System.out.println("Invalid command. Please provide the necessary parameters.");
                return -1;
        	}
        	else 
        		return 1;
		}
		return -1;
	}
	
}