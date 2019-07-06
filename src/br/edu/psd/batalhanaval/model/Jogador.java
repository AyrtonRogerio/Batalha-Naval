/**
 * 
 */
package br.edu.psd.batalhanaval.model;

import java.util.HashMap;
import java.util.Map;

import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.Enum.TipoEmbarcacaoEnum;

/**
 * @author ayrton
 *
 */
public class Jogador {

	private String nome;
	private boolean suaVez = false;
	private boolean emJogo;
	private String acertos="";
	private Map<String,String>coordenadasMeuJogoAtual = new HashMap<String,String>();
	/**
	 * @param nome
	 * @param suaVez
	 * @param acertos
	 * @param jogadas
	 */


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

	/**
	 * @param jogadas the jogadas to set
	 */
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

	public int embarcacoesAfundadas(String navio) {
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.CRUZADORES.getValor()))
			return acertos.split(TipoEmbarcacaoEnum.CRUZADORES.getValor()).length - 1< 0? 0:acertos.split(TipoEmbarcacaoEnum.CRUZADORES.getValor()).length - 1;
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.ENCOURACADO.getValor()))
			return acertos.split(TipoEmbarcacaoEnum.ENCOURACADO.getValor()).length - 1< 0? 0:acertos.split(TipoEmbarcacaoEnum.ENCOURACADO.getValor()).length - 1;
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.HIDROAVIAO.getValor()))
			return acertos.split(TipoEmbarcacaoEnum.HIDROAVIAO.getValor()).length - 1< 0? 0:acertos.split(TipoEmbarcacaoEnum.HIDROAVIAO.getValor()).length - 1;
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.PORTAAVIAO.getValor()))
			return acertos.split(TipoEmbarcacaoEnum.PORTAAVIAO.getValor()).length - 1< 0? 0:acertos.split(TipoEmbarcacaoEnum.PORTAAVIAO.getValor()).length - 1;
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.SUBMARINO.getValor()))
			return acertos.split(TipoEmbarcacaoEnum.SUBMARINO.getValor()).length - 1< 0? 0:acertos.split(TipoEmbarcacaoEnum.SUBMARINO.getValor()).length - 1;
		return 0;
	}
	public boolean verificarSeGanhei() {
		if(acertos.split(";").length-1>=EmbarcacoesUtil.NUMCRUZADORES+EmbarcacoesUtil.NUMEMCOURACADO+EmbarcacoesUtil.NUMHIDROAV+
			EmbarcacoesUtil.NUMPORTAAV+EmbarcacoesUtil.NUMSUBMARINO)
			return true;
		return false;
	}
	public void resetarCaracteristicas() {
		coordenadasMeuJogoAtual .clear();
		this.acertos="";
		this.emJogo=false;
		this.suaVez=false;
	}
}
