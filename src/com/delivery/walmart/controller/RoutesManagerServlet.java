package com.delivery.walmart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.walmart.dao.MapaDAO;
import com.delivery.walmart.model.Mapa;

/**
 * Servlet implementation class RoutesManagerServlet
 */
@WebServlet("/RoutesManagerServlet")
public class RoutesManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoutesManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getParameter("action");
		 response.setContentType("text/html;charset=UTF-8");

		 if(action.equals("save")){
		      String map = request.getParameter("map");
		      String routes = request.getParameter("routes");
		      
		     Boolean insertOK = MapaDAO.insert(map, routes);
		     
		     PrintWriter out = response.getWriter();
		      if(insertOK){
			      response.setStatus(200);  
		      }else{
		    	  response.setStatus(400);
		      }
		 }
		 else if(action.equals("load")){
		      
		     List<Mapa> routesLoaded = MapaDAO.load();
		     StringBuilder sb = new StringBuilder();
		     
		     sb.append("[");
		     for(Mapa m : routesLoaded){
		    	 sb.append("{\"id\":"+m.getId()+",\"nome\":\""+m.getNome()+"\",\"rotas\":\""+m.getRotas()+"\"},");
		     }
		     if(sb.length() > 3){
		    	 sb.deleteCharAt(sb.lastIndexOf(","));
		     }
		     sb.append("]");
		     PrintWriter out = response.getWriter();
	    	 out.write(sb.toString());
		     response.setStatus(200);  
		 }
		 else if(action.equals("remove")){
			 
			 Boolean deleteOK = MapaDAO.delete(Integer.parseInt(request.getParameter("id")));
		     
		     if(deleteOK){
			    response.setStatus(200);  
		     }else{
		       response.setStatus(400);
		     }
		 }
		 else if(action.equals("update")){
			  int id = Integer.parseInt(request.getParameter("id"));
		      String map = request.getParameter("map");
		      String routes = request.getParameter("routes");
		      
		     Boolean updateOK = MapaDAO.update(new Mapa(id, map, routes));
		     
		      if(updateOK){
			      response.setStatus(200);  
		      }else{
		    	  response.setStatus(400);
		      }
		 }
	}
}
