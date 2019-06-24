/**
 * 
 */
package br.edu.psd.batalhanaval.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayrton
 *
 */
public class Servidor extends Thread implements Runnable {

	private ServerSocket serverSocket;
	private int porta;
	public String nomeServidor;
	private BufferedReader entrada;
	private PrintWriter saida;
	private String resp;
	private List<Cliente> clientes;
	
	/**
	 * @param serverSocket
	 * @param porta
	 * @param nomeServidor
	 * @throws IOException 
	 */
	public Servidor(ServerSocket serverSocket, int porta, String nomeServidor) throws IOException {
		super();
		this.serverSocket = new ServerSocket(porta);
		this.porta = porta;
		this.nomeServidor = nomeServidor;
	}
	
	public Socket ouvirPorta() throws IOException {
		 Socket socket = serverSocket.accept();
		 return socket;
	}
	

	public void recebeMensagem(Socket socket) {
		
		clientes = new ArrayList<Cliente>();
		
		
		new Thread(() -> {
			
			try {
				
				entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				saida = new PrintWriter(socket.getOutputStream(), true);
				
				
				while ((resp = entrada.readLine()) != null ) {
					
					/**
					 * Mostrar vitórias 
					 */
					if(resp.substring(0, 2).equals("V:")) {
						
					}
					
					/**
					 * Mostrar clientes conectados
					 */
					if(resp.substring(0, 2).equals("C:")) {
						
					}
					
					/**
					 * Indicar os tiros errados
					 */
					if(resp.substring(0, 2).equals("E:")) {
						
					}
					
					
					/**
					 * Mostrar tiros acertados
					 */
					if(resp.substring(0, 2).equals("A:")) {
						
					}
					
					
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		});
		
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
