/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.SystemColor;

/**
 * @author ayrton
 *
 */
public class PanelCliente extends JPanel {
	
	private JTextField txtFieldNome;
	private JTextField txtFieldIpServidor;
	private JTextField txtFieldPorta;
	private JRadioButton rdBtnJogarOn, rdBtnJogarOff;
	private JButton btnJogar,btnVoltar;
	
	/**
	 * Create the panel.
	 */
	public PanelCliente() {
		setBackground(Color.WHITE);
		
		txtFieldNome = new JTextField();
		txtFieldNome.setForeground(Color.LIGHT_GRAY);
		txtFieldNome.setText("Nome");
		txtFieldNome.setBounds(121, 60, 216, 35);
		txtFieldNome.setToolTipText("Insira seu nome ou apelido para jogar");
		txtFieldNome.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtFieldNome.setColumns(10);
		
		txtFieldIpServidor = new JTextField();
		txtFieldIpServidor.setForeground(Color.LIGHT_GRAY);
		txtFieldIpServidor.setText("IP do servidor");
		txtFieldIpServidor.setBounds(121, 106, 216, 39);
		txtFieldIpServidor.setToolTipText("Insira o IP do servidor para conexão");
		txtFieldIpServidor.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtFieldIpServidor.setColumns(10);
		
		txtFieldPorta = new JTextField();
		txtFieldPorta.setForeground(Color.LIGHT_GRAY);
		txtFieldPorta.setText("Porta");
		txtFieldPorta.setBounds(122, 156, 215, 37);
		txtFieldPorta.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtFieldPorta.setColumns(10);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.RED);
		btnVoltar.setBounds(121, 238, 88, 33);
		btnVoltar.setToolTipText("Voltar ao modo de escolha");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		btnJogar = new JButton("Jogar");
		btnJogar.setBackground(SystemColor.textHighlight);
		btnJogar.setBounds(231, 238, 121, 33);
		btnJogar.setToolTipText("Iniciar o jogo");
		btnJogar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		rdBtnJogarOff = new JRadioButton("Jogar offline");
		rdBtnJogarOff.setBounds(93, 198, 131, 33);
		rdBtnJogarOff.setFont(new Font("Dialog", Font.BOLD, 15));
		
		rdBtnJogarOn = new JRadioButton("Jogar online");
		rdBtnJogarOn.setBounds(257, 202, 155, 29);
		rdBtnJogarOn.setSelected(true);
		rdBtnJogarOn.setFont(new Font("Dialog", Font.BOLD, 15));
		setLayout(null);
		add(txtFieldNome);
		add(txtFieldPorta);
		add(txtFieldIpServidor);
		add(btnVoltar);
		add(rdBtnJogarOff);
		add(rdBtnJogarOn);
		add(btnJogar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 38, 430, 10);
		add(separator);
		
		JGradientePanel panel = new JGradientePanel(SystemColor.textHighlight, Color.white);
		//panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 480, 36);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblJogador = new JLabel("Jogador");
		lblJogador.setBounds(0, 0, 450, 35);
		panel.add(lblJogador);
		lblJogador.setBackground(SystemColor.text);
		lblJogador.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 30));
		lblJogador.setHorizontalAlignment(SwingConstants.CENTER);

	}
	public JTextField getTxtFieldNome() {
		return txtFieldNome;
	}
	public JTextField getTxtFieldIpServidor() {
		return txtFieldIpServidor;
	}
	public JTextField getTxtFieldPorta() {
		return txtFieldPorta;
	}
	public JRadioButton getRdBtnJogarOn() {
		return rdBtnJogarOn;
	}
	public JRadioButton getRdBtnJogarOff() {
		return rdBtnJogarOff;
	}
	public JButton getBtnJogar() {
		return btnJogar;
	}
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	/**
	 * @param txtFieldNome the txtFieldNome to set
	 */
	public void setTxtFieldNome(JTextField txtFieldNome) {
		this.txtFieldNome = txtFieldNome;
	}
	/**
	 * @param txtFieldIpServidor the txtFieldIpServidor to set
	 */
	public void setTxtFieldIpServidor(JTextField txtFieldIpServidor) {
		this.txtFieldIpServidor = txtFieldIpServidor;
	}
	/**
	 * @param txtFieldPorta the txtFieldPorta to set
	 */
	public void setTxtFieldPorta(JTextField txtFieldPorta) {
		this.txtFieldPorta = txtFieldPorta;
	}
	/**
	 * @param rdBtnJogarOn the rdBtnJogarOn to set
	 */
	public void setRdBtnJogarOn(JRadioButton rdBtnJogarOn) {
		this.rdBtnJogarOn = rdBtnJogarOn;
	}
	/**
	 * @param rdBtnJogarOff the rdBtnJogarOff to set
	 */
	public void setRdBtnJogarOff(JRadioButton rdBtnJogarOff) {
		this.rdBtnJogarOff = rdBtnJogarOff;
	}
	/**
	 * @param btnJogar the btnJogar to set
	 */
	public void setBtnJogar(JButton btnJogar) {
		this.btnJogar = btnJogar;
	}
	/**
	 * @param btnVoltar the btnVoltar to set
	 */
	public void setBtnVoltar(JButton btnVoltar) {
		this.btnVoltar = btnVoltar;
	}
}
