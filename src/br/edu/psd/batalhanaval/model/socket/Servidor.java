/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.CordenadasJogador;
import br.edu.psd.batalhanaval.view.TelaServidor;



/**
 * @author ayrton
 *
 */
public class Servidor extends Thread{

	private ServerSocket serverSocket;
	private int porta;
	public String nomeServidor;
	private String resp;
	public static ArrayList<ClienteServer> clientes;
	private TelaServidor telaServidor;
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
		 telaServidor.getTextArea().append("<Ouve uma requisi��o> \r\n");
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
				System.out.println("rrrrrrrr");
				criarCanalComunicacaoCliente(socket);
				System.out.println("dps do canal de comu do serv");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Cria o canal de recep��o de mensagens dos clientes entre servidor e cliente.
	 * */
	public void criarCanalComunicacaoCliente(Socket socket) {//Canal de comunica��o entre o cliente e o server.
		new Thread(() -> {
			try {
				String resp = "";
				ClienteServer cl = new ClienteServer(socket);
				clientes.add(cl);
				System.out.println(clientes.size());
				resp="";
				Object o = null;
		
				ObjectInputStream ois = cl.getOis();
				ObjectOutputStream ous = cl.getOos();
	
				System.out.println("dps do print read serv");
		
				while ((o=ois.readObject()) != null ) {
					if(o instanceof String) {
						resp = (String)o;
						System.out.println("Server recebeu:"+resp);
						if(resp.contains(ProtocoloUtil.LISTA_USER_ONLINE)) {//depois fazer a logica de enviar os clientes com ip e porta e nome!
							//ous.writeObject("Server disse: RECEBI ESSA SOLICITACAO"+ProtocoloUtil.LISTA_USER_ONLINE);
							//break;O brak estava pausando o while
						}else if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {
							enviarParaDestino(resp);
							//break;O brak estava pausando o while
						}
						else if(resp.contains(ProtocoloUtil.NOME)) {
							//enviarParaDestino(resp);
							String s[] = resp.split(";");
							clientes.get(clientes.size()-1).setNome(s[1]);
							int cont = 0;
							for(ClienteServer c:clientes) {
								System.out.println(c.getNome());
								if(cont!=(clientes.size()-1)) {
									c.getOos().writeObject(ProtocoloUtil.NOME+" "+clientes.get(clientes.size()-1).getNome());
									ous.flush();
								}
								cont++;
							}
							if(clientes.size()>1) {
								for(ClienteServer c:clientes) {
									if(!c.getNome().equals(clientes.get(clientes.size()-1).getNome())) {
										clientes.get(clientes.size()-1).getOos().writeObject(ProtocoloUtil.NOME+" "+c.getNome());
									}
								}
							}

						}else if(resp.contains(ProtocoloUtil.USER_WIN)) {
							//	saida.println(ProtocoloUtil.USER_WIN);
							//break;
						}else if(resp.contains(ProtocoloUtil.USER_LOSE)) {
						//	saida.println(ProtocoloUtil.USER_LOSE);
						////	break;					

						}else if(resp.contains(ProtocoloUtil.TIRO_ACERTO)) {
						//	saida.println(ProtocoloUtil.TIRO_ACERTO);
						//	break;
						}else if(resp.contains(ProtocoloUtil.TIRO_ERRO)) {
						//	saida.println(ProtocoloUtil.TIRO_ERRO);
						//	break;
						}else if(resp.contains(ProtocoloUtil.USER_SAIU)) {
						//	saida.println(ProtocoloUtil.USER_SAIU);
						//	break;
						}else if(resp.contains(ProtocoloUtil.CONECTADO)){
						//	saida.println(ProtocoloUtil.CONECTADO);
						//	break;
						}else if(resp.contains(ProtocoloUtil.ACEITAR)) {
							aceitarDesafio(resp);
						}else if(resp.contains(ProtocoloUtil.ESPERARANDO)) {
							enviarParaDestino(resp);
						}else if(resp.contains(ProtocoloUtil.INICIAR)) {
							for(ClienteServer c:clientes) {
								if(c.getNome().equals(resp.replace(ProtocoloUtil.INICIAR, ""))) {
									c.getOos().writeObject(ProtocoloUtil.INICIAR);
								}
							}
						}

					}else {
						CordenadasJogador cJogador = (CordenadasJogador)o;
						for(ClienteServer c: clientes) {
							if(c.getNome().equals(cJogador.getNome())) {//falta dizer pra quem vai enviar as codernadas
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
