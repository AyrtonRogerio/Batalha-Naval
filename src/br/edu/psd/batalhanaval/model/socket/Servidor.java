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
		 telaServidor.getTextArea().append("<Ouve uma requisiÁ„o> \r\n");
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
	 * Cria o canal de recepÁ„o de mensagens dos clientes entre servidor e cliente.
	 * */
	public void criarCanalComunicacaoCliente(Socket socket) {//Canal de comunicaÁ„o entre o cliente e o server.
		new Thread(() -> {
			try {
				clientes.add(new Cliente(socket));
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
				while ((resp = entrada.readLine()) != null ) {
					System.out.println("Ah");
					/**
					 * Mostrar vit√≥rias 
//					 */
//					if(resp.substring(0, 2).equals("V:")) {
//						
//					}
//					
//					/**
//					 * Mostrar clientes conectados
//					 */
//					if(resp.substring(0, 2).equals("C:")) {
//						
//					}
//					
//					/**
//					 * Indicar os tiros errados
//					 */
//					if(resp.substring(0, 2).equals("E:")) {
//						
//					}
//					
//					
//					/**
//					 * Mostrar tiros acertados
//					 */
//					if(resp.substring(0, 2).equals("A:")) {
//						
//					}
					
					
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
