package br.edu.psd.batalhanaval.model;

import java.util.Map;

public class CordenadasJogador {
	private Map<String, String> coordenadas;
	private String nome;
		
	public CordenadasJogador(Map<String, String> coordenadas, String nome) {
		super();
		this.coordenadas = coordenadas;
		this.nome = nome;
	}
	
	public Map<String, String> getCoordenadas() {
		return coordenadas;
	}
	
	public void setCoordenadas(Map<String, String> coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
