/**
* Name:  Movement on a Graph created by Polygons
* Author:  Patrick Taillandier
* Modified by: Lolibeth Domer
*/

model polygon
global {
	//Import of the shapefile containing the different polygons
	file shape_file_in <- file('squareHole.shp');
	graph the_graph;
	
	geometry shape <- envelope(shape_file_in);
	
	init {    
		create object from: shape_file_in ;
		object the_object <- first(object);
		
		//triangulation of the object to get the different triangles of the polygons
		list<geometry> triangles <- list(triangulate(the_object, 0.01));
		
		loop trig over: triangles {
			create triangle_obj {
				shape <- trig;
			}
		}
		
		//creation of a list of skeleton from the object 
		list<geometry> skeletons <- list(skeletonize(the_object, 0.01));
		
		//Split of the skeletons list according to their intersection points
		list<geometry> skeletons_split  <- split_lines(skeletons);
		loop sk over: skeletons_split {
			create skeleton {
				shape <- sk;
			}
		}
		
		//Creation of the graph using the edges resulting of the splitted skeleton
		 the_graph <- as_edge_graph(skeleton);
		 
		 
		create goal  {
			 location <- any_location_in (one_of(skeleton)); 
		}
		create people number: 100 {
			 target <- one_of (goal) ; 
			 location <- any_location_in (one_of(skeleton));
		} 
	}
}

species object  {
	aspect default {
		draw shape color: #gray ;
	}
}

species triangle_obj  {
	rgb color <- rgb(150 +rnd(100),150 + rnd(100),150 + rnd(100));
	aspect default {
		draw shape color: #gray ; 
	}
}

species skeleton  {
	aspect default {
		draw shape + 0.2 color: #red ;
	}
}
	
species goal {
	aspect default {
		draw cube(5) color:#green;
	}
}

species people skills: [moving3D] {
	goal target;
	path my_path; 
		rgb color <- rgb(150 +rnd(100),150 + rnd(100),150 + rnd(100));
	reflex goto {
		do goto on:the_graph target:target speed:1.0;
	}
	aspect default {
		draw pyramid(3) color: color;
		draw sphere(3/3) at: {location.x,location.y,3*0.75} color: color;
	}
}

experiment goto_polygon type: gui {
	output {
		display objects_display type:opengl{
			species object aspect: default ;
			species triangle_obj aspect: default ;
			species skeleton aspect: default ;
			species people aspect: default ;
			species goal aspect: default ;
		}
	}
}
 
