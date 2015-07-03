package com.delivery.walmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.delivery.walmart.db.DBConnection;
import com.delivery.walmart.model.Mapa;
import com.delivery.walmart.model.Route;

public class MapaDAO {
	
	public static Boolean update(Mapa mapa){
		
		 DBConnection db = new DBConnection();
		 Connection conn = null;
		 PreparedStatement pps = null; 
		 
	      try {
			
			conn = db.getConnection();
			
			String sql = "update tb_mapas set nome = ?, rotas = ? where id = ?";
			
			pps = conn.prepareStatement(sql);
			
			pps.setString(1, mapa.getNome());
			pps.setString(2, mapa.getRotas());
			pps.setInt(3, mapa.getId());
			
			pps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(pps != null){
					pps.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException s){
				return false;
			}
		}
		
		return true;
	}
	
	public static Boolean insert(String map, String routes){
		
		 DBConnection db = new DBConnection();
		 Connection conn = null;
		 PreparedStatement pps = null; 
		 
	      try {
			
			conn = db.getConnection();
			
			String sql = "insert into tb_mapas (nome, rotas) values (?, ?)";
			
			pps = conn.prepareStatement(sql);
			
			pps.setString(1, map);
			pps.setString(2, routes);
			
			pps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(pps != null){
					pps.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException s){
				return false;
			}
		}
		
		return true;
	}
	
	public static Boolean delete(Integer id){
		
		 DBConnection db = new DBConnection();
		 Connection conn = null;
		 PreparedStatement pps = null; 
		 
	      try {
			
			conn = db.getConnection();
			
			String sql = "delete from tb_mapas where id = ?";
			
			pps = conn.prepareStatement(sql);
			
			pps.setInt(1, id);
			
			pps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(pps != null){
					pps.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException s){
				return false;
			}
		}
		
		return true;
	}
	
	public static List<Mapa> load(){
		List<Mapa> ret = new ArrayList<Mapa>();
		
		 DBConnection db = new DBConnection();
		 Connection conn = null;
		 PreparedStatement pps = null; 
		 
	      try {
			
			conn = db.getConnection();
			
			String sql = "select id, nome, rotas from tb_mapas";
			
			pps = conn.prepareStatement(sql);
			ResultSet rs = pps.executeQuery();
			
			while(rs.next()){
				ret.add(new Mapa(rs.getInt("id"), rs.getString("nome"), rs.getString("rotas")));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(pps != null){
					pps.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException s){
				return null;
			}
		}
		
		return ret;
	}
}
