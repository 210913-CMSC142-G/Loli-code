/**
* Name: Shortest Path 3d
* Author: Lolibeth Domer 
* Note: No vertex names since string is 2d 
*/

model ShortestPath

global {
	graph g_graph;
	int nb_nodes <-10;
    int power_sources <-round(nb_nodes*0.3);
    int walker <-power_sources;
    int power_consumer <-round(nb_nodes*0.7);
	string network <-"Random Network";
	string routing <- "Random Routing";
	string weight_option<-nil;
	string layout<-nil;
	bool Visit <-false; 
	bool source_consumer <-false; 
	bool weighted<-false;
	bool negative<-false;
	int x_cells <- nb_nodes/2;
	int y_cells <- nb_nodes/2;
	int av_degree <- 4;
	string optimizer_type <- "Dijkstra";
	
	//internal variables of nodes	
	list<int> list_nodes_degrees;
	list<point> list_nodes_visited;
	list<point> list_all_nodes;
	list list_regular_nodes;//not point
	list<point> list_all_edges;
	list list_regular_edges;
	
	//points of nodes
	list<point> list_sources;
	list<point> list_consumers;
	list<point> list_walkers;
	list<int> list_connected_index;

	//shortest path variables
	point source;
    point target;
    path shortest_path;
    path centrality_based_path;
    list<path> paths;    
    
    agent s<-nil;
	agent t<-nil;
		
	reflex source_consumer when: source_consumer=true{
	    if (g_graph = nil) {
	        g_graph <- one_of(g_graph.vertices);
	    }
	    	list_all_nodes<-nil;
	    	shortest_path <- nil;
		    loop while: shortest_path = nil {
		        source <-any (list_walkers);
		        target <- any (list_consumers);
		        
		         //stopping simulation	
	  			 if ( empty(list_nodes_visited) and Visit=true){
	   					write "ALL VERTICES WERE VISITED";
	   					do pause;
	   					break;
	   					}
	   			else if (Visit=true){// mark the node as visited 
			        remove target from: list_nodes_visited;
			     }
		       if (source != target) {
			        shortest_path <- path_between(g_graph, source, target);
			        loop v over: g_graph.vertices {
						if (point(v)=source){
							s<-v;
							 write "\nSource:"+v;
							 break;}}
					
					 loop v over: g_graph.vertices {
						 if (point(v)=target){
							t<-v;
							  write "Target:"+v;
							  break;}}
			        if (!empty(shortest_path)){write shortest_path;
			        if (weighted=true){do compute;}}
			        else{	write "No Path"; }
			     }
	    	}
    }//reflex end
    
    reflex random_source_target when: source_consumer=false{
    	shortest_path <- nil;
    	list_sources<-nil;
    	list_consumers<-nil;
    	
	    if (g_graph = nil) {
	        g_graph <- one_of(g_graph.vertices);}
	     
	    bool f<-true;
	    loop while: (f){
	    	 if ( empty(list_all_nodes) and Visit=true or (length(list_all_nodes)=1)){
	   				write "ALL VERTICES WERE VISITED";
	   				draw string("Visited") color: #lightgreen size: 4 at: {source.location.x-1, source.location.y+2};
	   				source<-nil;
	   				target<-nil;
	   				do pause;
	   				break;
	   		}
	   		
	    	 source <-any (list_all_nodes);
		     target <- any (list_all_nodes);
		     if source!=target{
		     	f<-false;
		     }
	    
	    
	    shortest_path <- path_between(g_graph, source, target);
	    loop v over: g_graph.vertices {
			if (point(v)=source){
				s<-v;
			 	write "\nSource:"+v;
				break;}}
					
		loop v over: g_graph.vertices {
			if (point(v)=target){
				t<-v;
			    write "Target:"+v;
				break;}}
		
	    if (Visit=true){// mark the node as visited 
			remove target from: list_all_nodes;
		}
		  if (!empty(shortest_path)){write shortest_path;
			        if (weighted=true){do compute;}}
			        else{	write "No Path"; }
		}
    }//reflex end
    
    action compute{
    	list compute;
			loop v over:list_regular_edges {
				if (contains(string(shortest_path),string(v))){
					add v to: compute;
				}
			}
			float c;
			string s<-"";
			loop v over:compute {
				if (v=compute[length(compute)-1]){
					s<-s+string(g_graph weight_of v with_precision 1);
					c<-c+g_graph weight_of v ;
				}
				else{
					s<-s+string(g_graph weight_of v with_precision 1)+"+";
					c<-c+g_graph weight_of v ;
				}
			}
			if (length(compute)>1){
				write "Cost: "+s+"= "+string(c with_precision 1);
			}
			else if (length(compute)=1){
				write "Cost: "+string(c with_precision 1);
			}
		
    }
    
	//CLEANING GRAPHS, VARIABLES ETC.
	action clean {	
	
		ask E {
			do die;}

		ask V {
			do die;}
		s<-nil;
		t<-nil;
		//list of walkers/producers/consumers
		list_sources<-nil;
		list_consumers<-nil;
		list_walkers<-nil;
		
		//reflex walkers
		centrality_based_path <- nil;
		shortest_path <- nil;
		list_connected_index <- nil;
		
		//internal variables/nodes
		list_nodes_degrees<-nil;
		list_nodes_visited<-nil;
		list_all_nodes<-nil;
		list_regular_nodes<-nil;		
	}
	
	//RANDOMIZE PRODUCER, CONSUMER, AND WALKERS, GET CONSUMERS TO VISIT
	action assigning_nodes{
		int count_sources<-0;
		int count_consumer<-0;
		int count_walker<-0;
		bool flag<-false;
		list<point> list_check<-nil;
		list<point> list_check_sources<-nil;
		
		point rand;
		
		list components_edge<-connected_components_of(g_graph,true);//true=edges, false=nodes
		list components_node<-connected_components_of(g_graph,false);//true=edges, false=nodes

		loop v over: g_graph.vertices {
			add v to: list_check;//for randomize
			add v to: list_all_nodes;//all nodes(point)
			add v to: list_regular_nodes;
			add g_graph degree_of v to: list_nodes_degrees;//(degree of nodes)
		}
		
		loop v over: g_graph.edges {
			add v to: list_all_edges;//all nodes(point)
			add v to: list_regular_edges;}
		
		loop p from:0 to: nb_nodes-1{
			rand<-any (list_check);
			//write length(list_check);
			if (count_sources!=power_sources){
				count_sources<-count_sources + 1;
				remove rand from: list_check;
				add rand to: list_sources;
			}
			else if (count_consumer!=power_consumer){
				count_consumer<-count_consumer + 1;
				remove rand from: list_check;
				add rand to: list_consumers;
			}	
			
		}//loop
		//make the rest a consumer
		if (!empty(list_check)){
				loop p from:0 to: length(list_check)-1{
					rand<-any (list_check);
					remove rand from: list_check;
					add rand to: list_consumers;
				}
			}
		
			//LIST OF CONSUMERS TO VISIT
			list_nodes_visited<-list_consumers;
			
			loop v over: list_sources {
			add v to: list_check_sources;}
			
			loop p from:0 to: walker-1{
				rand<-any (list_check_sources);
				 if (count_walker!=walker){
					count_walker<-count_walker + 1;
					remove rand from: list_check_sources;
					add rand to: list_walkers;
				}		
			}//loop walker
	}//randomized
	
	//RANDOM 
	action random_network {//builtin 
		//write "Creating Undirected Random Network";
		do clean;
		create V number: nb_nodes;
		g_graph <- first(V).my_graph;
		
		if (weighted=true and weight_option="Random(0,30)"){
			g_graph <- g_graph with_weights (g_graph.edges as_map (each::rnd(0,30)));
				write "Creating Undirected and Weighted(Random:0-30) Random Network";
		}
		else if (weighted=true and weight_option="Random(-30,30)"){
			g_graph <- g_graph with_weights (g_graph.edges as_map (each::rnd(-30,30)));
				write "Creating Undirected and Weighted(Random:-30-30) Random Network";
		}
		else if (weighted=true and weight_option="Length/Distance"){
			g_graph <- g_graph with_weights (g_graph.edges as_map (each::geometry(each).perimeter));
					write "Creating Undirected and Weighted(Length) Random Network";
		}
		else if weighted=false{
			write "Creating Undirected and Unweighted Random Network";
		}
		//g_graph <- directed(g_graph);
		g_graph <- undirected(g_graph);
	}
	
	action layout{
		switch layout {
			match "Force" {
				ask world {
					do f_layout;}}

			match "Grid" {
				ask world {
					do g_layout(av_degree);}}

			match "sphere" {
				ask world {
					do c_layout;}}
					
			match "Random" {
				ask world {
					write "Random Layout";}}
		}
	}
	
	action algorithm{
		g_graph <- g_graph with_optimizer_type optimizer_type;}
	
	action c_layout {
		write "sphere classical layout : nodes are randomly placed on a sphere";
		g_graph <- layout_circle(g_graph, world.shape,false);}

	action f_layout {
		write "Forced based layout : connected node pull each other, while unconnected node push each other away";
		g_graph <- layout_force(g_graph, world.shape, 0.4, 0.01, 0 );}

	action g_layout(int k) {
		do clean;
		g_graph <- grid_cells_to_graph(Ve,E);
		write "Grid based layout : distributes nodes over a grid to minimize edge crossing";
		g_graph <- undirected(g_graph);}
		
}// global end

//V SPECIES
species V parent: graph_node edge_species: E skills: [moving3D]{
   
    init {
	    int i <- 0;
	    loop g over: V {
	        if (flip(0.1)) {
	        	add i to: list_connected_index;}
	        i <- i + 1;
	    }}

    bool related_to (V other) {
	    using topology(world) {
	        return (self.location distance_to other.location < 50);
	    }}

   aspect base {
    	point the_node <-self;
		string class<-nil;
		rgb color <- rgb(150 +rnd(100),150 + rnd(100),150 + rnd(100));
		
		if (source_consumer=true){
			loop s from:0 to: length(list_sources)-1 {
				if (list_sources[s]=the_node){
					class<-"source";}}
			loop c from:0 to: length(list_consumers)-1 {
				if (list_consumers[c]=the_node){
					class<-"consumer";}}
			
			if (class="source"){  
				draw cube(3) border: #black color: #red;}
			else if(class="walker"){
				draw sphere(2) color: #yellow;}
			else{//consumers
	    		draw pyramid(3) border: #black color: color;
				draw sphere(3/3)   at: {location.x,location.y,3*0.75} color: color;}
    	}else{
    		draw pyramid(3) border: #black color: color;
			draw sphere(3/3)at: {location.x,location.y,3*0.75} color: color;
    	}
    	
    	
    	//draw string(g_graph degree_of self) color: #black size: 4 at: {self.location.x-1, self.location.y-2};
      
      	bool flag<-false;
      	if (Visit=true){
		    loop v over: list_consumers {
		      	if (the_node=v){
		      		flag<-true;
		      	}
		      }
		     //except sources
		     loop v over: list_sources {
		      	 if (the_node=v){
		      		flag<-true;
		      	}
		     }
		      loop v over: list_all_nodes {
		      	if (the_node=v){
		      		flag<-true;
		      	}
		      }
	     }
	     if (flag=false and Visit=true){
	     	draw pyramid(3) border: #black color: #green;
			draw sphere(3/3)at: {location.x,location.y,3*0.75} color: #green;
	     	if (length(list_all_nodes)=1){
	     			
	     	}	
	     }
	    
	     draw geometry(self) color: #black size: 4 at: {self.location.x, self.location.y};	
      	}//aspect
}//end builtin node 

species E parent: base_edge skills: [moving3D] {
	init{
		//write self;
	}
   aspect base {
   		 draw shape color: #grey;
   		 if layout!="Grid"{
	   		 if (weighted=false){
	   			//draw (string(self)) color: #black size: 4 at: {self.location.x, self.location.y};	
	   		 }
	   		else {
	   			//draw geometry((g_graph weight_of self) with_precision 1) color: #black size: 4 at: {self.location.x, self.location.y+2};	
	   		}
   		}
    }
   
}
	grid Ve width: x_cells height: y_cells neighbors: 4  {
		
}

//NETWORKS
experiment Graph type: gui  {
	
	parameter "Number of Vertices :" var:nb_nodes min:2 max:30;
	parameter "Weighted:" var:weighted enables:[weight_option,negative];	
	parameter "Weight_no:" var:weight_option among:["Length/Distance","Random(0,30)","Random(-30,30)"];
	parameter "Type of optimizer" var: optimizer_type among: [ "Dijkstra", "BellmannFord", "FloydWarshall"];
	parameter "Graph Layout:" var:layout step:2 among:["Random","Force","Grid"];
	parameter "Marked as Visited:" var:Visit;
	parameter "Activate assigned source:" var:source_consumer;
	
	user_command "Draw the Network"  {		
		ask world {
			do random_network;
			do layout;
			do assigning_nodes;	
			do algorithm;
		}
	}//user command
	
	output{
		monitor "Number of Vertices:" value: length(g_graph.vertices);
		monitor "Number of Edges:" value: length(g_graph.edges);
		
		display Graph type:opengl background:rgb(10,40,55) {
		species V aspect: base;
        species E aspect: base;
     	
        graphics "path" { 	
        	if layout="Grid"{
	        	loop v over: g_graph.vertices {
	        		point z<-v;
	        		rgb color <- rgb(150 +rnd(100),150 + rnd(100),150 + rnd(100));
						draw pyramid(3) at: v border: #black color: color;
						draw sphere(3/3)at: {z.location.x,z.location.y,3*0.75} color: color;
				}
				loop e over: g_graph.edges {
				}
			}
			loop eg over: g_graph.edges {
						geometry edge_geom <- geometry(eg);
						draw line(edge_geom.points, 0.2)  color: #grey;
					
					}
        	if (shortest_path != nil) {
        		if source_consumer=true{
	            draw cube(4) border: #black at: source color: #red;}
	            else {
		            draw pyramid(4) border: #black at: source  color: #red;
					draw sphere(4/3) at: {source.x,source.y,3*0.75} color: #red;
	            }
	          		draw pyramid(4) border: #black at: target  color: #yellow;
					draw sphere(4/3) at: {target.x,target.y,4*0.75} color: #yellow;
	          	geometry edge_geom <- geometry(shortest_path.shape);
				draw line(edge_geom.points, 0.5)  color: #yellow;
			}
		}
        }
 	}
}//experiment end


