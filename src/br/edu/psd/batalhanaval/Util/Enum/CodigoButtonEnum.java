package br.edu.psd.batalhanaval.Util.Enum;

public enum CodigoButtonEnum {
	
	NUMERO("[NUM]"), VAZIO("[V]"), POSICAO("[POS]"), TIROAGUA("[TIRO NA AGUA]"),AFUNDAR("[AFUNDADO]"),ATIVO("[ATIVO]");
	private String descricao;
	private CodigoButtonEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
