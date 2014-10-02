package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import define.Define;

public class Dijkstra
{
	public static ArrayList<ArrayList<Integer>> shortest_Path = new ArrayList<ArrayList<Integer>>();
	private static Dijkstra instance = new Dijkstra();
	
	private Dijkstra(){
		
	}
	
	public static Dijkstra getInstance(){
		return Dijkstra.instance;
	}
	
	public void DijkstraMain(){
		for(int i = 0 ;i < Define.station.size() ; i++){
			this.DijkstraOperate(i);
		}
	}
	

	public void DijkstraOperate(int arg)
    {
		
        Vertex v0 = new Vertex("Seoul");
        Vertex v1 = new Vertex("Chuncheon");
        Vertex v2 = new Vertex("Wonju");
        Vertex v3 = new Vertex("Gyeongju");
        Vertex v4 = new Vertex("Daejeon");
        Vertex v5 = new Vertex("Asan");
        Vertex v6 = new Vertex("Gwangju");

		v0.adjacencies = new Edge[]{ new Edge(v1, 16),
		                             new Edge(v2, 22),
		                             new Edge(v4, 29),
		                             new Edge(v5, 20)};
		
		v1.adjacencies = new Edge[]{ new Edge(v0, 16),
		                             new Edge(v2, 28),
		                             new Edge(v3, 31) };
		
		v2.adjacencies = new Edge[]{ new Edge(v0, 22),
									 new Edge(v1, 28),
									 new Edge(v3, 32),
									 new Edge(v4, 23)};
		
		v3.adjacencies = new Edge[]{ new Edge(v1, 31),
		                             new Edge(v2, 32),
		                             new Edge(v4, 15),
		                             new Edge(v6, 18)};
		
		v4.adjacencies = new Edge[]{ new Edge(v0, 29),
	                               	 new Edge(v2, 23),
	                               	 new Edge(v3, 15),
	                               	 new Edge(v5, 35),
	                               	 new Edge(v6, 12)};
		
		v5.adjacencies = new Edge[]{ new Edge(v0, 20),
              	 					 new Edge(v4, 35),
              	 					 new Edge(v6, 25)};
              	 
		v6.adjacencies = new Edge[]{ new Edge(v3, 18),
              	 					 new Edge(v4, 12),
              	 					 new Edge(v5, 25)};
              	 		
		
		Vertex[] vertices = { v0, v1, v2, v3, v4, v5, v6 };
		
		ArrayList<Integer> tmp = new ArrayList<Integer>(); 
		
		

			computePaths(vertices[arg]);
			
			for (Vertex v : vertices){
				tmp.add((int) v.minDistance);
			}
			
			Dijkstra.shortest_Path.add(tmp);

		
    }
	



    public void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

	while (!vertexQueue.isEmpty()) {
	    Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
		if (distanceThroughU < v.minDistance) {
		    vertexQueue.remove(v);
		    v.minDistance = distanceThroughU ;
		    v.previous = u;
		    vertexQueue.add(v);
		}
            }
        }
    }

    public ArrayList<String> getShortestPathTo(Vertex target)
    {
    	ArrayList<String> result = new ArrayList<String>();
        
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous){
            result.add(vertex.name);
        }
        Collections.reverse(result);
        return result;
    }

    
}
