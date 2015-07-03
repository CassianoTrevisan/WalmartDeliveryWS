package com.delivery.walmart.ws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import com.delivery.walmart.model.Route;

class Vertex implements Comparable<Vertex>
{
    public final String name;
    public List<Edge> adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Dijkstra{
	
    public static void computePaths(Vertex source){
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
	
            // Visit each edge exiting u
            for (Edge e : u.adjacencies){
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

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
    
    public String findClosestPath (String originPoint, String destinyPoint,
    		Double fuelPrice, Double autonomy, String routes){
    
    	RoutesParser rp = new RoutesParser();
    	
    	String finalResult = "";
    	
    	try {
			RoutesParserResult routeResult = rp.parseRoutes(routes);
			
			finalResult = computePath(originPoint, destinyPoint, fuelPrice, autonomy, routeResult.getRoutes());
    	}catch (NumberFormatException nfe){
    		return "Formato do valor 'distância' inválido";
		} catch (Exception e) {
			return "Formato do mapa inválido ou ponto origem/destino inexistente";
		}
    	
    	return finalResult;
    }

    public static String computePath(String originPoint, String destinyPoint,
    		Double fuelPrice, Double autonomy, List<Route> routes){
	
    	Vertex fromVertex = new Vertex(originPoint);
    	Vertex whereVertex = new Vertex(destinyPoint);
		
		List<Vertex> allPossibleVertices = new ArrayList<Vertex>();
		
		Vertex auxVertex;
		
	    for(Route r : routes){//isolando todas as cidades do mapa
	    	
	    	auxVertex = new Vertex(r.getOrigin());
	    	
	    	if(!allPossibleVertices.contains(auxVertex)){//se esta cidade ainda nao estiver na lista
	    		allPossibleVertices.add(auxVertex);
	    	}
	    	
	    	auxVertex = new Vertex(r.getDestiny());
	    	if(!allPossibleVertices.contains(auxVertex)){//se esta cidade ainda nao estiver na lista
	    		allPossibleVertices.add(auxVertex);
	    	}
	    }
	    
	    
	    HashMap<String, Vertex> verticesMap = new HashMap<String, Vertex>();
	    
	    for(Vertex v : allPossibleVertices){// populando HashMap apartir da List para ter acesso direto ao objeto sem fazer iteracao
	    	verticesMap.put(v.name, v);
	    }
	    
	    for(Vertex v : allPossibleVertices){
	    	
	    	v.adjacencies = new ArrayList<Edge>();
	    	
	    	for(Route r : routes){
	    		if(r.getOrigin().equals(v.name)){
	    			
	    			v.adjacencies.add(new Edge(verticesMap.get(r.getDestiny()), r.getDistance()));
	    			
	    		}
	    	}
	    }
	        
		computePaths(verticesMap.get(fromVertex.name));
		
		whereVertex = verticesMap.get(whereVertex.name);
		
	    List<Vertex> path = getShortestPathTo(whereVertex);
	    
	    Double minDistance = whereVertex.minDistance;
	    
	    return "Melhor rota: " + path + ", distância: " + minDistance + ", custo final: " + String.format("%.2f", minDistance / autonomy * fuelPrice);
	}
}