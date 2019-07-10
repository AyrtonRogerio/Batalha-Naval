/**
 * 
 */
package br.edu.psd.batalhanaval.Util;

import br.edu.psd.batalhanaval.model.socket.Cliente;

/**
 * @author ayrton
 *
 */
public class ProtocoloUtil {

	//Atributo pra modificar o cliente na sessao dele
	public static Cliente CLIENTE = null;
	
	public static final String CONECTADO = "<CON>"; // 
	public static final String LISTA_USER_ONLINE = "<LUO>"; // lista user online
	public static final String USER_SAIU = "<UOF>"; // notifica users quando sai
	public static final String USER_WIN = "<UWG>"; // Usuário q ganhou o jogo
	public static final String USER_LOSE = "<ULG>"; // usuário q perdeu o jogo
	public static final String TIRO_ERRO = "<TER>"; // tiro errado
	public static final String TIRO_ACERTO = "<TAC>"; //tiro certo
	public static final String COORDENADA = "<CORD>"; //tiro certo
	public static final String QUER_JOGAR ="<QUER_JOGAR>";//PERGUNTA SE O OUTRO JOGADOR QUER JOGAR
	public static final String ACEITAR = "<OK>";// ACEITAR REQUISI��O PRA JOGAR 
	public static final String RECUSAR = "<N�O>";// RECUSAR REQUISI��O PRA JOGAR 
	public static final String ESPERARANDO = "<ESPERANDO>";
	public static final String INICIAR = "<INICIAR>";
	public static final String NOME = "<MEUNOME>";
	public static final String TERMINEI = "<TERMINEI>";
	public static final String SEPARADOR = "< >";
	public static final String JOGADOR_EXISTE="<EXISTE>";
	public static final String JOGADOR_NAO_EXISTE="<NAO_EXISTE>";
	public static final String JOGADOR_JOGANDO="<JOGANDO>";
	public static String splitDestino(String destino) {
		if(destino.contains(QUER_JOGAR)) {
			String id = destino.replace(QUER_JOGAR, "");
			return id;
		}else {
			String s = destino.replace(ESPERARANDO, "");
			return s;
		}
		
	}
	
	public static String splitNome(String nome) {
		String n = nome.replace(NOME, "");
		return n;
	}
	
}
