package br.edu.psd.batalhanaval.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {

	private JPanel contentPane;
	private TelaEscolha telaEscolha ;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Tela() {
		telaEscolha=new TelaEscolha();
		
		setSize(480, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		telaEscolha.setBounds(0, 0, 480, 300);
		getContentPane().add(telaEscolha);
		telaEscolha.getTelaServidor().setBounds(0, 0, 480, 300);
		getContentPane().add(telaEscolha.getTelaServidor());
		telaEscolha.getTelaCliente().setBounds(0, 0, 480, 300);
		getContentPane().add(telaEscolha.getTelaCliente());
		
		setVisible(true);
	}


	public JPanel getContentPane() {
		return contentPane;
	}




	public TelaEscolha getTelaEscolha() {
		return telaEscolha;
	}
	
	

}
