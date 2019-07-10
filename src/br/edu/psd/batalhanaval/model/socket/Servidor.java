/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.ssl.SSLServerSocketFactory;
import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.CordenadasJogador;
import br.edu.psd.batalhanaval.model.CordenadasRetorno;
import br.edu.psd.batalhanaval.view.TelaServidor;



/**
 * @author ayrton
 *
 */
public class Servidor extends Thread{

	private SSLServerSocketFactory serverSocketFactory;
	private ServerSocket serverSocket;
	private int porta;
	public String nomeServidor;
	private String resp;
	public static ArrayList<ClienteServer> clientes;
	private TelaServidor telaServidor;
	private ClienteServer cl;
	private boolean temp;//Respónsavel por determinar se o jogador existe ou não
	/**
	 * @param serverSocket
	 * @param porta
	 * @param nomeServidor
	 * @throws IOException 
	 */
	public Servidor(int porta, String nomeServidor,TelaServidor servidor) throws IOException {
		super();
		this.serverSocket = new ServerSocket(porta);
		this.porta = porta;
		this.nomeServidor = nomeServidor;
		clientes = new ArrayList<ClienteServer>();
		telaServidor = servidor;

	}
	public Socket ouvirPorta() throws IOException {
		Socket socket = serverSocket.accept();
		telaServidor.getTextArea().append("<Houve uma requisiï¿½ï¿½o> \r\n");
		telaServidor.getTextArea().append("Dados do socket: "+ socket.toString() + "\r\n\n");
		telaServidor.getTextArea().requestFocusInWindow();
		return socket;
	}
	@Override
	/**
	 * Recebe os clientes.
	 * */
	public void run() {
		Socket socket = null;
		try {
			while((socket = ouvirPorta()) != null) {
				criarCanalComunicacaoCliente(socket);
				System.out.println("dps do canal de comu do serv");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Cria o canal de recepï¿½ï¿½o de mensagens dos clientes entre servidor e cliente.
	 * */
	public void criarCanalComunicacaoCliente(Socket socket) {//Canal de comunicaï¿½ï¿½o entre o cliente e o server.
		new Thread(() -> {
			try {
				String resp = "";
				 cl = new ClienteServer(socket);
				
				clientes.add(cl);
				System.out.println(clientes.size());
				resp="";
				Object o = null;

				ObjectInputStream ois = cl.getOis();
				ObjectOutputStream ous = cl.getOos();

				System.out.println("dps do print read serv");
//				if(!verificarSeJogadorExiste()) {
//					
//				}
				while ((o=ois.readObject()) != null ) {
					if(o instanceof String) {
						resp = (String)o;
						System.out.println("Server recebeu:"+resp);
						if(resp.contains(ProtocoloUtil.LISTA_USER_ONLINE)) {//
							if(!temp) {//se temp for falso significa que o jogador não exite o nome dele e setado e ele avisa aos demais
								
								System.out.println("Cl:"+cl.getNome());
								for(ClienteServer c:clientes) {//retorna pra mim os outros que jï¿½ estavam online
									if(!c.getNome().equals(cl.getNome())) {
										cl.getOos().writeObject(ProtocoloUtil.LISTA_USER_ONLINE+ProtocoloUtil.SEPARADOR+c.getNome());
									}
								}
								for(ClienteServer c:clientes) {//retorna pros outros que eu fiquei online!
									if(!c.getNome().equals(cl.getNome())) {
										c.getOos().writeObject(ProtocoloUtil.LISTA_USER_ONLINE+ProtocoloUtil.SEPARADOR+cl.getNome());
										ous.flush();
									}	
								}
								cl.getOos().writeObject(ProtocoloUtil.JOGADOR_NAO_EXISTE);//avisa que ele ja esta conectado pra atualizar sua tela
							}else {//se não é removido do array
								cl.getOos().writeObject(ProtocoloUtil.JOGADOR_EXISTE);
								int i=0;
								for(ClienteServer c:clientes) {
									if(c.getNome()==null) {
										clientes.remove(i);
										break;
									}
									i++;
								}
								
							}
							temp=false;
							
						}else if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {//enviar requisiï¿½ï¿½o para jogar partida
							enviarParaDestino(resp);
							//break;O brak estava pausando o while
						}
						else if(resp.contains(ProtocoloUtil.NOME)) {//Registra o nome do jogador no server
							//enviarParaDestino(resp);
							String s[] = resp.split(ProtocoloUtil.SEPARADOR);
							
							if(!verificarSeJogadorExiste(s[1])) {
								clientes.get(clientes.size()-1).setNome(s[1]);
								temp=false;
							}else {
								temp=true; 
							}	
							
							
						}else if(resp.contains(ProtocoloUtil.USER_WIN)) {
							String []s=resp.split(ProtocoloUtil.SEPARADOR);
							for(ClienteServer c:clientes) {
								if(c.getNome().equals(s[1])) {
									c.getOos().writeObject(resp);
									break;
								}
							}
						}else if(resp.contains(ProtocoloUtil.USER_SAIU)) {
							//	saida.println(ProtocoloUtil.USER_SAIU);
							//	break;
							System.out.println("Cl:"+cl.getNome());
							for(ClienteServer c:clientes) {//retorna pra mim os outros que jï¿½ estavam online
								if(!c.getNome().equals(cl.getNome())) {
									cl.getOos().writeObject(ProtocoloUtil.USER_SAIU+ProtocoloUtil.SEPARADOR+c.getNome());
								}
							}
							for(ClienteServer c:clientes) {//retorna pros outros que eu fiquei online!
								if(!c.getNome().equals(cl.getNome())) {
									c.getOos().writeObject(ProtocoloUtil.USER_SAIU+ProtocoloUtil.SEPARADOR+cl.getNome());
									ous.flush();
								}	
							}
						}else if(resp.contains(ProtocoloUtil.CONECTADO)){
							//	saida.println(ProtocoloUtil.CONECTADO);
							//	break;
						}else if(resp.contains(ProtocoloUtil.TERMINEI)) {//quando o ultimo jogador terminar de montar o mapa a requisiï¿½ï¿½o vem pra ca
							String []s=resp.split(ProtocoloUtil.SEPARADOR);

							for(ClienteServer c:clientes) {
								if(c.getNome().equals(s[1])) {
									c.getOos().writeObject(resp);
									break;
								}
							}
						}else if(resp.contains(ProtocoloUtil.JOGADOR_JOGANDO)){
							for(ClienteServer c:clientes) {//retorna pros outros que eu estou em uma partida!
								if(!c.getNome().equals(resp.replace(ProtocoloUtil.JOGADOR_JOGANDO,""))) {
									c.getOos().writeObject(ProtocoloUtil.JOGADOR_JOGANDO+ProtocoloUtil.SEPARADOR+resp.replace(ProtocoloUtil.JOGADOR_JOGANDO,""));
									c.getOos().flush();
									System.out.println("oklllllllll");
									
								}	
							}
						}else if(resp.contains(ProtocoloUtil.JOGADOR_DISPONIVEL)) {///
								for(ClienteServer c:clientes) {//retorna pros outros que eu estou em uma partida!
									if(!c.getNome().equals(resp.replace(ProtocoloUtil.JOGADOR_DISPONIVEL,""))) {
										c.getOos().writeObject(ProtocoloUtil.JOGADOR_DISPONIVEL+ProtocoloUtil.SEPARADOR+resp.replace(ProtocoloUtil.JOGADOR_JOGANDO,""));
										c.getOos().flush();
										
									}	
								}
							
						}
						else if(resp.contains(ProtocoloUtil.ACEITAR)) {//aceito jogar a partida
							aceitarDesafio(resp);
						}else if(resp.contains(ProtocoloUtil.ESPERARANDO)) {//jogador avisa pro outro q ja terminou de montar seu mapa
							enviarParaDestino(resp);
						}
						//						else if(resp.contains(ProtocoloUtil.INICIAR)) {
						//							for(ClienteServer c:clientes) {
						//								if(c.getNome().equals(resp.replace(ProtocoloUtil.INICIAR, ""))) {
						//									c.getOos().writeObject(ProtocoloUtil.INICIAR);
						//								}
						//							}
						//						}

					}else {//recebe as coordenadas e repassa pro adiversario.
						String nome="";
						if(o instanceof CordenadasJogador) {
							CordenadasJogador cJogador = (CordenadasJogador)o;
							nome = ((CordenadasJogador) o).getNome();
						}else {
							CordenadasRetorno cJogador = (CordenadasRetorno)o;
							nome = ((CordenadasRetorno) o).getNomeDestino();
						}
							//						CordenadasJogador cJogador = (CordenadasJogador)o;
							for(ClienteServer c: clientes) {
								if(c.getNome().equals(nome)) {
									System.out.println(c.getNome());
									c.getOos().writeObject(o);
									c.getOos().flush();
									break;
								}
							}
						
					}


				}
				System.out.println("Serve acabou!");
			} catch (Exception e) {
				e.printStackTrace();
				
				//pegar a exceÃ§Ã£o quando o cliente sair e avisar aos outros
				if(cl == null) {
					
				}
				
			}

		}).start();

	}
	//enviar msg para a pessoa que quer desafiar
	public void enviarParaDestino(String msg) throws IOException {
		String []temp = msg.split(" ");
		for(ClienteServer c: clientes) {
			if(c.getNome().equals(ProtocoloUtil.splitDestino(temp[0]))) {
				System.out.println(c.getNome());
				c.getOos().writeObject(msg);
				c.getOos().flush();
				break;
			}
		}
	}

	public void aceitarDesafio(String msg) throws IOException {
		String []temp = msg.split(" ");
		System.out.println(temp[1]);
		System.out.println(temp[2]);
		System.out.println(clientes.get(1).getNome());
		for(ClienteServer c: clientes) {
			if(temp[2].equals(c.getNome())) {
				System.out.println(c.getNome());
				c.getOos().writeObject(ProtocoloUtil.ACEITAR+temp[1]);
				c.getOos().flush();
				break;
			}
		}
	}
	
	public boolean verificarSeJogadorExiste(String nome) {
		for(ClienteServer c:clientes) {
			if(c.getNome()==null) {
				continue;
			}
			if(c.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the serverSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @param serverSocket the serverSocket to set
	 */
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * @return the porta
	 */
	public int getPorta() {
		return porta;
	}

	/**
	 * @param porta the porta to set
	 */
	public void setPorta(int porta) {
		this.porta = porta;
	}

	/**
	 * @return the nomeServidor
	 */
	public String getNomeServidor() {
		return nomeServidor;
	}

	/**
	 * @param nomeServidor the nomeServidor to set
	 */
	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}



}
