/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.psd.batalhanaval.model.Jogador;

/**
 * @author ayrton
 *
 */
/**
 * @author p
 *
 */
public class Cliente implements Runnable{

	private Socket socket;
	private BufferedReader bufferDeEntrada;//Caixa de entrada!
	private PrintStream escritorDeBuffer;//Escreve na caixa de entrada
//	private ArrayList<ServerSocket>servidores=new ArrayList<>();//p-2-p
	private int portaServerExterno = 9000;
	private String ipServerExterno = "localhost";
	private String nome;
	private Jogador jogador;
	private String resp;
	private String msg;

	public Cliente() throws UnknownHostException, IOException {
		this.socket = new Socket(this.ipServerExterno,this.portaServerExterno);
		this.iniciarBufferPrint();
	}

	public Cliente(int portaServerExterno, String ipServerExterno) throws UnknownHostException, IOException {
		super();
		this.portaServerExterno = portaServerExterno;
		this.ipServerExterno = ipServerExterno;
		this.socket = new Socket(this.ipServerExterno,this.portaServerExterno);
		this.iniciarBufferPrint();
	}

	public Cliente(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
		this.ipServerExterno = socket.getInetAddress().getHostName();//ipdoserver
		this.portaServerExterno = socket.getPort();
		this.iniciarBufferPrint();
	}
	private void iniciarBufferPrint() {
		try {
			this.bufferDeEntrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.escritorDeBuffer = new PrintStream(this.socket.getOutputStream());
	
		} catch (IOException e) {
		
			e.printStackTrace();
			System.out.println("N�o iniciou o Buffer!");
			System.exit(0);
		} 
	}
	public void run() {
		this.criarCanalComunicacaoServer();
	}
	
	
	public void criarCanalComunicacaoServer (){//Canal de comunica��o entre o cliente e o server.
		BufferedReader entrada;
		try {
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			resp = "";
			while ((resp = entrada.readLine()) != null ) {

				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	
	public void enviaMensagem(String mensagem) {
		this.msg = mensagem;
		escritorDeBuffer.println(this.msg);
	}
	
	
	public void sair() throws IOException {
		
		bufferDeEntrada.close();
		escritorDeBuffer.close();
		socket.close();
		
	}
	
	
	public String escutaMensagem() throws IOException {
		return resp = bufferDeEntrada.readLine();
	}

	
	public Socket getSocket() {
		return socket;
	}
	
	
	
	public int getPortaServerExterno() {
		this.portaServerExterno = socket.getPort();
		return this.portaServerExterno;
	}

	public void setPortaServerExterno(int portaServerExterno) {
		this.portaServerExterno = portaServerExterno;
	}
	
	public String getIpServerExterno() {
		ipServerExterno = socket.getInetAddress().getHostName();
		return ipServerExterno;
	}
	
	public void setIpServerExterno(String ipServerExterno) {
		this.ipServerExterno = ipServerExterno;
	}
	
	public BufferedReader getBufferDeEntrada() {
		return bufferDeEntrada;
	}

	public void setBufferDeEntrada(BufferedReader bufferDeEntrada) {
		this.bufferDeEntrada = bufferDeEntrada;
	}

	public PrintStream getEscritorDeBuffer() {
		return escritorDeBuffer;
	}

	public void setEscritorDeBuffer(PrintStream escritorDeBuffer) {
		this.escritorDeBuffer = escritorDeBuffer;
	}
	public String getNome() {
		return nome;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

}
