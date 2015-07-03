package com.delivery.walmart.ws.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.delivery.walmart.ws.Dijkstra;

public class DijkstraTest {
	
	String originPoint = "A";
	String destinyPoint = "B";
	Double fuelPrice = new Double(3.0);
	Double autonomy = new Double(10.5);
	String routes = "A B 10-C D 15-E G 50";
	
	@Test
	public final void testComputePaths() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetShortestPathTo() {
		fail("Not yet implemented");
	}

	@Test
	public final void testFindClosestPath() {
		
		Dijkstra d = new Dijkstra();
		
		String result = d.findClosestPath(originPoint, destinyPoint, fuelPrice, autonomy, routes);
		String expectedResult = "Melhor rota: [A, B], distância: 10.0, custo final: 2,86";
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public final void testFindClosestPathWrongDestiny() {
		destinyPoint = "destinyDoesNotExist";
		
		Dijkstra d = new Dijkstra();
		
		String result = d.findClosestPath(originPoint, destinyPoint, fuelPrice, autonomy, routes);
		String expectedResult = "Formato do mapa inválido ou ponto origem/destino inexistente";
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	public final void testFindClosestPathWrongOrigin() {
		originPoint = "originDoesNotExist";
		Dijkstra d = new Dijkstra();
		
		String result = d.findClosestPath(originPoint, destinyPoint, fuelPrice, autonomy, routes);
		String expectedResult = "Formato do mapa inválido ou ponto origem/destino inexistente";
		
		assertEquals(result, expectedResult);
	}

	@Test
	public final void testComputePath() {
		fail("Not yet implemented");
	}

}
