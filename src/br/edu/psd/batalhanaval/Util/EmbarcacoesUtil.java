package br.edu.psd.batalhanaval.Util;

public class EmbarcacoesUtil {
	public static int NUMEMCOURACADO = 2;
	public static int NUMCRUZADORES = 3;
	public static int NUMPORTAAV = 1;
	public static int NUMHIDROAV = 5;
	public static int NUMSUBMARINO = 4;
	private static int encouracadosPosicionados = 0;
	private static int cruzadoresPosicionados = 0;
	private static int portaAvPosicionados = 0;
	private static int hidroAvPosicionados = 0;
	private static int subimarinosPosicionados = 0;
	public static int getEncouracadosPosicionados() {
		return encouracadosPosicionados;
	}
	public static void setEncouracadosPosicionados(int encouracadosPosicionados) {
		EmbarcacoesUtil.encouracadosPosicionados = encouracadosPosicionados;
	}
	public static int getCruzadoresPosicionados() {
		return cruzadoresPosicionados;
	}
	public static void setCruzadoresPosicionados(int cruzadoresPosicionados) {
			EmbarcacoesUtil.cruzadoresPosicionados=cruzadoresPosicionados;
			if(EmbarcacoesUtil.cruzadoresPosicionados<0)
				EmbarcacoesUtil.cruzadoresPosicionados=0;
			else if(EmbarcacoesUtil.cruzadoresPosicionados>NUMCRUZADORES)
				EmbarcacoesUtil.cruzadoresPosicionados=NUMCRUZADORES;
	}
	public static int getPortaAvPosicionados() {
		return portaAvPosicionados;
	}
	public static void setPortaAvPosicionados(int portaAvPosicionados) {
		EmbarcacoesUtil.portaAvPosicionados = portaAvPosicionados;
	}
	public static int getHidroAvPosicionados() {
		return hidroAvPosicionados;
	}
	public static void setHidroAvPosicionados(int hidroAvPosicionados) {
		EmbarcacoesUtil.hidroAvPosicionados = hidroAvPosicionados;
	}
	public static int getSubimarinosPosicionados() {
		return subimarinosPosicionados;
	}
	public static void setSubimarinosPosicionados(int subimarinosPosicionados) {
		EmbarcacoesUtil.subimarinosPosicionados = subimarinosPosicionados;
	}
	
}
