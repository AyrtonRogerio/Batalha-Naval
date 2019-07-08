/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ClienteUtil;
import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.model.CordenadasJogador;
import br.edu.psd.batalhanaval.model.Jogador;
import br.edu.psd.batalhanaval.model.MontadorDeMapa;
import br.edu.psd.batalhanaval.model.TableModel;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;
import br.edu.psd.batalhanaval.view.TelaJogo;

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
	//private PrintWriter escritorDeBuffer;//Escreve na caixa de entrada
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
	private InputStream inputStream;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OutputStream outputStream;
	private TelaEscolherOponente telaEscolherOponente;
	private TelaCriarMapa telaCriarMapa;
	private String desafiado;//guardar o nome do jogador que vc desafiou e ele aceitou
	private String desafiador;//guardar o nome do jogador 
	private boolean concluido,concluidoAdversario;//identificar quem terminou de montar o mapa primeiro
	private TelaJogo telajogo;
	
	
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
		this.jogador.setNome(nome);
		jogadores = (TableModel)telaEscolherOponente.getTable().getModel();
		this.iniciarBufferPrint();
	}

	public Cliente(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
		this.ipServerExterno = socket.getInetAddress().getHostName();//ipdoserver
		this.portaServerExterno = socket.getPort();
		this.jogador = new Jogador();
		this.iniciarBufferPrint();
	}
	public Cliente(String nome) {
		this.nome = nome;
		this.jogador = new Jogador();
	}

	private void iniciarBufferPrint() {
		try {
			outputStream = this.socket.getOutputStream();
			oos = new ObjectOutputStream(outputStream);
			
			} catch (IOException e) {
		
			e.printStackTrace();
			System.out.println("N�o iniciou o Buffer!");
			System.exit(0);
		} 
	}
	public void run() {
		try {
			System.out.println("Cliente iniciar");
			this.criarCanalComunicacaoServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void criarCanalComunicacaoServer () throws IOException, ClassNotFoundException{//Canal de comunica��o entre o cliente e o server.
		inputStream = socket.getInputStream();
		ois = new ObjectInputStream(inputStream);
		System.out.println("opa");

		try {
			Object o = null;
			resp = "";
			while ((o=ois.readObject()) != null ) {
				
				if(o instanceof String) {
					resp = (String)o;
					System.out.println("Cliente recebeu:"+resp);
					if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {
						String []temp = resp.split(" ");
						// se aceitar
						String s = "jogador "+temp[1]+" esta lhe desafiando aceita?";
						if(JOptionPane.showConfirmDialog(null,s)==0) {
							oos.writeObject(ProtocoloUtil.ACEITAR+" "+ProtocoloUtil.splitDestino(resp));
							oos.flush();
							setDesafiador(temp[1]);
							telaEscolherOponente.setVisible(false);
							telaCriarMapa.setTitle(this.nome);
							telaCriarMapa.setVisible(true);
							SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);
						}else {//se n�o
							oos.writeObject(ProtocoloUtil.RECUSAR);
							oos.flush();
						}
					}else if(resp.contains(ProtocoloUtil.ACEITAR)) {
						setDesafiado(resp.replace(ProtocoloUtil.ACEITAR,""));
						setStatus(ClienteUtil.JOGANDO);
						telaEscolherOponente.setVisible(false);
						telaCriarMapa.setTitle(this.nome);
						telaCriarMapa.setVisible(true);
					}else if(resp.contains(ProtocoloUtil.RECUSAR)) {
						setStatus(ClienteUtil.DISPONIVEL);
					}else if(resp.contains(ProtocoloUtil.INICIAR)) {
						setStatusDeJogo(ProtocoloUtil.INICIAR);
						telaCriarMapa.setVisible(false);
						telajogo.limparTela();
						telajogo.setVisible(true);
					}else if(resp.contains(ProtocoloUtil.ESPERARANDO)) {
						String []s = resp.split(" ");
						setConcluidoAdversario(true);
						JOptionPane.showConfirmDialog(null,"O jogador "+s[0].replace(ProtocoloUtil.ESPERARANDO, "")+"esta esperando");
					}
					else if (resp.contains(ProtocoloUtil.LISTA_USER_ONLINE)) {
	
					}else if(resp.contains(ProtocoloUtil.NOME)){
						String s[] = resp.split(" ");
						Cliente c = new Cliente(s[1]);
						jogadores.addValor(c);
					}
				}else {
					CordenadasJogador cJogador = (CordenadasJogador)o;
					MontadorDeMapa.montarMapaAdversarioOnline(cJogador.getCoordenadas());
					EmbarcacoesUtil.limparPosicionamentos();
					if(concluido&&concluidoAdversario) {
						telaCriarMapa.setVisible(false);
						telajogo.limparTela();
						telajogo.setVisible(true);
						SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);
						if(desafiado!=null) {
							oos.writeObject(ProtocoloUtil.INICIAR+desafiado);
						}else {
							oos.writeObject(ProtocoloUtil.INICIAR+desafiador);
						}
					
					}
					
				}
				
				
			}
			System.out.println("Sai cliente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	
	public TelaJogo getTelajogo() {
		return telajogo;
	}

	public void setTelajogo(TelaJogo telajogo) {
		this.telajogo = telajogo;
	}

	public void enviaMensagem(String mensagem) {
		this.msg = mensagem;
		try {
			System.out.println("MSG ENVIADA PARA O SERVER:"+msg);
			this.oos.writeObject(this.msg);
			//this.oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sair() throws IOException {
		bufferDeEntrada.close();
		//escritorDeBuffer.close();
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

	public ObjectInputStream getOis() {
		return ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public TableModel getJogadores() {
		return jogadores;
	}

	public TelaEscolherOponente getTelaEscolherOponente() {
		return telaEscolherOponente;
	}

	public void setTelaEscolherOponente(TelaEscolherOponente telaEscolherOponente) {
		this.telaEscolherOponente = telaEscolherOponente;
	}

	public TelaCriarMapa getTelaCriarMapa() {
		return telaCriarMapa;
	}

	public void setTelaCriarMapa(TelaCriarMapa telaCriarMapa) {
		this.telaCriarMapa = telaCriarMapa;
	}

	public String getDesafiado() {
		return desafiado;
	}

	public void setDesafiado(String desafiado) {
		this.desafiado = desafiado;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public boolean isConcluidoAdversario() {
		return concluidoAdversario;
	}

	public void setConcluidoAdversario(boolean concluidoAdversario) {
		this.concluidoAdversario = concluidoAdversario;
	}

	public String getDesafiador() {
		return desafiador;
	}

	public void setDesafiador(String desafiador) {
		this.desafiador = desafiador;
	}

	
	
}
