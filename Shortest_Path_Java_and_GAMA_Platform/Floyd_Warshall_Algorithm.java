// Bellman-Ford's Algorithm in Java
// credit:  Aakash Hasija https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/?ref=gcse
// modified by Lolibeth Domer added prevVertex

import java.util.*;
import java.lang.*;
import java.io.*;


public class floyd_warshall{
	static int INF = 99999, V;
	int prevVertex[][];
	void floydWarshall(int graph[][]){
		V=graph.length;
		int dist[][] = new int[V][V];
		prevVertex=new int [V][V];
		int i, j, k;
		

		//Initialize the distance in the graph
		for (i = 0; i < V; i++) {
			for (j = 0; j < V; j++) {
				dist[i][j] = graph[i][j];
				prevVertex[i][j]=INF;
			}
		}
		// Assigning the source 
		for (k = 0; k < V; k++){
			// The next destination vertex from the source k
			for (i = 0; i < V; i++){
				//Updating the distance matrix 
				for (j = 0; j < V; j++){
					// If vertex k is on the short	est path from
					// i to j, then update the value of dist[i][j]
					int temp_d=dist[i][j];
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						
						//if (k==0)
							//prevVertex[j][0]=0;
						 if (temp_d>dist[i][j] )
			    			  prevVertex[i][j]=k;
					}
				}
			}
		}

		// Print the shortest distance matrix
		printSolution(dist);
	}

	void printSolution(int dist[][]){
		System.out.println("\tFloyd-Warshall's Algorithm\n\t");
		
		System.out.println("\tShortest Distance from the Source");
		System.out.print("S\\T\t");
		for (int l = 0; l <V; l++) 
			 System.out.print((char)(65+l)+"\t");
		System.out.print("\n");
		for (int i=0; i<V; ++i){
			System.out.print("\n"+(char)(65+i)+"\t");
			for (int j=0; j<V; ++j){
				if (dist[i][j]==INF)
					System.out.print("INF\t");
				else
					System.out.print(dist[i][j]+"\t");
			}}
		
		System.out.println("\n\n\tShortest Path All Pairs(Prev Vertex)");
		System.out.print("\t");
		for (int l = 0; l <V; l++) 	
			 System.out.print((char)(65+l)+"\t");
		System.out.print("\n");
		for (int i=0; i<V; ++i){
			System.out.print("\n"+(char)(65+i)+"\t");
			for (int j=0; j<V; ++j){
				if (i==j)
					System.out.print("none\t");
				else if (prevVertex[i][j]==INF)System.out.print((char)(65+i)+"\t");
				else
					System.out.print((char)(65+prevVertex[i][j])+"\t");
			}}
	}

	// Driver program to test above function
	public static void main (String[] args){
		/* Let us create the following weighted graph	 
		int graph[][] = { {0, 5, INF, 10},
						{INF, 0, 3, INF},
						{INF, INF, 0, 1},
						{INF, INF, INF, 0}
						};/*
		/*  */int graph[][] = new int[][] 
		    		{	{ 0, 1, 7, 3, INF},
		    			{ 1, 0, 2, 6, INF },
		    			{ 7, 2, 0, 2, 8 },
		    			{ 3, 6, 2, 0, 3 },
		    			{ INF, INF, 8, 3, 0 }
		    		};
		    		V=graph.length;
		floyd_warshall a = new floyd_warshall();

		// Print the solution
		a.floydWarshall(graph);
	}
}


