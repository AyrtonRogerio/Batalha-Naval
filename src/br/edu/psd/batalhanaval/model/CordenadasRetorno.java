package br.edu.psd.batalhanaval.model;

import java.io.Serializable;

public class CordenadasRetorno implements Serializable{
	private String cord1;
	private String cord2;
	private String cord3;
	private String acertoC1;
	private String acertoC2;
	private String acertoC3;
	private String nomeDestino;//Destino!!!
	private String nomeRemetente;//Destino!!!
	public CordenadasRetorno(String cord1, String cord2, String cord3, String acertoC1, String acertoC2,
			String acertoC3, String nome,String remetente) {
		super();
		this.cord1 = cord1;
		this.cord2 = cord2;
		this.cord3 = cord3;
		this.acertoC1 = acertoC1;
		this.acertoC2 = acertoC2;
		this.acertoC3 = acertoC3;
		this.nomeDestino = nome;
		this.nomeRemetente=remetente;
	}
	
	public String getNomeDestino() {
		return nomeDestino;
	}

	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	@Override
	public String toString() {
		return "CordenadasRetorno [cord1=" + cord1 + ", cord2=" + cord2 + ", cord3=" + cord3 + ", acertoC1=" + acertoC1
				+ ", acertoC2=" + acertoC2 + ", acertoC3=" + acertoC3 + ", nome=" + nomeDestino + "]";
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
	public String getAcertoC1() {
		return acertoC1;
	}
	public void setAcertoC1(String acertoC1) {
		this.acertoC1 = acertoC1;
	}
	public String getAcertoC2() {
		return acertoC2;
	}
	public void setAcertoC2(String acertoC2) {
		this.acertoC2 = acertoC2;
	}
	public String getAcertoC3() {
		return acertoC3;
	}
	public void setAcertoC3(String acertoC3) {
		this.acertoC3 = acertoC3;
	}
	
}
