package br.edu.psd.batalhanaval.controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.Util.Enum.CodigoButtonEnum;
import br.edu.psd.batalhanaval.Util.Enum.TipoEmbarcacaoEnum;
import br.edu.psd.batalhanaval.model.CordenadasJogador;
import br.edu.psd.batalhanaval.view.TelaJogo;

public class ControllerTelaJogo extends KeyAdapter{
	private TelaJogo telaPartida;
	public ControllerTelaJogo(TelaJogo telaPartida) {
		this.telaPartida = telaPartida;
		this.telaPartida.getBtnDisparar().addActionListener(evt->dispararEvent());
		this.telaPartida.getTxtFieldY1().addKeyListener(this);
		this.telaPartida.getTxtFieldY2().addKeyListener(this);
		this.telaPartida.getTxtFieldY3().addKeyListener(this);
		this.telaPartida.getTxtFieldX1().addKeyListener(this);
		this.telaPartida.getTxtFieldX2().addKeyListener(this);
		this.telaPartida.getTxtFieldX3().addKeyListener(this);

	}
	@Override
	public void keyReleased(java.awt.event.KeyEvent evt) {  
		if(((JTextField) evt.getSource()).getToolTipText().equalsIgnoreCase("Y"))
			((JTextField) evt.getSource()).setText(((JTextField) evt.getSource()).getText().replaceAll("[^0-9]", ""));
		else {
			((JTextField) evt.getSource()).setText(((JTextField) evt.getSource()).getText().toUpperCase().replaceAll("[^A-Z]", ""));
		}
	} 
	private void dispararEvent() {//but�o disparar.
		
		if(SocketUtil.getClienteCorrente().getJogador().isSuaVez()) {//olha se est� na vez do jogador
			if(!validacaoCampos()) {//se n�o encontrou nenhum campo invalido!x1,x2,y1,y2 e etc.
				String l1 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX1().getText());
				String col1 = this.telaPartida.getTxtFieldY1().getText();
				String l2 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX2().getText());
				String col2 = this.telaPartida.getTxtFieldY2().getText();
				String l3 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX3().getText());
				String col3 = this.telaPartida.getTxtFieldY3().getText();
				
				if(SocketUtil.offiline) {//Verifica se a partida � com o computador!!!
					String msg = null;
					String r1 = "";
					String r2 = "";
					String r3 = "";
					r1 = this.verificarTiro(l1, col1,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					r2 = this.verificarTiro(l2, col2,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					r3 = this.verificarTiro(l3, col3,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					msg+=r1+"\n"+r2+"\n"+r3;
					System.out.println("NAVIOS AFUNDADOS:"+SocketUtil.getClienteCorrente().getJogador().getAcertos());
					//System.out.println("QUANT AFUNDADOS:"+(SocketUtil.getClienteCorrente().getJogador().getAcertos().split(";").length-1)+"");
					if(msg.replace("null","").length()>0) {//depois setar na propia tela por uma label!!!!
						String afundar = "";
						if((r1+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r1.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
						    afundar+= r1+"\n";
						}
						if((r2+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r2.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
							afundar+= r2+"\n";
						}
						if((r3+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
							SocketUtil.getClienteCorrente().getJogador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r3.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
							afundar += r1+"\n";
						}
						JOptionPane.showMessageDialog(null,"Resultado Disparos:\n"+msg.replace("null",""));
						//
					}
					if(SocketUtil.getClienteCorrente().getJogador().verificarSeGanhei())
					{
						JOptionPane.showMessageDialog(null,SocketUtil.getClienteCorrente().getJogador().getNome()+" Ganhou a partida!");
						SocketUtil.getClienteCorrente().getJogador().resetarCaracteristicas();
						SocketUtil.offiline = false;
						telaPartida.limparTela();
						//teria que limpar todas as telas.
						System.exit(0);
					}
					SocketUtil.getClienteCorrente().getJogador().setSuaVez(false);
					this.maquinaJoga();//a vez eh da maquina!!!
					
				}else {//o jogo nao eh offiline!
					CordenadasJogador cj = null;
					if(SocketUtil.getClienteCorrente().getDesafiado()!=null) {//ent�o ele eh o desafiado!
						String dsf = SocketUtil.getClienteCorrente().getDesafiado();
						 cj = new CordenadasJogador(l1+","+col1,l2+","+col2,l3+","+col3,dsf);
						 cj.setNomeRementente(SocketUtil.getClienteCorrente().getNome());
					}else {
						String dsf = SocketUtil.getClienteCorrente().getDesafiador();
						cj = new CordenadasJogador(l1+","+col1,l2+","+col2,l3+","+col3,dsf);
						cj.setNomeRementente(SocketUtil.getClienteCorrente().getNome());
					}
					SocketUtil.getClienteCorrente().enviarObj(cj);	
					SocketUtil.getClienteCorrente().getJogador().setSuaVez(false);
					// A partir da qui,  as coisas ir�o acontecer no else classe Cliente!
				}
			}	
		}else {
			JOptionPane.showMessageDialog(null,"Aguarde sua vez!!!");
		}
	}
	private void maquinaJoga() {
		Random r = new Random();
		String l1 = (r.nextInt(15)+1)+"";
		String col1 = (r.nextInt(15)+1)+"";
		String l2 = (r.nextInt(15)+1)+"";
		String col2 = (r.nextInt(15)+1)+"";
		String l3 = (r.nextInt(15)+1)+"";
		String col3 = (r.nextInt(15)+1)+"";

		new Thread(()->{//So pra n�o ser muito rapido!!!
			
			try {
				String msg = null;
				String r1 = "";
				String r2 = "";
				String r3 = "";
				Thread.sleep(1000);
				r1 = this.verificarTiro(l1, col1,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				Thread.sleep(1000);
				r2 =this.verificarTiro(l2, col2,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				Thread.sleep(1000);
				r3=this.verificarTiro(l3, col3,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				msg+=r1+r2+r3;
				if(msg.replace("null","").length()>0) {//depois setar na propia tela por uma label!!!!
					String afundar = "";
					if((r1+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
						SocketUtil.getComputador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r1.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
					}
					if((r2+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
						SocketUtil.getComputador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r2.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
					}
					if((r3+"").contains(CodigoButtonEnum.AFUNDAR.getDescricao())) {
						SocketUtil.getComputador().setAcertos(SocketUtil.getClienteCorrente().getJogador().getAcertos()+r3.replace(CodigoButtonEnum.AFUNDAR.getDescricao(),"")+";");
					}
				}
				System.out.println("Acertos Computador:"+SocketUtil.getComputador().getAcertos());
				if(SocketUtil.getComputador().verificarSeGanhei())
				{
					JOptionPane.showMessageDialog(null,SocketUtil.getComputador()+" Ganhou a partida!");
					SocketUtil.getComputador().resetarCaracteristicas();
					SocketUtil.offiline = false;
					telaPartida.limparTela();
					//teria que limpar todas as telas incluusive  tela criar mapa.!!.
					System.exit(0);
				}
				SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
				JOptionPane.showMessageDialog(null,"Sua vez de jogar!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;


	}
	/**
	 * Retorna uma string com nome do navio em caso de afundamento de embarcacao.
	 * */
	public static String verificarTiro(String lin, String col, Map<String,JButton> mapaButton, Map<String, String> jogo){//contra o computador!
		try {
		if(jogo.get(lin+","+col).equals(CodigoButtonEnum.POSICAO.getDescricao())) {//tiro na agua
			mapaButton.get(lin+","+col).setBackground(Color.orange);
			return CodigoButtonEnum.TIROAGUA.getDescricao();
			//this.telaPartida.getCoordenadasmapAdversario().get(l1+","+col1).setName(CodigoButtonEnum.TIROAGUA.getDescricao());
		}
		else if(!jogo.get(lin+","+col).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
			//atingiu alguma embarca��o!!!
			String vetor [] = jogo.get(lin+","+col).split(";");
			String nomeEmbarcacao = vetor[0];
			String numEmbarcacao = vetor[1];
			String posEmbarcacao = vetor[2];
			String acertou = null;
			if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.ENCOURACADO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.BLACK);
				acertou  = TipoEmbarcacaoEnum.ENCOURACADO.getValor();
			}else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.PORTAAVIAO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.RED);
				acertou  = TipoEmbarcacaoEnum.PORTAAVIAO.getValor();
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.CRUZADORES.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.yellow);
				acertou  = TipoEmbarcacaoEnum.CRUZADORES.getValor();
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.HIDROAVIAO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.GREEN);
				acertou  = TipoEmbarcacaoEnum.HIDROAVIAO.getValor();
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.SUBMARINO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.DARK_GRAY);
				acertou  = TipoEmbarcacaoEnum.SUBMARINO.getValor();
			}
			
			if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoEnum.HIDROAVIAO.getValor())) {//ent�o eh hidroaviao!!!
				int x = Integer.parseInt(lin),y = Integer.parseInt(col);
				if(!posEmbarcacao.equals("P1")) {
					if(posEmbarcacao.equals("P2")) {
						y = Integer.parseInt(col)+1;
						x = Integer.parseInt(lin)-1;
					}
					if(posEmbarcacao.equals("P3")) {
						y = Integer.parseInt(col)-1;
						x = Integer.parseInt(lin)-1;
					}
				}
				if(!mapaButton.get(x+","+y).isEnabled()) {
					return null;
				}else if(verificaSeafundouEnumbarcacao(nomeEmbarcacao, posEmbarcacao, lin+","+col, mapaButton)) {
					return nomeEmbarcacao+CodigoButtonEnum.AFUNDAR.getDescricao();	
				}
			}
			if(!mapaButton.get(lin+","+column1(posEmbarcacao, lin+","+col)).isEnabled()) {
				System.out.println("Ja esta afundada!!"+nomeEmbarcacao);
				//acertou  = TipoEmbarcacaoEnum.ENCOURACADO.getValor();
			}//verifica se ela j� esta afundada!
			else if(verificaSeafundouEnumbarcacao(nomeEmbarcacao, posEmbarcacao, lin+","+col, mapaButton)) {
				return nomeEmbarcacao+CodigoButtonEnum.AFUNDAR.getDescricao();
			}else
				return acertou;
			//tiro na agua
			//this.telaPartida.getCoordenadasmapAdversario().get(l2+","+col2).setName(CodigoButtonEnum.TIROAGUA.getDescricao());
		}//
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Corrd "+lin+","+col);
		}
		return null;
	}
	private boolean validacaoCampos() {
		System.out.println(this.telaPartida.getTxtFieldY1().getText().replace(" ","").length());

		System.out.println(this.telaPartida.getTxtFieldY1().getText().replace(" ","").compareTo("15")>0);
		System.out.println(this.telaPartida.getTxtFieldY1().getText());


		if(this.telaPartida.getTxtFieldY1().getText().replace(" ","").length()<=0 ||
				(Integer.parseInt(this.telaPartida.getTxtFieldY1().getText().replace(" ","")) > 15)){
			JOptionPane.showMessageDialog(null,"Campo Y1 invalido!!!");
			return true;
		}
		if(this.telaPartida.getTxtFieldY2().getText().replace(" ","").length()<=0 ||
				(Integer.parseInt(this.telaPartida.getTxtFieldY2().getText().replace(" ",""))>15)){
			JOptionPane.showMessageDialog(null,"Campo Y2 invalido!!!");
			return true;
		}
		if(this.telaPartida.getTxtFieldY3().getText().replace(" ","").length()<=0 ||
				(Integer.parseInt(this.telaPartida.getTxtFieldY3().getText().replace(" ",""))>15)){
			JOptionPane.showMessageDialog(null,"Campo Y3 invalido!!!");
			return true;
		}
		if(this.telaPartida.getTxtFieldX1().getText().replace(" ","").length()<=0 || this.telaPartida.getTxtFieldX1().getText().replace(" ","").length()>1 ||
				(this.telaPartida.getTxtFieldX1().getText().replace(" ","").compareTo("P")> 0) || this.telaPartida.getTxtFieldX1().getText().replace(" ","").compareTo("K")==0){
			JOptionPane.showMessageDialog(null,"Campo X1 invalido!!!");
			return true;
		}
		if(this.telaPartida.getTxtFieldX2().getText().replace(" ","").length()<=0 || this.telaPartida.getTxtFieldX2().getText().replace(" ","").length()>1 ||
				(this.telaPartida.getTxtFieldX2().getText().replace(" ","").compareTo("P")>0)|| this.telaPartida.getTxtFieldX2().getText().replace(" ","").compareTo("K")==0){
			JOptionPane.showMessageDialog(null,"Campo X2 invalido!!!");
			return true;
		}
		if(this.telaPartida.getTxtFieldX3().getText().replace(" ","").length()<=0 || this.telaPartida.getTxtFieldX3().getText().replace(" ","").length()>1 ||
				(this.telaPartida.getTxtFieldX3().getText().replace(" ","").compareTo("P")> 0) || this.telaPartida.getTxtFieldX3().getText().replace(" ","").compareTo("K")==0){
			JOptionPane.showMessageDialog(null,"Campo X3 invalido!!!");
			return true;
		}
		return false;
	}
	//	private void initEventos() {
	//		for (String key : telaPartida.getCoordenadasmapCliente().keySet()) {
	//			JButton b = telaPartida.getCoordenadasmapCliente().get(key);
	//			b.addActionListener(evt -> eventoClickMeuMapa(b));
	//		}
	//		for (String key : telaPartida.getCoordenadasmapAdversario().keySet()) {
	//			//Capturamos o valor a partir da chave
	//			JButton b = telaPartida.getCoordenadasmapAdversario().get(key);
	//			b.addActionListener(evt -> eventoClickMapaAdversario(b));
	//		}
	//	}
	//	private void eventoClickMapaAdversario(JButton b) {
	//
	//	}
	//	private void eventoClickMeuMapa(JButton b) {
	//
	//	}
	public static boolean verificaSeafundouEnumbarcacao(String navio, String numParte,String coord,Map<String,JButton>mapa) {
		String s[] = coord.split(",");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		y = column1(numParte, coord);//busca a primeira posicao do navio!
		if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.ENCOURACADO.getValor())) {
			if(mapa.get(x+","+y).getBackground()==Color.BLACK && mapa.get(x+","+(y+1)).getBackground()==Color.BLACK &&
					mapa.get(x+","+(y+2)).getBackground()==Color.BLACK && mapa.get(x+","+(y+3)).getBackground()==Color.BLACK ) {//Ent�o a embraca��o foi afundada.
				mapa.get(x+","+y).setEnabled(false);//Usado para indicar q o navio foi afundado!
				return true;
			}
		}else if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.PORTAAVIAO.getValor())) {
			if(mapa.get(x+","+y).getBackground()==Color.red && mapa.get(x+","+(y+1)).getBackground()==Color.red &&
					mapa.get(x+","+(y+2)).getBackground()==Color.red && mapa.get(x+","+(y+3)).getBackground()==Color.red && mapa.get(x+","+(y+4)).getBackground()==Color.red ) {
				mapa.get(x+","+y).setEnabled(false);//Usado para indicar q o navio foi afundado!
				return true;
			}	
		}else if(navio.equalsIgnoreCase(TipoEmbarcacaoEnum.CRUZADORES.getValor())) {
			if(mapa.get(x+","+y).getBackground()==Color.yellow && mapa.get(x+","+(y+1)).getBackground()==Color.yellow) {
				mapa.get(x+","+y).setEnabled(false);//Usado para indicar q o navio foi afundado!
				return true;
			}
		}else if(navio.equals(TipoEmbarcacaoEnum.HIDROAVIAO.getValor())) {//se possivel transformar num enum esse nome.
			if(!numParte.equals("P1")) {
				if(numParte.equals("P2")) {
					y = Integer.parseInt(s[1])+1;
					x = Integer.parseInt(s[0])-1;
				}
				if(numParte.equals("P3")) {
					y = Integer.parseInt(s[1])-1;
					x = Integer.parseInt(s[0])-1;
				}
			}
			if(mapa.get(x+","+y).getBackground()==Color.GREEN && mapa.get((x+1)+","+(y-1)).getBackground()==Color.GREEN && mapa.get((x+1)+","+(y+1)).getBackground()==Color.GREEN) {
					mapa.get(x+","+y).setEnabled(false);//Usado para indicar q o navio foi afundado!
					return true;
			}
		}else if(navio.equals(TipoEmbarcacaoEnum.SUBMARINO.getValor())) {
			if(mapa.get(x+","+y).getBackground()==Color.DARK_GRAY) {
				mapa.get(x+","+y).setEnabled(false);//Usado para indicar q o navio foi afundado!
				return true;
			}
		}
		return false;
	}
	public static int column1(String numParte,String coord) {//Captura a coluna1
		String s[] = coord.split(",");
		int y = Integer.parseInt(s[1]);
		if(!numParte.equals("P1")) {
			int numP = Integer.parseInt(numParte.replace("P",""));
			y = y - (numP - 1);//coluna da primeira posicao da embarcacao;
		}
		return y;
	}
	/*
	 * Verifica qual embarcacao foi atingida!
	 * **/
	public static void verificaLocalDaEmbarcacao(String acerto, String cord, Map<String, JButton>mapButton) {
		acerto = acerto==null?"":acerto;
		if(acerto.equalsIgnoreCase(TipoEmbarcacaoEnum.ENCOURACADO.getValor())) {
			mapButton.get(cord).setBackground(Color.BLACK);
			//acertou  = TipoEmbarcacaoEnum.ENCOURACADO.getValor();
		}else if(acerto.equalsIgnoreCase(TipoEmbarcacaoEnum.PORTAAVIAO.getValor())) {
			mapButton.get(cord).setBackground(Color.RED);
			//acertou  = TipoEmbarcacaoEnum.PORTAAVIAO.getValor();
		}
		else if(acerto.equalsIgnoreCase(TipoEmbarcacaoEnum.CRUZADORES.getValor())) {
			mapButton.get(cord).setBackground(Color.yellow);
			//acertou  = TipoEmbarcacaoEnum.CRUZADORES.getValor();
		}
		else if(acerto.equalsIgnoreCase(TipoEmbarcacaoEnum.HIDROAVIAO.getValor())) {
			mapButton.get(cord).setBackground(Color.GREEN);
			//acertou  = TipoEmbarcacaoEnum.HIDROAVIAO.getValor();
		}
		else if(acerto.equalsIgnoreCase(TipoEmbarcacaoEnum.SUBMARINO.getValor())) {
			mapButton.get(cord).setBackground(Color.DARK_GRAY);
			//acertou  = TipoEmbarcacaoEnum.SUBMARINO.getValor();
		}
		else if(acerto.equalsIgnoreCase(CodigoButtonEnum.TIROAGUA.getDescricao())) {
			mapButton.get(cord).setBackground(Color.orange);
			//acertou  = TipoEmbarcacaoEnum.SUBMARINO.getValor();
		}
	}
}
