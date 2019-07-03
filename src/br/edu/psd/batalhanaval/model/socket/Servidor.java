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
				criarCanalComunicacaoCliente(socket);
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
				clientes.add(new Cliente(socket));
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
				
				while ((resp = entrada.readLine()) != null ) {
					
					String protocolo = resp.substring(0, 3);
					String mensagem = resp.substring(3);
					
					System.out.println("Ah");
					
					switch (protocolo) {
					
					case ProtocoloUtil.LISTA_USER_ONLINE:
						System.out.println("Entrou switch");
						for(Cliente cli: clientes) {
							saida.println(ProtocoloUtil.LISTA_USER_ONLINE + cli.getNome());
						}
						break;

					case ProtocoloUtil.USER_WIN:
						
						saida.println(ProtocoloUtil.USER_WIN);
						break;
					
					case ProtocoloUtil.USER_LOSE:
						
						saida.println(ProtocoloUtil.USER_LOSE);
						break;					

					case ProtocoloUtil.TIRO_ACERTO:
						
						saida.println(ProtocoloUtil.TIRO_ACERTO);
						break;
						
					case ProtocoloUtil.TIRO_ERRO:
						
						saida.println(ProtocoloUtil.TIRO_ERRO);
						break;
						
					case ProtocoloUtil.USER_SAIU:
						
						saida.println(ProtocoloUtil.USER_SAIU);
						break;
						
					case ProtocoloUtil.CONECTADO:
						
						saida.println(ProtocoloUtil.CONECTADO);
						break;
					
					default:
						break;
					}
					
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
		
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
