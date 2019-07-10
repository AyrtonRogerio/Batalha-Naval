/**
 * 
 */
package br.edu.psd.batalhanaval.model.socket;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ClienteUtil;
import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.Util.Enum.CodigoButtonEnum;
import br.edu.psd.batalhanaval.Util.Enum.TipoEmbarcacaoEnum;
import br.edu.psd.batalhanaval.controller.ControllerTelaJogo;
import br.edu.psd.batalhanaval.model.CordenadasJogador;
import br.edu.psd.batalhanaval.model.CordenadasRetorno;
import br.edu.psd.batalhanaval.model.Jogador;
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
			System.out.println("Nï¿½o iniciou o Buffer!");
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


	public void criarCanalComunicacaoServer () throws IOException, ClassNotFoundException{//Canal de comunicaï¿½ï¿½o entre o cliente e o server.
		inputStream = socket.getInputStream();
		ois = new ObjectInputStream(inputStream);
		System.out.println("opa");

		try {
			Object o = null;
			resp = "";
			while ((o=ois.readObject()) != null ) {
				if(o instanceof String) {
					String resp = (String)o;
					System.out.println("Cliente recebeu:"+resp);
					if(resp.contains(ProtocoloUtil.QUER_JOGAR)) {//jogador recebe requisição pra aceitar partida
						String []temp = resp.split(" ");
						// se aceitar
						String s = "jogador "+temp[1]+" esta lhe desafiando aceita?";
						if(JOptionPane.showConfirmDialog(null,s)==0) {//se o jogador aceitar jogar
							oos.writeObject(ProtocoloUtil.ACEITAR+" "+ProtocoloUtil.splitDestino(resp));
							//SocketUtil.setAdversarioOnline(new ClienteServer(temp[1]));
							setStatus(ClienteUtil.JOGANDO);
							oos.flush();
							setDesafiador(temp[1]);
							System.out.println(temp[1]);
							telaEscolherOponente.setVisible(false);
							telaCriarMapa.setTitle(this.nome);
							telaCriarMapa.setVisible(true);
							SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);
						}else {//se não
							oos.writeObject(ProtocoloUtil.RECUSAR);
							setStatus(ClienteUtil.DISPONIVEL);
							oos.flush();
						}
					}else if(resp.contains(ProtocoloUtil.ACEITAR)) {//jogador aceita jogar partida e atualiza as telas
						setDesafiado(resp.replace(ProtocoloUtil.ACEITAR,""));
						setStatus(ClienteUtil.JOGANDO);
						SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);
						telaEscolherOponente.setVisible(false);
						telaCriarMapa.setTitle(this.nome);
						telaCriarMapa.setVisible(true);
					}else if(resp.contains(ProtocoloUtil.RECUSAR)) {//jogador recusa jogar partida
						setStatus(ClienteUtil.DISPONIVEL);
						SocketUtil.getClienteCorrente().getJogador().setEmJogo(false);
						//SocketUtil.getClienteCorrente().getJogador().setEmJogo(false);
					}else if(resp.contains(ProtocoloUtil.INICIAR)) {//avisar ao jogador que terminou de montar o mapa primeiro pra atualizar a tela
						
					}else if(resp.contains(ProtocoloUtil.ESPERARANDO)) {//avisa que o adversario ja terminou de montar o mapa 
						String []s = resp.split(" ");
						setConcluidoAdversario(true);
						//setConcluido(true);
						JOptionPane.showMessageDialog(null,"O outro jogador "+s[0].replace(ProtocoloUtil.ESPERARANDO, "")+" esta esperando");
					}else if(resp.contains(ProtocoloUtil.TERMINEI)) {//Avisa que o outro jogador terminou de montar o mapa e atualiza as telas
						setConcluidoAdversario(true);
						EmbarcacoesUtil.limparPosicionamentos();
						if(concluido && concluidoAdversario) {//se o adversario ja terminou e ele tbm
							telaCriarMapa.setVisible(false);
							telajogo.limparTela();
							telajogo.setVisible(true);
							SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);

						}
					}
					else if (resp.contains(ProtocoloUtil.LISTA_USER_ONLINE)) {//recebe jogadores online
						String s[] = resp.split(ProtocoloUtil.SEPARADOR);
						Cliente c = new Cliente(s[1]);
						jogadores.addValor(c);
					}else if(resp.contains(ProtocoloUtil.NOME)){

					}
					else if(resp.contains(ProtocoloUtil.USER_WIN)){
						//então o adiversario deste cliente ganhou!
						String []s=resp.split(ProtocoloUtil.SEPARADOR);
						JOptionPane.showMessageDialog(null, "Seu Adversario:"+s[2]+" Ganhou a partida!");
						System.exit(0);
					}
				}else {
					if(o instanceof CordenadasJogador) {//Um jogador envia para o outro, para ele consultar em seu mapa se arcetou ou não alguma embarcacao!
						
						CordenadasJogador cJogador = (CordenadasJogador)o;
						String r1 = null;//resultado tiro 1;
						String r2 = null;//resultado tiro 2;
						String r3 = null;//resultado tiro 3;
						System.out.println(cJogador.toString()+" Jogador");
						//msg ="";
						
						String l1 =  cJogador.getCord1().split(",")[0];
						String col1 = cJogador.getCord1().split(",")[1];
						String l2 = cJogador.getCord2().split(",")[0];;
						String col2 = cJogador.getCord2().split(",")[1];
						String l3 = cJogador.getCord3().split(",")[0];
						String col3 = cJogador.getCord3().split(",")[1];

						r1 = ControllerTelaJogo.verificarTiro(l1, col1,telajogo.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
						r2 = ControllerTelaJogo.verificarTiro(l2, col2,telajogo.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
						r3 = ControllerTelaJogo.verificarTiro(l3, col3,telajogo.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
						this.enviarObj(new CordenadasRetorno(cJogador.getCord1(),cJogador.getCord2(),cJogador.getCord3(),r1,r2,r3,cJogador.getNomeRementente(), cJogador.getNome()));//envia as respostas ao remetente!
						//msg = r1+r2+r3;
						
						SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
						new Thread(()->{
							//SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
							JOptionPane.showMessageDialog(null, "Sua vez de jogar!!!");
						}).start();
					}else {
						//O retorno ao cliente.
						CordenadasRetorno cJogador = (CordenadasRetorno)o;
						String afundar = "";
						System.out.println(cJogador.toString()+" Jogador");
						//verifica local de tiro, quais embarcacoes forma acertadas
						ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC1(), cJogador.getCord1(), telajogo.getCoordenadasmapAdversario());
						ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC2(), cJogador.getCord2(), telajogo.getCoordenadasmapAdversario());
						ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC3(), cJogador.getCord3(), telajogo.getCoordenadasmapAdversario());
						
						//Verifica afundamento de embarcação!
						//quando afundada a primeira posição da embarcacao fica disable, para compelmentar a logica do metodo verificar se já afundou!
						if((cJogador.getAcertoC1()+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {//olha se afundou alguma
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+cJogador.getAcertoC1().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
							ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC1().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),""), cJogador.getCord1(), telajogo.getCoordenadasmapAdversario());
							//afundar+= cJogador.getAcertoC1()+"\n";
						}
						if((cJogador.getAcertoC2()+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+cJogador.getAcertoC2().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
							ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC2().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),""), cJogador.getCord2(), telajogo.getCoordenadasmapAdversario());
							//afundar+= cJogador.getAcertoC2()+"\n";
						}
						if((cJogador.getAcertoC3()+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+cJogador.getAcertoC3().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
							ControllerTelaJogo.verificaLocalDaEmbarcacao(cJogador.getAcertoC3().replace(CodigoButtonEnum.AFUNDAR.getDescricao(),""), cJogador.getCord3(), telajogo.getCoordenadasmapAdversario());
							//afundar += cJogador.getAcertoC3()+"\n";
						} 
						String msg = "RESULTADO DOS DISPAROS:\n"+(cJogador.getAcertoC1()+"\n"+cJogador.getAcertoC2()+"\n"+cJogador.getAcertoC3());
						System.out.println(SocketUtil.getClienteCorrente().getJogador().getAcertos());
						if(SocketUtil.getClienteCorrente().getJogador().verificarSeGanhei())
						{
							JOptionPane.showMessageDialog(null,SocketUtil.getClienteCorrente().getNome()+" Ganhou a partida!");
							SocketUtil.getClienteCorrente().getJogador().resetarCaracteristicas();
							this.enviaMensagem(ProtocoloUtil.USER_WIN+ProtocoloUtil.SEPARADOR+cJogador.getNomeRemetente()+ProtocoloUtil.SEPARADOR+cJogador.getNomeDestino());//para  enviar a esse que o outro venceu!
							SocketUtil.offiline = false;
							telajogo.limparTela();
							//teria que limpar todas as telas incluusive  tela criar mapa.!!.
							System.exit(0);
						}else
						new Thread(()->{
							//SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
							JOptionPane.showMessageDialog(null,(msg).replace("null",""));
						}).start();
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
	public void enviarObj(Object mensagem) {
		try {
			System.out.println("MSG ENVIADA PARA O SERVER:"+msg);
			this.oos.writeObject(mensagem);
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
