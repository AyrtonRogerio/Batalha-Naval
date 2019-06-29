/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * @author ayrton
 *
 */
public class TelaJogo extends JPanel {

	private JPanel panel, panelMapJogador, panelMapAdversario, panelEmbarcacoes;

	private JLabel lblSeuJogo, lblJogoAdversario;
	private JLabel lblTiro1;
	private JLabel lblTiro2;
	private JLabel lblTiro3;
	private JLabel lblCoordX;
	private JTextField txtFieldX1;
	private JTextField txtFieldY1;
	private JLabel lblY;
	private JTextField txtFieldY2;
	private JTextField txtFieldX2;
	private JTextField txtFieldX3;
	private JTextField txtFieldY3;
	private JSeparator separator_1;
	private JSeparator separator;
	private JSeparator separator_2;

	/**
	 * Create the panel.
	 */
	public TelaJogo() {
		setBackground(Color.BLACK);
		setSize(new Dimension(1023, 666));
		setLayout(null);

		panel = new JPanel();
		panel.setSize(new Dimension(1024, 800));
		panel.setBounds(0, 0, 1023, 666);
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
		FlowLayout flowLayout = (FlowLayout) panelEmbarcacoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelEmbarcacoes.setBounds(35, 413, 958, 131);
		panel.add(panelEmbarcacoes);

		lblTiro1 = new JLabel("Tiro 1:");
		lblTiro1.setBounds(303, 564, 59, 15);
		panel.add(lblTiro1);

		lblTiro2 = new JLabel("Tiro 2:");
		lblTiro2.setBounds(374, 564, 46, 15);
		panel.add(lblTiro2);

		lblTiro3 = new JLabel("Tiro 3:");
		lblTiro3.setBounds(442, 564, 46, 15);
		panel.add(lblTiro3);

		lblCoordX = new JLabel("Coordenada X:");
		lblCoordX.setBounds(184, 593, 111, 15);
		panel.add(lblCoordX);

		txtFieldX1 = new JTextField();
		txtFieldX1.setBounds(303, 591, 46, 19);
		panel.add(txtFieldX1);
		txtFieldX1.setColumns(10);

		txtFieldY1 = new JTextField();
		txtFieldY1.setColumns(10);
		txtFieldY1.setBounds(303, 617, 46, 19);
		panel.add(txtFieldY1);

		lblY = new JLabel("Coordenada Y:");
		lblY.setBounds(184, 619, 111, 15);
		panel.add(lblY);

		txtFieldY2 = new JTextField();
		txtFieldY2.setColumns(10);
		txtFieldY2.setBounds(374, 617, 46, 19);
		panel.add(txtFieldY2);

		txtFieldX2 = new JTextField();
		txtFieldX2.setColumns(10);
		txtFieldX2.setBounds(374, 591, 46, 19);
		panel.add(txtFieldX2);

		txtFieldX3 = new JTextField();
		txtFieldX3.setColumns(10);
		txtFieldX3.setBounds(442, 591, 46, 19);
		panel.add(txtFieldX3);

		txtFieldY3 = new JTextField();
		txtFieldY3.setColumns(10);
		txtFieldY3.setBounds(442, 617, 46, 19);
		panel.add(txtFieldY3);

		JButton btnDisparar = new JButton("Disparar");
		btnDisparar.setBounds(540, 614, 116, 25);
		panel.add(btnDisparar);

		separator_1 = new JSeparator();
		separator_1.setBounds(35, 556, 958, 8);
		panel.add(separator_1);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(510, 52, 2, 350);
		panel.add(separator);

		separator_2 = new JSeparator();
		separator_2.setBounds(35, 408, 956, 10);
		panel.add(separator_2);

	}

	public void criarPanelMapJogador() {

		panelMapJogador = new JPanel();
		panelMapJogador.setBounds(35, 50, 471, 351);
		panelMapJogador.setLayout(new GridLayout(17, 17));


		construirMapa(panelMapJogador, 17, 17);

	}


	public void criarPanelMapAdversario() {

		panelMapAdversario = new JPanel();
		panelMapAdversario.setBounds(522, 50, 471, 351);
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
