/**
 * 
 */
package br.edu.psd.batalhanaval.Util.Enum;

import com.sun.org.apache.regexp.internal.recompile;

/**
 * @author ayrton
 *
 */
public enum TipoEmbarcacaoEnum {

	ENCOURACADO ("ENCOURACADO"), PORTAAVIAO("PORTAAVIOES"), 
	CRUZADORES("CRUZADORES"), HIDROAVIAO("HIDROAVIAO"), SUBMARINO("SUBMARINO");
	
	private String descricao;
	
	private TipoEmbarcacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getValor() {
		return this.descricao;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getValor();
	}
	
	
}
