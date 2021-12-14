// Bellman-Ford's Algorithm in Java
// credit:  Aakash Hasija https://www.geeksforgeeks.org/bellman-ford-algorithm-simple-implementation/
// modified by Lolibeth Domer added prevVertex

import java.util.*;
import java.lang.*;
import java.io.*;

// A class to represent a connected, directed and weighted graph
public class bellman_ford {
	
	// A class to represent a weighted edge in graph
	class Edge {
		int src, dest, weight;
		Edge(){
			src = dest = weight = 0;}
	};

	int V, E;
	int prevVertex[];
	Edge edge[];
	int source;
	// Creates a graph with V vertices and E edges
	bellman_ford(int v, int e){
		V = v;
		E = e;
		prevVertex=new int [V];
		edge = new Edge[e];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}
	
	void BellmanFord(bellman_ford graph, int src){
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];
		source=src;
		// Initialize 
		for (int i = 0; i < V; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;

		
		for (int i = 1; i < V; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				int temp_d=dist[v];
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
					if (v==0)
	    			  prevVertex[0]=0;
					else if (temp_d>dist[v])
	    			  prevVertex[v]=u;
				}
			}
		}

		for (int j = 0; j < E; ++j) {
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("bellman_ford contains negative weight cycle");
				return;
			}
		}
		printArr(dist, V);
	}

	// A utility function used to print the solution
	void printArr(int distance[], int V){
		System.out.println("Bellman-Ford Algorithm\n\nVertex\tCost\tPrev.Vertex");
		 for (int i = 0; i < distance.length; i++) {
			  if (i==source)  System.out.println(String.format("%s\t %s\t   none", (char)(65+i), distance[i]));
			  else if (distance[i]==Integer.MAX_VALUE) System.out.println(String.format("%s\t INF\t    %s", (char)(65+i),  (char)(source+65)));
			  else System.out.println(String.format("%s\t %s\t    %s", (char)(65+i), distance[i], (char)(prevVertex[i]+65)));
		  }

		  System.out.println(String.format("\nShortest Path(Source: %s Target:E):\t", (char)(65+source)));
		  int x=4;
			  while(true) {
				 System.out.print((char)(65+x));
				  if (x==source)
				  	break;
				  x=prevVertex[x];
				  System.out.print(" <-- ");
			  }
			 
		  
	}

	// Driver method to test above function
	public static void main(String[] args){
		int V = 5; // Number of vertices in graph
		int E = 8; // Number of edges in graph

		bellman_ford graph = new bellman_ford(V, E);
		//DIRECTED GRAPH -Example towards E
		graph.edge[0].src =0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = 1;
		
		graph.edge[1].src = 0;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 7;
		
		graph.edge[2].src = 0;
		graph.edge[2].dest = 3;
		graph.edge[2].weight = 6;
		
		graph.edge[3].src = 1;
		graph.edge[3].dest = 2;
		graph.edge[3].weight = -2;
		
		graph.edge[4].src = 1;
		graph.edge[4].dest = 3;
		graph.edge[4].weight = 3;
		
		graph.edge[5].src = 2;
		graph.edge[5].dest = 3;
		graph.edge[5].weight = 4;
		
		graph.edge[6].src = 2;
		graph.edge[6].dest = 4;
		graph.edge[6].weight = -1;
		
		graph.edge[7].src = 3;
		graph.edge[7].dest = 4;
		graph.edge[7].weight = 3;
		

		graph.BellmanFord(graph, 0);
	}
}

 
