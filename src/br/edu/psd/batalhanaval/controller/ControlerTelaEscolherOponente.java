package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.psd.batalhanaval.Util.ClienteUtil;
import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.model.socket.Cliente;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;

public class ControlerTelaEscolherOponente implements ActionListener{
	Cliente c;
	TelaCriarMapa telaCriarMapa;
	public ControlerTelaEscolherOponente() {

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		c.setStatus(ClienteUtil.AGUARDANDO);
		//falta especificar qual o jogador selecionado
		c.enviaMensagem(ProtocoloUtil.QUER_JOGAR+"1");//Envia requisição para um jogador
		while(c.getStatus().equals(ClienteUtil.AGUARDANDO)) {
			if(c.getStatus().equals(ClienteUtil.JOGANDO)) {
				telaCriarMapa.setVisible(true);
			}
		}
		
	}
}
