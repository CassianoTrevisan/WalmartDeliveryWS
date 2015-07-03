package com.delivery.walmart.model;

public class Mapa {
	private Integer id;
	private String nome;
	private String rotas;
	
	
	public Mapa(Integer id, String nome, String rotas) {
		super();
		this.id = id;
		this.nome = nome;
		this.rotas = rotas;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRotas() {
		return rotas;
	}
	public void setRotas(String rotas) {
		this.rotas = rotas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
