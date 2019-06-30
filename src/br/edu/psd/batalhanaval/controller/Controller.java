/**
 * 
 */
package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.model.socket.Cliente;
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
				System.out.println("Servidor iniciado");
				s.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Um servido já está em uso", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		} 
		if(e.getSource() == telaEscolha.getBtnJogador()){
			
			f.getTelaEscolha().setVisible(false);
			f.getTelaEscolha().getTelaCliente().setVisible(true);
			
		}
		
	}
}
