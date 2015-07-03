package com.delivery.walmart.ws;

import com.delivery.walmart.model.Route;

public class RoutesParser {
	private RoutesParserResult result;
	
	public RoutesParserResult parseRoutes(String routes) throws Exception{
		
		result = new RoutesParserResult();
		String[] strRoutes = routes.split("-");
		
		for(String strRoute : strRoutes){
			String[] strData = strRoute.split(" ");
			if(strData.length != 3){
				throw new Exception("Formato das rotas inválido, ex: A B 10-B C 20");
			}else{
				Double distance = Double.parseDouble(strData[2]);
				//para cada rota proveniente do mapa original, crio uma segunda rota de sentido oposto
				result.getRoutes().add(new Route(strData[0], strData[1], distance));
				result.getRoutes().add(new Route(strData[1], strData[0], distance));
			}
		}
		
		
		return this.result;
	}
}
