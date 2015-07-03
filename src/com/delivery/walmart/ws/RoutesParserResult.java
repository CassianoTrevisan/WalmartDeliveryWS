package com.delivery.walmart.ws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.delivery.walmart.model.Route;

public class RoutesParserResult {
	private HashSet<Vertex> allPossibleVertices = new HashSet<Vertex>();
	private HashSet<Edge> allPossibleEdges = new HashSet<Edge>();
	private List<Route> routes = new ArrayList<Route>();
	
	
	
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
}
