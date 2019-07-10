package br.edu.psd.batalhanaval.model;

import java.io.Serializable;

public class CordenadasJogador implements Serializable{
	private String cord1;
	private String cord2;
	private String cord3;
	private String nome;//Destino!!!
	private String nomeRementente;//Destino!!!
		

	public CordenadasJogador(String cord1, String cord2, String cord3, String nome) {
		super();
		this.cord1 = cord1;
		this.cord2 = cord2;
		this.cord3 = cord3;
		this.nome = nome;
	}
	public String getNomeRementente() {
		return nomeRementente;
	}
	public void setNomeRementente(String nomeRementente) {
		this.nomeRementente = nomeRementente;
	}
	@Override
	public String toString() {
		return "CordenadasJogador [cord1=" + cord1 + ", cord2=" + cord2 + ", cord3=" + cord3 + ", nome=" + nome
				+ ", nomeRementente=" + nomeRementente + "]";
	}
	public String getCord1() {
		return cord1;
	}

	public void setCord1(String cord1) {
		this.cord1 = cord1;
	}

	public String getCord2() {
		return cord2;
	}

	public void setCord2(String cord2) {
		this.cord2 = cord2;
	}

	public String getCord3() {
		return cord3;
	}

	public void setCord3(String cord3) {
		this.cord3 = cord3;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
