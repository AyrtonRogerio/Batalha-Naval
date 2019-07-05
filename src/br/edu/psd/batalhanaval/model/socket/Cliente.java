/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ClienteUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.Jogador;
import br.edu.psd.batalhanaval.model.TableModel;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;

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
	private PrintWriter escritorDeBuffer;//Escreve na caixa de entrada
//	private ArrayList<ServerSocket>servidores=new ArrayList<>();//p-2-p
	private int portaServerExterno = 9000;
	private String ipServerExterno = "localhost";
	private String nome;
	private Jogador jogador;
	private String resp;
	private String msg;
	private String status;//para verificar se o usuario ja esta em uma partida
	private String statusDeJogo;// para saber se o jogador terminou de montar o mapa
	private TableModel jogadores;

	public Cliente() throws UnknownHostException, IOException {
		this.socket = new Socket(this.ipServerExterno,this.portaServerExterno);
		this.jogador = new Jogador();
		this.iniciarBufferPrint();
	}
	
	public Cliente(int portaServerExterno, String ipServerExterno,String nome,TelaEscolherOponente telaEscolherOponente) throws UnknownHostException, IOException {
		super();
		this.portaServerExterno = portaServerExterno;
		this.ipServerExterno = ipServerExterno;
		this.socket = new Socket(this.ipServerExterno,this.portaServerExterno);
		this.jogador = new Jogador();
		this.nome = nome;
		jogadores = (TableModel)telaEscolherOponente.getTable().getModel();
		this.iniciarBufferPrint();
	}

	public Cliente(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
		this.ipServerExterno = socket.getInetAddress().getHostName();//ipdoserver
		this.portaServerExterno = socket.getPort();
		this.jogador = new Jogador();
		//this.iniciarBufferPrint();
	}
	public Cliente(String nome) {
		this.nome = nome;
		this.jogador = new Jogador();
	}

	private void iniciarBufferPrint() {
		try {
			
			this.escritorDeBuffer = new PrintWriter(this.socket.getOutputStream(), true);
			this.escritorDeBuffer.println(this.nome.getBytes());
		} catch (IOException e) {
		
			e.printStackTrace();
			System.out.println("N�o iniciou o Buffer!");
			System.exit(0);
		} 
	}
	public void run() {
		try {
			this.criarCanalComunicacaoServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void criarCanalComunicacaoServer () throws IOException{//Canal de comunica��o entre o cliente e o server.
		System.out.println("opa");
		this.bufferDeEntrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		BufferedReader entrada;
		try {
			entrada = this.bufferDeEntrada;
			resp = "";
			while ((resp = entrada.readLine()) != null ) {
				System.out.println(resp);
				if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {
					// se aceitar
					if(JOptionPane.showConfirmDialog(null, "jogador "+this.escritorDeBuffer.getClass().getName()+" esta lhe desafiando aceita?")==0) {
						escritorDeBuffer.println(ProtocoloUtil.ACEITAR.getBytes() );
					}else {//se n�o
						escritorDeBuffer.println(ProtocoloUtil.RECUSAR.getBytes());
					}

				}else if(resp.contains(ProtocoloUtil.ACEITAR)) {
					this.setStatus(ClienteUtil.JOGANDO);
				}else if(resp.contains(ProtocoloUtil.RECUSAR)) {
					this.setStatus(ClienteUtil.DISPONIVEL);
				}else if(resp.contains(ProtocoloUtil.INICIAR)) {
					this.setStatusDeJogo(ProtocoloUtil.INICIAR);
					//falta passar as cordenadas
				}else {
					Cliente c = new Cliente(resp);
					c.setStatus(ClienteUtil.DISPONIVEL);
					jogadores.addValor(c);
				}
				
				
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

	public PrintWriter getEscritorDeBuffer() {
		return escritorDeBuffer;
	}

	public void setEscritorDeBuffer(PrintWriter escritorDeBuffer) {
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

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDeJogo() {
		return statusDeJogo;
	}

	public void setStatusDeJogo(String statusDeJogo) {
		this.statusDeJogo = statusDeJogo;
	}

	
}
