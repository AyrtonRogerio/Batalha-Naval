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
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

/**
 * @author ayrton
 *
 */
public class TelaEscolha extends JPanel {
	private JButton btnServidor;
	private JButton btnJogador;
	private TelaServidor telaServidor;
	private TelaCliente telaCliente;
	/**
	 * Create the panel.
	 */
	public TelaEscolha() {
		
		JLabel lblEscolhaComoVoc = new JLabel("Escolha como voc\u00EA deseja iniciar a aplica\u00E7\u00E3o!");
		lblEscolhaComoVoc.setFont(new Font("Dialog", Font.BOLD, 20));
		
		btnServidor = new JButton("Servidor");
		
		btnJogador = new JButton("Jogador");
		telaCliente = new TelaCliente();
		telaCliente.setVisible(false);
		telaServidor = new TelaServidor();
		telaServidor.setVisible(false);
		btnJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(315, Short.MAX_VALUE)
					.addComponent(btnServidor)
					.addGap(27)
					.addComponent(btnJogador, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(115))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEscolhaComoVoc)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblEscolhaComoVoc, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnJogador)
						.addComponent(btnServidor))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

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
	public TelaCliente getTelaCliente() {
		return telaCliente;
	}
	
	
	
}
