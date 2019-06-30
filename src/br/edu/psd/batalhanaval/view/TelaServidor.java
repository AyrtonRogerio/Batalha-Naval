/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;

/**
 * @author ayrton
 *
 */
public class TelaServidor extends JPanel {

	private JButton btnVoltar;
	private JButton btnCriar;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public TelaServidor() {
		setBackground(SystemColor.activeCaptionBorder);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(180, 327, 87, 35);
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnVoltar.setToolTipText("Voltar a escolha");
		
		btnCriar = new JButton("Criar");
		btnCriar.setBounds(277, 327, 104, 35);
		btnCriar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnCriar.setToolTipText("Criar o servidor");
		setLayout(null);
		add(btnVoltar);
		add(btnCriar);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 108, 430, 181);
		add(textArea);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		lblServidor.setBounds(-10, 11, 450, 25);
		add(lblServidor);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIp.setBounds(10, 62, 46, 14);
		add(lblIp);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPorta.setBounds(10, 83, 46, 14);
		add(lblPorta);
		
		JLabel lblLocalhost = new JLabel("localhost");
		lblLocalhost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocalhost.setBounds(37, 62, 126, 14);
		add(lblLocalhost);
		
		JLabel label = new JLabel("9000");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(47, 83, 46, 14);
		add(label);

	}
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	public JButton getBtnCriar() {
		return btnCriar;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
}
