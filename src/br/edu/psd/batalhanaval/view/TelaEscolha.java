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

	/**
	 * Create the panel.
	 */
	public TelaEscolha() {
		
		JLabel lblEscolhaComoVoc = new JLabel("Escolha como você deseja iniciar a aplicação!");
		lblEscolhaComoVoc.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JButton btnServidor = new JButton("Servidor");
		
		JButton btnJogador = new JButton("Jogador");
		btnJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(lblEscolhaComoVoc)
					.addContainerGap(97, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(291, Short.MAX_VALUE)
					.addComponent(btnServidor)
					.addGap(18)
					.addComponent(btnJogador, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(289))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblEscolhaComoVoc, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnServidor)
						.addComponent(btnJogador))
					.addContainerGap(373, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
