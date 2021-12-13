// Dijkstra's Algorithm in Java
// credit: https://www.programiz.com/dsa/dijkstra-algorithm
// modified by Lolibeth Domer added prevVertex

public class dijkstra {
	static int INF = 99999;
  public static void dijkstra(int[][] graph, int source) {
	  int count = graph.length;
	  boolean[] visitedVertex = new boolean[count];
	  int[] distance = new int[count];
	  int prevVertex[]=new int [count];
	  for (int i = 0; i < count; i++) {
		  visitedVertex[i] = false;
		  distance[i] = Integer.MAX_VALUE;
		  prevVertex[i]=Integer.MAX_VALUE;}

	  distance[source] = 0;
	  for (int i = 0; i < count; i++) {
	      // Update the distance between neighbouring vertex and source vertex
	      int u = findMinDistance(distance, visitedVertex);
	      visitedVertex[u] = true;

	      // Update all the neighbouring vertex distances
	      for (int v = 0; v < count; v++) {
	    	  if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) { 
	    		  int temp_d=distance[v];
	    		  distance[v] = distance[u] + graph[u][v];
	    		 
	    		  if (v==0)
	    			  prevVertex[source]=0;
	    		  else if (temp_d>distance[v])
	    			  prevVertex[v]=u;
	    		 
	    	  } 
	      }
	  }
	  
	  System.out.println("Dijkstra's Algorithm\n\nVertex\tCost\tPrev.Vertex");
	  for (int i = 0; i < distance.length; i++) {
		  if (i==source)  System.out.println(String.format("%s\t %s\t   none", (char)(65+i), distance[i]));
		  else if (prevVertex[i]==Integer.MAX_VALUE) System.out.println(String.format("%s\t %s\t    %s", (char)(65+i), distance[i], (char)(source+65)));
		  else System.out.println(String.format("%s\t %s\t    %s", (char)(65+i), distance[i], (char)(prevVertex[i]+65)));
	  }
	 
  }

  // Finding the minimum distance between the option nodes
  private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
	  int minDistance = Integer.MAX_VALUE;
	  int minDistanceVertex = -1;
	  for (int i = 0; i < distance.length; i++) {
		  if (!visitedVertex[i] && distance[i] < minDistance) {
			  	minDistance = distance[i];
			  	minDistanceVertex = i;}
	  }
	  return minDistanceVertex;
  }

  public static void main(String[] args) {
	  int graph[][] = new int[][] 
	    		{	{ 0, 1, 7, 3, INF},
	    			{ 1, 0, 2, 6, INF },
	    			{ 7, 2, 0, 2, 8 },
	    			{ 3, 6, 2, 0, 3 },
	    			{ INF, INF, 8, 3, 0 }
	    		};
    dijkstra T = new dijkstra();
    //graph, source, target
    T.dijkstra(graph, 1);
  }
}