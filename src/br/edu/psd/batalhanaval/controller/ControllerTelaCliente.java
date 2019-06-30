package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import br.edu.psd.batalhanaval.model.socket.Cliente;
import br.edu.psd.batalhanaval.view.Tela;
import br.edu.psd.batalhanaval.view.TelaCliente;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;

public class ControllerTelaCliente implements ActionListener{
	TelaCliente telaCliente;
	TelaCriarMapa telaCriarMapa;
	Tela t;
	public ControllerTelaCliente(TelaCliente cliente,Tela t,TelaCriarMapa telaCriarMapa) {
		this.telaCliente = cliente;
		this.t=t;
		this.telaCriarMapa = telaCriarMapa;
		this.telaCliente.getBtnJogar().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.telaCliente.getBtnJogar()==e.getSource()) {
			try {
				Cliente c = new Cliente();
				t.setVisible(false);
				telaCriarMapa.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
