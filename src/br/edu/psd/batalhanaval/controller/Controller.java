/**
 * 
 */
package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import br.edu.psd.batalhanaval.model.socket.Servidor;
import br.edu.psd.batalhanaval.view.Tela;
import br.edu.psd.batalhanaval.view.TelaEscolha;

/**
 * @author ayrton
 *
 */
public class Controller implements ActionListener{
	private Tela f;
	private TelaEscolha telaEscolha;
	public Controller(TelaEscolha telaEscolha,Tela f) {
		this.f=f;
		this.telaEscolha = telaEscolha;
		this.telaEscolha.getBtnServidor().addActionListener(this);
		this.telaEscolha.getBtnJogador().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(telaEscolha.getBtnServidor()==e.getSource()) {
			f.getTelaEscolha().setVisible(false);
			f.getTelaEscolha().getTelaServidor().setVisible(true);
			try {
				Thread s = new Servidor(9000, "Servidor",telaEscolha.getTelaServidor());
				s.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			f.getTelaEscolha().setVisible(false);
			f.getTelaEscolha().getTelaCliente().setVisible(true);
		}
		
	}
}
