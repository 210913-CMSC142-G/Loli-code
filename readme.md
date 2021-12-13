# Finding the Shortest Path using Three Different Algorithms
The shortest path problem is finding the shortest minimum cost path from a source to a specific destination in a network/graph. This is an NP (Nondeterministic Polynomial) Complete Problem, since it is a computational problem with many efficient solution algorithms.  
## Introduction
   1.	**Graph** is a mathematical representation of a network and describes the relationship between lines and points. 
   2.	**Vertex** or node is a point in the graph represented by a circle.
   3.	**Edge** is a line that serves as a link that connects the vertices together.
   4.	Directed vs. Undirected
        - **Directed Graph** consists of edges or links of vertices that represents a one-way relationship.
        - **Undirected Graph** consists of edges that are bidirectional, or a relationship from a node to another node and backwards. 
   5.	Weighted vs. Unweighted
        - **Weighted Graph** is a graph whose edges have been labeled with weights or numbers. 
        - **Unweighted Graph** It is a graph with no weight or no numerical values attached.  
   6.	**Path** is a list of edges or vertices going from the source vertex to the target vertex. 
## Algorithms
   1.	**Dijkstra’s Algorithm**
        -	It is an algorithm for finding the shortest path with a minimum cost between nodes in any weighted graph with nonnegative weights. It was created and published by a computer scientist and software engineer, Dr. Edsger Dijkstra in 1959. 
        -	Time Complexity: **O( E log V) or O (V²)**
        - Space Complexity: **O(V)**
   2.	**Bellman-Ford Algorithm**
        -	It is also a single-source shortest path algorithm, similar to Dijkstra’s Algorithm. However, Bellman-Ford can work on graphs with negative- weighted edges.This was proposed by Alfonso Shimbell in 1955, however it was named after Richard Bellman and Lester Ford Jr.
        -	Time Complexity: **O(VE)**
        -	Space Complexity: **O(V)**
   3.	**Floyd-Warshall Algorithm**
        -	This is algorithm is very similar to Dijkstra’s, however the difference is that it computes all the pairs shortest distance of each node or vertex. Also, it do not have a boolean Visited_vertex variable where it strictly can visit the node only once. Thus, this updates distance whenever it changes and also applicable to negative-weighted graphs. This was proposed by Robert Floyd and Stephen  Warshall in the same year 1962.    
        -	Time Complexity: **O(V³)**
        -	Space Complexity: **O(V²)**
## Comparison of the Algorithms
| Algorithm | Time Complexity | Source | Negative Weights | Method | Directed/Indirected |
| --------- |---------------- | ------ |----------------- | ------ | ------------------- |
| Dijkstra  | O (E log V) or O (V²) | Single | No | Greedy | Both |
| Bellman-Ford | O (VE) | Single | Yes | Dynamic Programming | Both |
| Floyd-Warshall | O (V³) | All | Yes | Dynamic Programming | Both |

## Applications
   1.	**Digital Mapping Services: Google Map** was based in Dijkstra’s Algorithm which compute the minimum distance in various routes and paths from the source to the target location. 
   2.	**Communication Network: Telephone Network**; The bandwidth represents the amount information that can be transmitted by the line. Transmission line represent as edges and vertices are the stations.  
   3.	**IP Routing**; Internet have an Open Shortest Path First (OPSF) Protocol that is used to find the best shortest cost path between the source router and destination router. Also, Dijkstra’s algorithm is widely used in routing protocols. 
   4.	**Smart Grid: Power System**; This is a two-way communication from the producers to consumers. In which the sources controls and automates the needs of the customers. This portrays the shortest problem path to which transmission line it will use towards the consumers destination. 
   5.	**Flighting Agenda** have certain access to database on flights such as the arrival and departure time that computes the earliest arrival time for the destination from the origin airport. Therefore, it can determine which flights are the shortest and have minimum cost towards the customer’s destination.  
## Demonstration
   1.	Java 
          - Dijkstra's Algorithm (Java): https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/Dijkstra_Algorithm.java
          - Bellman-Ford's Algorithm (Java): https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/Bellman_Ford_Algorithm.java
          - Floyd-Warshall's Algorithm (Java): https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/Floyd_Warshall_Algorithm.java
   3.	Gama Platform
          - Shortest Path Problem simulation (GAMA Platform)
              - Java2D: https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/ShortestPath.gaml
              - 3d or opengl: https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/ShortestPath3d.gaml
          - Emergency Exit simulation (GAMA Platform): https://github.com/210913-CMSC142-G/Loli-code/blob/main/Shortest_Path_Java_and_GAMA_Platform/EmergencyExit.gaml
## References
  - GeeksforGeeks.(2020).Application of Dijkstra’s Shortest Path. https://www.geeksforgeeks.org/applications-of-dijkstras-shortest-path-algorithm/
  -	Statistics.com.(2021).Directed Vs. Undirected Network. https://www.statistics.com/glossary/directed-vs-undirected-network/
  - Indiatimes.(2021).Definition of Graph Theory. https://economictimes.indiatimes.com/definition/graph-theory
  - Javatpoint.(n.d).Type of Graphs.https://www.javatpoint.com/graph-theory-types-of-graphs 
  - GITTA.(2016).Dijkstra Algorithm: Short terms and Pseudocode. http://www.gitta.info/Accessibiliti/en/html/Dijkstra_learningObject1.html
  - GeeksforGeeks.(2021).Bellman-Ford Algorithm.https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
  - GeeksforGeeks.(2021). Floyd Warshall Algorithm. https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
  - Abdul, B. (n.d.).Dijkstra’s Algorithm; Bellman Ford; Floyd Warshall https://www.youtube.com/channel/UCZCFT11CWBi3MHNlGf019nw
  - Mejia, A.(2020). How to find time complexity of an algorithm.Adrian Mejia.https://adrianmejia.com/how-to-find-time-complexity-of-an-algorithm-code-big-o-notation/
