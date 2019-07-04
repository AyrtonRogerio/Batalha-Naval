/**
 * 
 */
package br.edu.psd.batalhanaval.model;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import br.edu.psd.batalhanaval.Util.Enum.CodigoButtonEnum;

/**
 * @author ayrton
 *
 */
public class Jogador {

	private String nome;
	private boolean suaVez = false;
	private boolean emJogo;
	private String acertos;
	private int jogadas[] = new int[3] ;
	private Map<String,String>coordenadasMeuJogoAtual = new HashMap<String,String>();
	/**
	 * @param nome
	 * @param suaVez
	 * @param acertos
	 * @param jogadas
	 */
	public Jogador(String nome, boolean suaVez, String acertos, int[] jogadas) {
		super();
		this.nome = nome;
		this.suaVez = suaVez;
		this.acertos = acertos;
		this.jogadas = jogadas;
	}
	
	public Jogador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the suaVez
	 */
	public boolean isSuaVez() {
		return suaVez;
	}
	/**
	 * @param suaVez the suaVez to set
	 */
	public void setSuaVez(boolean suaVez) {
		this.suaVez = suaVez;
	}
	/**
	 * @return the acertos
	 */
	public String getAcertos() {
		return acertos;
	}
	/**
	 * @param acertos the acertos to set
	 */
	public void setAcertos(String acertos) {
		this.acertos = acertos;
	}
	/**
	 * @return the jogadas
	 */
	public int[] getJogadas() {
		return jogadas;
	}
	/**
	 * @param jogadas the jogadas to set
	 */
	public void setJogadas(int[] jogadas) {
		this.jogadas = jogadas;
	}

	public Map<String, String> getCoordenadasMeuJogoAtual() {
		return coordenadasMeuJogoAtual;
	}

	public void setCoordenadasMeuJogoAtual(Map<String, String> coordenadasMeuJogoAtual) {
		this.coordenadasMeuJogoAtual = coordenadasMeuJogoAtual;
	}
	
	public boolean isEmJogo() {
		return emJogo;
	}

	public void setEmJogo(boolean emJogo) {
		this.emJogo = emJogo;
	}


	 
}
