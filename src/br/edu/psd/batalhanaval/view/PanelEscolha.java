/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * @author ayrton
 *
 */
public class PanelEscolha extends JGradientePanel {
	private JButton btnServidor;
	private JButton btnJogador;
	private TelaServidor telaServidor;
	private PanelCliente telaCliente;
	/**
	 * Create the panel.
	 */
	public PanelEscolha(Color initialColor, Color finalColor) {
		super(initialColor,finalColor);
		JLabel lblEscolhaComoVoc = new JLabel("Escolha como voc\u00EA deseja iniciar a aplica\u00E7\u00E3o!");
		lblEscolhaComoVoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaComoVoc.setBounds(0, 82, 470, 52);
		lblEscolhaComoVoc.setFont(new Font("Dialog", Font.BOLD, 19));
		lblEscolhaComoVoc.setForeground(Color.WHITE);
		btnServidor = new JButton("Servidor");
		btnServidor.setBounds(126, 152, 85, 35);
		btnServidor.setBackground(Color.WHITE);
		btnServidor.setFont(new Font("Dialog", Font.BOLD, 12));
		
		btnJogador = new JButton("Jogador");
		btnJogador.setBounds(226, 152, 109, 35);
		btnJogador.setBackground(Color.WHITE);
		btnJogador.setFont(new Font("Dialog", Font.BOLD, 12));
		
		telaCliente = new PanelCliente();
		telaCliente.setVisible(false);
		telaServidor = new TelaServidor();
		telaServidor.setVisible(false);
		btnJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setLayout(null);
		add(btnServidor);
		add(btnJogador);
		add(lblEscolhaComoVoc);

	}
	public JButton getBtnServidor() {
		return btnServidor;
	}
	public JButton getBtnJogador() {
		return btnJogador;
	}
	public TelaServidor getTelaServidor() {
		return telaServidor;
	}
	public PanelCliente getTelaCliente() {
		return telaCliente;
	}
	
	
	
}
