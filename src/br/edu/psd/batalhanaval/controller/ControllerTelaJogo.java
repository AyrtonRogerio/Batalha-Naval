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
import br.edu.psd.batalhanaval.Util.Enum.TipoEmbarcacaoUtil;
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
	private void dispararEvent() {
		if(SocketUtil.getClienteCorrente().getJogador().isSuaVez()) {
			if(!validacaoCampos()) {//se não encontrou nenhum campo invalido!
				String l1 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX1().getText());
				String col1 = this.telaPartida.getTxtFieldY1().getText();
				String l2 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX2().getText());
				String col2 = this.telaPartida.getTxtFieldY2().getText();
				String l3 = SocketUtil.converteLetraemNumero(this.telaPartida.getTxtFieldX3().getText());
				String col3 = this.telaPartida.getTxtFieldY3().getText();

				if(SocketUtil.offiline) {//Verifica se a partida é com oo computador!!!
					this.verificarTiro(l1, col1,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					this.verificarTiro(l2, col2,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					this.verificarTiro(l3, col3,this.telaPartida.getCoordenadasmapAdversario(), SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
					SocketUtil.getClienteCorrente().getJogador().setSuaVez(false);
					//a maquina joga!
					this.maquinaJoga();//a vez eh da maquina!!!
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
		new Thread(()->{//So pra não ser muito rapido!!!
			try {
				Thread.sleep(1000);
				this.verificarTiro(l1, col1,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				Thread.sleep(1000);
				this.verificarTiro(l2, col2,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				Thread.sleep(1000);
				this.verificarTiro(l3, col3,this.telaPartida.getCoordenadasmapCliente(), SocketUtil.getClienteCorrente().getJogador().getCoordenadasMeuJogoAtual());
				SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
				JOptionPane.showMessageDialog(null, "Sua vez de jogar!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();;
	
		
	}
	private void verificarTiro(String lin, String col, Map<String,JButton> mapaButton, Map<String, String> jogo) {//contra o computador!
		if(jogo.get(lin+","+col).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
			mapaButton.get(lin+","+col).setBackground(Color.orange);//tiro na agua
			//this.telaPartida.getCoordenadasmapAdversario().get(l1+","+col1).setName(CodigoButtonEnum.TIROAGUA.getDescricao());
		}
		else if(!jogo.get(lin+","+col).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
			//atingiu alguma embarcação!!!
			String vetor [] = jogo.get(lin+","+col).split(";");
			String nomeEmbarcacao = vetor[0];
			String numEmbarcacao = vetor[1];
			String posEmbarcacao = vetor[2];
			if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoUtil.ENCOURACADO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.BLACK);
			}else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoUtil.PORTAAVIAO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.RED);
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoUtil.CRUZADORES.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.yellow);
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoUtil.HIDROAVIAO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.GREEN);
			}
			else if(nomeEmbarcacao.equalsIgnoreCase(TipoEmbarcacaoUtil.SUBMARINO.getValor())) {
				mapaButton.get(lin+","+col).setBackground(Color.DARK_GRAY);
			}
			//tiro na agua
			//this.telaPartida.getCoordenadasmapAdversario().get(l2+","+col2).setName(CodigoButtonEnum.TIROAGUA.getDescricao());
		}
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
}
