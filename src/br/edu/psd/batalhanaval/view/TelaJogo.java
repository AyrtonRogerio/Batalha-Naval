/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 * @author ayrton
 *
 */
public class TelaJogo extends JPanel {

	private JPanel panel, panelMapJogador, panelMapAdversario, panelEmbarcacoes;
	
	private JLabel lblSeuJogo, lblJogoAdversario;
	
	/**
	 * Create the panel.
	 */
	public TelaJogo() {
		setBackground(Color.BLACK);
		setSize(new Dimension(1024, 800));
		setLayout(null);
		
		panel = new JPanel();
		panel.setSize(new Dimension(1024, 800));
		panel.setBounds(0, 0, 1024, 704);
		panel.setLayout(null);
		add(panel);
		criarPanelMapJogador();
		
		panel.add(panelMapJogador);
		
		criarPanelMapAdversario();
		panel.add(panelMapAdversario);
		
		lblSeuJogo = new JLabel("Seu jogo");
		lblSeuJogo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSeuJogo.setBounds(184, 12, 131, 32);
		panel.add(lblSeuJogo);
		
		lblJogoAdversario = new JLabel("Jogo do Adversário");
		lblJogoAdversario.setFont(new Font("Dialog", Font.BOLD, 25));
		lblJogoAdversario.setBounds(641, 12, 251, 32);
		panel.add(lblJogoAdversario);
		
		panelEmbarcacoes = new JPanel();
		panelEmbarcacoes.setBounds(35, 413, 958, 175);
		panel.add(panelEmbarcacoes);

	}
	
	public void criarPanelMapJogador() {
		
		panelMapJogador = new JPanel();
		panelMapJogador.setBounds(35, 50, 453, 351);
		panelMapJogador.setLayout(new GridLayout(17, 17));
		
		
		construirMapa(panelMapJogador, 17, 17);
		
	}
	

	public void criarPanelMapAdversario() {
		
		panelMapAdversario = new JPanel();
		panelMapAdversario.setBounds(540, 50, 453, 351);
		panelMapAdversario.setLayout(new GridLayout(17, 17));
		
		construirMapa(panelMapAdversario, 17, 17);
		
		
	}
	
	public void construirMapa(JPanel panel, int rows, int cols) {
		
		
		JLabel label = null;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++){
				label = new JLabel("");
				label.setName(i + "," + j);
				label.setToolTipText(i + "," + j);
				
				
				} 
				
				panel.add(label);
			}
		
	
}
