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
	
	public static final String CONECTADO = "CON"; // 
	public static final String LISTA_USER_ONLINE = "LUO"; // lista user online
	public static final String USER_SAIU = "UOF"; // notifica users quando sai
	public static final String USER_WIN = "UWG"; // Usuário q ganhou o jogo
	public static final String USER_LOSE = "ULG"; // usuário q perdeu o jogo
	public static final String TIRO_ERRO = "TER"; // tiro errado
	public static final String TIRO_ACERTO = "TAC"; //tiro certo
	
}
