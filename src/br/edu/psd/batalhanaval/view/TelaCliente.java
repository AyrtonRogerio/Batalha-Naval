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

	/**
	 * Create the panel.
	 */
	public TelaCliente() {
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblIpDoServidor = new JLabel("IP do servidor:");
		lblIpDoServidor.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorta.setFont(new Font("Dialog", Font.BOLD, 25));
		
		txtFieldNome = new JTextField();
		txtFieldNome.setToolTipText("Insira seu nome ou apelido para jogar");
		txtFieldNome.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldNome.setColumns(10);
		
		txtFieldIpServidor = new JTextField();
		txtFieldIpServidor.setToolTipText("Insira o IP do servidor para conexão");
		txtFieldIpServidor.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldIpServidor.setColumns(10);
		
		txtFieldPorta = new JTextField();
		txtFieldPorta.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtFieldPorta.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setToolTipText("Voltar ao modo de escolha");
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.setToolTipText("Iniciar o jogo");
		btnJogar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JRadioButton rdBtnJogarOff = new JRadioButton("Jogar offline");
		rdBtnJogarOff.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JRadioButton rdBtnJogarOn = new JRadioButton("Jogar online");
		rdBtnJogarOn.setSelected(true);
		rdBtnJogarOn.setFont(new Font("Dialog", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(171)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblIpDoServidor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblPorta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFieldNome, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldPorta, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldIpServidor, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdBtnJogarOff, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(rdBtnJogarOn, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnJogar, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(214, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(182)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFieldNome, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpDoServidor)
						.addComponent(txtFieldIpServidor, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPorta)
						.addComponent(txtFieldPorta, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdBtnJogarOff, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnJogar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
						.addComponent(rdBtnJogarOn))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
