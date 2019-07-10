package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ClienteUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.socket.Cliente;
import br.edu.psd.batalhanaval.model.socket.Servidor;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;

public class ControlerTelaEscolherOponente implements ActionListener{
	Cliente c;
	TelaCriarMapa telaCriarMapa;
	TelaEscolherOponente escolherOponente;
	public ControlerTelaEscolherOponente(Cliente c,TelaEscolherOponente telaEscolherOponente,TelaCriarMapa telaCriarMapa) {
		this.c=c;
		this.escolherOponente=telaEscolherOponente;
		this.telaCriarMapa=telaCriarMapa;
		c.setTelaCriarMapa(this.telaCriarMapa);
		c.setTelaEscolherOponente(escolherOponente);
		escolherOponente.getBtnDesafiar().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(!c.getJogadores().getValorAt(escolherOponente.getTable().getSelectedRow()).getStatus().equals(ClienteUtil.JOGANDO)) {//verifica se o jogador ja esta em uma partida
				c.setStatus(ClienteUtil.AGUARDANDO);
				c.getOos().writeObject(ProtocoloUtil.QUER_JOGAR+c.getJogadores().getValorAt(escolherOponente.getTable().getSelectedRow()).getNome()+" "+c.getNome());
			}else {
				JOptionPane.showMessageDialog(null,"O jogador ja esta jogando. desafie outro!!");
			}
			
	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//Envia requisição para um jogador
	}
}
