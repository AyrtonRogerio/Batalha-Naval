/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.TableModel;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;
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
	private ArrayList<Cliente> clientes;
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
		clientes = new ArrayList<Cliente>();
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
				
				System.out.println("canal serv");
				resp="";
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println("dps do buff read do serv");
				PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
				System.out.println("dps do print read serv");
				System.out.println( entrada.readLine());
				System.out.println(" to string entrada " + entrada.toString());
				while ((resp = entrada.readLine()) != null ) {
					
//					String protocolo = resp.substring(0, 3);
//					String mensagem = resp.substring(3);
					
					System.out.println("Ah");
		
					if(resp.contains(ProtocoloUtil.LISTA_USER_ONLINE)) {
						System.out.println("Entrou switch");
						for(Cliente cli: clientes) {
							saida.println(ProtocoloUtil.LISTA_USER_ONLINE + cli.getNome());
						}
						break;
					}else if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {
						enviarParaDestino(resp);
						break;
					}else if(resp.contains(ProtocoloUtil.USER_WIN)) {
						saida.println(ProtocoloUtil.USER_WIN);
						break;
					}else if(resp.contains(ProtocoloUtil.USER_LOSE)) {
						saida.println(ProtocoloUtil.USER_LOSE);
						break;					

					}else if(resp.contains(ProtocoloUtil.TIRO_ACERTO)) {
						saida.println(ProtocoloUtil.TIRO_ACERTO);
						break;
					}else if(resp.contains(ProtocoloUtil.TIRO_ERRO)) {
						saida.println(ProtocoloUtil.TIRO_ERRO);
						break;
					}else if(resp.contains(ProtocoloUtil.USER_SAIU)) {
						saida.println(ProtocoloUtil.USER_SAIU);
						break;
					}else if(resp.contains(ProtocoloUtil.CONECTADO)){
						saida.println(ProtocoloUtil.CONECTADO);
						break;
					}else {
						clientes.add(new Cliente(socket));
						clientes.get(clientes.size()-1).setNome(resp);
						int cont = 0;
						for(Cliente c:clientes) {
							
							if(cont!=(clientes.size()-1)) {
								c.getEscritorDeBuffer().println(resp.getBytes());
							}
							cont++;
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
		
	}
	//enviar msg para a pessoa que quer desafiar
	public void enviarParaDestino(String msg) throws IOException {
		for(Cliente c: clientes) {
			if(c.getNome().equals(clientes.get(ProtocoloUtil.splitDestino(msg)).getNome())) {
				c.getEscritorDeBuffer().println(msg.getBytes());
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
