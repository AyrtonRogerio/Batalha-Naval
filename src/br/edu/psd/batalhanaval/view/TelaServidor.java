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

/**
 * @author ayrton
 *
 */
public class TelaServidor extends JPanel {
	private JTextField txtFieldIpServidor;
	private JTextField txtFieldPorta;

	private JButton btnVoltar;
	private JButton btnCriar;
	/**
	 * Create the panel.
	 */
	public TelaServidor() {
		
		JLabel lblIpDoServidor = new JLabel("IP do servidor:");
		lblIpDoServidor.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorta.setFont(new Font("Dialog", Font.BOLD, 25));
		
		txtFieldIpServidor = new JTextField();
		txtFieldIpServidor.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldIpServidor.setToolTipText("IP do servidor");
		txtFieldIpServidor.setColumns(10);
		
		txtFieldPorta = new JTextField();
		txtFieldPorta.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldPorta.setToolTipText("Porta do servidor");
		txtFieldPorta.setColumns(10);
		
		 btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnVoltar.setToolTipText("Voltar a escolha");
		
		btnCriar = new JButton("Criar");
		btnCriar.setFont(new Font("Dialog", Font.BOLD, 15));
		btnCriar.setToolTipText("Criar o servidor");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(180)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblPorta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblIpDoServidor, Alignment.LEADING))
						.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCriar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 125, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFieldPorta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(txtFieldIpServidor, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
					.addGap(180))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(203)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtFieldIpServidor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIpDoServidor))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtFieldPorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPorta, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCriar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(203, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
