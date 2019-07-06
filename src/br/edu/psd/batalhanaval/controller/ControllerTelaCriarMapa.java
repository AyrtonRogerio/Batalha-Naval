package br.edu.psd.batalhanaval.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.model.Jogador;
import br.edu.psd.batalhanaval.model.MontadorDeMapa;
import br.edu.psd.batalhanaval.model.socket.Cliente;
import br.edu.psd.batalhanaval.view.TelaJogo;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;

public class ControllerTelaCriarMapa implements ActionListener{
	
	private TelaCriarMapa telaCMapa;
	private TelaJogo telaJogo;
	
	public ControllerTelaCriarMapa(TelaCriarMapa telaCMapa, TelaJogo telaJogo) {
		
		this.telaCMapa=telaCMapa;
		this.telaJogo = telaJogo;
	
		this.telaCMapa.getSelecionarCruzButton().addActionListener(this);
		this.telaCMapa.getSelecionarEncButton().addActionListener(this);
		this.telaCMapa.getSelecionarHidroButton().addActionListener(this);
		this.telaCMapa.getSelecionarPortaAvButton().addActionListener(this);
		this.telaCMapa.getSelecionarSubButton().addActionListener(this);
		this.telaCMapa.getBtnJogar().addActionListener(evt -> jogar());
		
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if(this.telaCMapa.getSelecionarCruzButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarCruzButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarEncButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarEncButton().setBackground(Color.BLACK);

		if(this.telaCMapa.getSelecionarHidroButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarHidroButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarPortaAvButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarPortaAvButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarSubButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarSubButton().setBackground(Color.BLACK);
		
		if(evt.getSource()==this.telaCMapa.getSelecionarCruzButton()) {
			if(this.telaCMapa.getSelecionarCruzButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarCruzButton().setBackground(Color.BLUE);
			}
		}else if(evt.getSource()==this.telaCMapa.getSelecionarEncButton()) {
			if(this.telaCMapa.getSelecionarEncButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarEncButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarHidroButton()) {
			if(this.telaCMapa.getSelecionarHidroButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarHidroButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarPortaAvButton()) {
			if(this.telaCMapa.getSelecionarPortaAvButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarPortaAvButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarSubButton()) {
			if(this.telaCMapa.getSelecionarSubButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarSubButton().setBackground(Color.BLUE);
			}
		}
		
		if(evt.getSource() == this.telaCMapa.getBtnJogar()) {
			
			this.telaCMapa.getPanelEmbarcacoes().setVisible(false);
			this.telaCMapa.getPanelMapa().setVisible(false);
			this.telaCMapa.setTelaJogo(telaJogo);
			this.telaCMapa.getTelaJogo().setVisible(true);
			this.telaCMapa.getTelaJogo().getPanel().setVisible(true);
			this.telaCMapa.getPanelJogo().setVisible(true);
			
			
		}
	}
	private void jogar() {
		if(EmbarcacoesUtil.getCruzadoresPosicionados()<=0)
			JOptionPane.showMessageDialog(null,"Existem Embarcacoes que ainda precisam ser posicionadas!!!");
		else if(EmbarcacoesUtil.getEncouracadosPosicionados()<=0)
			JOptionPane.showMessageDialog(null,"Existem Embarcacoes que ainda precisam ser posicionadas!!!");
		else if(EmbarcacoesUtil.getHidroAvPosicionados()<=0)
			JOptionPane.showMessageDialog(null,"Existem Embarcacoes que ainda precisam ser posicionadas!!!");
		else if(EmbarcacoesUtil.getPortaAvPosicionados()<=0)
			JOptionPane.showMessageDialog(null,"Existem Embarcacoes que ainda precisam ser posicionadas!!!");
		else if(EmbarcacoesUtil.getSubimarinosPosicionados()<=0)
			JOptionPane.showMessageDialog(null,"Existem Embarcacoes que ainda precisam ser posicionadas!!!");
		else {
			Cliente cliente = SocketUtil.getClienteCorrente();
			MontadorDeMapa.montarMapaMeuJogoAtual(telaCMapa.getCoordenadasmap(),cliente.getJogador().getCoordenadasMeuJogoAtual());
			//Precisa verificar se o jogo � offiline.
			if(SocketUtil.offiline) {
				cliente.getJogador().setEmJogo(true);
				SocketUtil.setComputador(new Jogador());
				SocketUtil.getComputador().setNome("Computador");
				MontadorDeMapa.montarMapaComputador(SocketUtil.getComputador().getCoordenadasMeuJogoAtual());
				SocketUtil.getClienteCorrente().getJogador().setEmJogo(true);
				SocketUtil.getClienteCorrente().getJogador().setSuaVez(true);
				EmbarcacoesUtil.limparPosicionamentos();
				this.telaCMapa.setVisible(false);
				this.telaJogo.limparTela();
				this.telaJogo.setVisible(true);
			}else {
				try {
					cliente.getOos().writeObject(SocketUtil.informar.getBytes());
					while(cliente.getStatusDeJogo().equals(ProtocoloUtil.ESPERARANDO)) {
						if(cliente.getStatusDeJogo().equals(ProtocoloUtil.INICIAR)) {
							
							this.telaJogo.setVisible(true);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Montar mapa adiversario online!
			}	
		}
	}
}
