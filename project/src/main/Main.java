package main;

import graph.Edge;

import graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
        if (args.length == 0) {
            System.out.println("Invalid command. Please provide the necessary parameters.");
            return;
        }

        
        if (args[0].equals("-r")) {
            if (args.length != 12) {
                System.out.println("Invalid number of parameters for random mode.");
                return;
            }

            int numNodes = Integer.parseInt(args[1]);
            int maxWeight = Integer.parseInt(args[2]);
            int n1 = Integer.parseInt(args[3]);
            double alpha = Double.parseDouble(args[4]);
            double beta = Double.parseDouble(args[5]);
            double delta = Double.parseDouble(args[6]);
            double eta = Double.parseDouble(args[7]);
            double rho = Double.parseDouble(args[8]);
            double gamma = Double.parseDouble(args[9]);
            int colonySize = Integer.parseInt(args[10]);
            double tau = Double.parseDouble(args[11]);
            
            // POR FAZER
            
            Graph graph = new Graph(numNodes);
            Random random = new Random();
            
            // Random weight matrix
            int[][] matrix = new int[numNodes][numNodes];            
            
            for (int i = 0; i < numNodes; i++) {
                for (int j = i; j < numNodes; j++) {
                	if (i == j)
                		matrix[i][j] = 0;
                	else
                		matrix[i][j] = random.nextInt(maxWeight+1);
                }
            }
            
            // Trocar isto por metodos
            // Create the adjacency lists
            int weight;
            
            for (int i = 0; i < matrix.length; i++) { 	
                for (int j = i; j < matrix.length; j++) {
                    weight = matrix[i][j];
                	if (weight != 0) {
                        graph.addEdge(i+1, j+1, weight);
                    }
                }
            }
            
            // Print the graph
            System.out.println("Generated GRAPH: ");
            graph.printGraph();
            
            graph.hasHamiltonian(); // Has hamiltonian cycle?
            
        } else if (args[0].equals("-f")) {
        	if(args.length != 2) {
                System.out.println("Invalid command. Please provide the necessary parameters.");
                return;
        	}
        	
        	String inputFile = args[1];
        	
            // Parse the input file and extract the parameters and adjacency matrix
            // Create the graph based on the adjacency matrix
        	try {
                File file = new File(inputFile);
                Scanner scanner = new Scanner(file);

                // Read the first line containing the simulation parameters
                String parametersLine = scanner.nextLine();
                String[] parameters = parametersLine.split("\\s+");
                
                if (parameters.length != 10) {
                    System.out.println("Invalid number of parameters in the input file.");
                    scanner.close();
                    return;
                }

                int numNodes = Integer.parseInt(parameters[0]);
                int nestNode = Integer.parseInt(parameters[1]);
                double alpha = Double.parseDouble(parameters[2]);
                double beta = Double.parseDouble(parameters[3]);
                double delta = Double.parseDouble(parameters[4]);
                double eta = Double.parseDouble(parameters[5]);
                double rho = Double.parseDouble(parameters[6]);
                double gamma = Double.parseDouble(parameters[7]);
                int colonySize = Integer.parseInt(parameters[8]);
                double tau = Double.parseDouble(parameters[9]);

                // Initialize the objects
                Graph graph = new Graph(numNodes);
                int[][] matrix = new int[numNodes][numNodes];

                // Read the weight matrix containing the graph structure and store it in a matrix
                for (int i = 0; i < numNodes; i++) {
                    String[] weights = scanner.nextLine().split("\\s+");
                    if (weights.length != numNodes) {
                        System.out.println("Invalid adjacency list in the input file.");
                        scanner.close();
                        return;
                    }
                    	
                    for (int j = i; j < numNodes; j++) {
                        int weight = Integer.parseInt(weights[j]);
                        matrix[i][j] = weight;
                    }
                }
                
                // Create the adjacency lists
                int weight;
  
                for (int i = 0; i < matrix.length; i++) { 	
                    for (int j = i; j < matrix.length; j++) {
                        weight = matrix[i][j];
                    	if (weight != 0) {
                            graph.addEdge(i+1, j+1, weight);
                        }
                    }
                }
                
                // Print the graph
                System.out.println("Input file GRAPH: ");
                graph.printGraph();
                
                graph.hasHamiltonian(); // O MAIN PARA FICHEIROS TA AQUI!!!

                scanner.close();

            } catch (FileNotFoundException e) {
                System.out.println("Input file not found: " + inputFile);
            }
        } else {
            System.out.println("Invalid command. Please provide the necessary parameters.");
        }
        
        
        
     // TESTE PARA CRIAR UM SIMPLES GRAFO E FAZER PRINT
        
     // Create the graph
     //   Graph graph = new Graph(4);
        
     //   graph.addEdge(1, 2, 10);
     //   graph.addEdge(1, 3, 5);
     //   graph.addEdge(2, 3, 8);
     //   graph.addEdge(3, 4, 2);

     //   System.out.println("Number of vertices: " + graph.getNumNodes());
     //   System.out.println("Number of edges: " + graph.getNumEdges());
        
     //   graph.printGraph();
        
        

	}

}
