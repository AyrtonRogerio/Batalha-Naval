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

/**
 * @author ayrton
 *
 */
public class TelaCliente extends JPanel {
	
	private JTextField txtFieldNome;
	private JTextField txtFieldIpServidor;
	private JTextField txtFieldPorta;
	private JRadioButton rdBtnJogarOn, rdBtnJogarOff;
	private JButton btnJogar,btnVoltar;
	
	/**
	 * Create the panel.
	 */
	public TelaCliente() {
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(-57, 23, 243, 27);
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblIpDoServidor = new JLabel("IP do servidor:");
		lblIpDoServidor.setBounds(10, 61, 243, 33);
		lblIpDoServidor.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(115, 105, 71, 33);
		lblPorta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorta.setFont(new Font("Dialog", Font.BOLD, 15));
		
		txtFieldNome = new JTextField();
		txtFieldNome.setBounds(189, 21, 216, 35);
		txtFieldNome.setToolTipText("Insira seu nome ou apelido para jogar");
		txtFieldNome.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldNome.setColumns(10);
		
		txtFieldIpServidor = new JTextField();
		txtFieldIpServidor.setBounds(189, 61, 216, 39);
		txtFieldIpServidor.setToolTipText("Insira o IP do servidor para conexão");
		txtFieldIpServidor.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldIpServidor.setColumns(10);
		
		txtFieldPorta = new JTextField();
		txtFieldPorta.setBounds(189, 105, 215, 37);
		txtFieldPorta.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldPorta.setColumns(10);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(115, 189, 88, 33);
		btnVoltar.setToolTipText("Voltar ao modo de escolha");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		btnJogar = new JButton("Jogar");
		btnJogar.setBounds(239, 189, 121, 33);
		btnJogar.setToolTipText("Iniciar o jogo");
		btnJogar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		rdBtnJogarOff = new JRadioButton("Jogar offline");
		rdBtnJogarOff.setBounds(159, 149, 131, 33);
		rdBtnJogarOff.setFont(new Font("Dialog", Font.BOLD, 15));
		
		rdBtnJogarOn = new JRadioButton("Jogar online");
		rdBtnJogarOn.setBounds(289, 149, 155, 29);
		rdBtnJogarOn.setSelected(true);
		rdBtnJogarOn.setFont(new Font("Dialog", Font.BOLD, 15));
		setLayout(null);
		add(lblNome);
		add(lblIpDoServidor);
		add(lblPorta);
		add(txtFieldNome);
		add(txtFieldPorta);
		add(txtFieldIpServidor);
		add(btnVoltar);
		add(rdBtnJogarOff);
		add(rdBtnJogarOn);
		add(btnJogar);

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
