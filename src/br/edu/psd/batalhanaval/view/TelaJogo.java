/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.edu.psd.batalhanaval.Util.SocketUtil;

/**
 * @author ayrton
 *
 */
public class TelaJogo extends JPanel {

	private JPanel panel, panelMapJogador, panelMapAdversario, panelEmbarcacoes;

	private JButton panelMapaJogador;
	
	private  Map<String,JButton>coordenadasmap = new HashMap<String,JButton>();
	
	private GridLayout mapaJogadorGrid, mapaAdversarioGrid;
	
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

	private JButton btnDisparar;
	
	private JLabel lblPontos;
	
	private JPanel panelJogador;
	
	/**
	 * Create the panel.
	 */
	public TelaJogo() {
		setBackground(Color.BLACK);
		setSize(new Dimension(800, 700));
		setLayout(null);

		panel = new JPanel();
		panel.setSize(new Dimension(1024, 800));
		panel.setBounds(0, 0, 800, 700);
		panel.setLayout(null);
		add(panel);
		criarPanelMapJogador();

		panel.add(panelMapJogador);

		criarPanelMapAdversario();
		panel.add(panelMapAdversario);

		lblSeuJogo = new JLabel("Seu jogo");
		lblSeuJogo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSeuJogo.setBounds(124, 12, 131, 32);
		panel.add(lblSeuJogo);

		lblJogoAdversario = new JLabel("Jogo do Adversário");
		lblJogoAdversario.setFont(new Font("Dialog", Font.BOLD, 25));
		lblJogoAdversario.setBounds(459, 6, 251, 32);
		panel.add(lblJogoAdversario);

		panelEmbarcacoes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelEmbarcacoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelEmbarcacoes.setBounds(35, 413, 700, 131);
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

		btnDisparar = new JButton("Disparar");
		btnDisparar.setBounds(540, 614, 116, 25);
		panel.add(btnDisparar);

		separator_1 = new JSeparator();
		separator_1.setBounds(35, 556, 700, 10);
		panel.add(separator_1);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(391, 46, 2, 350);
		panel.add(separator);

		separator_2 = new JSeparator();
		separator_2.setBounds(35, 408, 700, 10);
		panel.add(separator_2);
		
		panelJogador = new JPanel();
		panelJogador.setBounds(35, 50, 300, 300);
		panel.add(panelJogador);
		panelJogador.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblPontos = new JLabel("Pontos: ");
		lblPontos.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPontos.setBounds(540, 578, 111, 25);
		panel.add(lblPontos);

		panel.setVisible(false);
		
	}

	/**
	 * Cria o panel do jogador
	 */
	public void criarPanelMapJogador() {

		panelMapJogador = new JPanel();
		panelMapJogador.setBounds(35, 50, 300, 300);
		panelMapJogador.setLayout(mapaJogadorGrid = new GridLayout(17, 17,0,0));

		montarMapaVazio(panelMapJogador);
		panelMapaJogador.setVisible(false);



	}


	/**
	 * Cria o panel do adversário
	 */
	public void criarPanelMapAdversario() {

		panelMapAdversario = new JPanel();
		panelMapAdversario.setBounds(435, 50, 300, 300);
		panelMapAdversario.setLayout( mapaAdversarioGrid = new GridLayout(17, 17,0,0));

		montarMapaVazio(panelMapAdversario);
		panelMapAdversario.setVisible(false);

	}

	/**
	 * Montar o mapa vazio
	 * @param panel
	 */
	private void montarMapaVazio(JPanel panel) {
		JButton botao = null;
		for(int i = 0; i < 17; i++)
			for(int j = 0; j < 17; j++){
				if(i == 0 || i==16) { //Se primeira ou ultima linha
					if(j==0 || j==16) {//Extremidades
						botao = new JButton(" ");
						botao.setName("[V]");
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						panel.add(botao);
					}
					else {
						botao = new JButton(j+"");
						botao.setName("[NUM]");
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						panel.add(botao);
					}
				}
				else {//Se n�o for primeira ou ultima linha
					if(i==0 && j==0 || i==16 && j==16 || i==0 && j==16 || i==16 && j==0) {//Captura as extremidades.
						botao = new JButton(" ");
						botao.setName(i + "," + j);
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						panel.add(botao);
					}
					else if(j==0 || j==16) {//Bordas Laterais
						botao = new JButton(SocketUtil.converterNumeroEmLetra(i));
						botao.setName(i + "," + j);
						botao.setToolTipText(i + "," + j);
						botao.setBorder(null);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						panel.add(botao);
					}else {//Miolo do mapa
						botao = new JButton(" ");
						botao.setName("[POS]");//Vai conter o COD da embarca��o.
						botao.setToolTipText(i + "," + j);//Vai conter a coordenada.
						botao.setBackground(Color.cyan);
						botao.setBorder(new LineBorder(Color.BLACK,1,false));
//						botao.addActionListener(this);
						coordenadasmap.put(i + "," + j, botao);
						panel.add(botao);
					}
				}
			} 
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the panelMapJogador
	 */
	public JPanel getPanelMapJogador() {
		return panelMapJogador;
	}

	/**
	 * @param panelMapJogador the panelMapJogador to set
	 */
	public void setPanelMapJogador(JPanel panelMapJogador) {
		this.panelMapJogador = panelMapJogador;
	}

	/**
	 * @return the panelMapAdversario
	 */
	public JPanel getPanelMapAdversario() {
		return panelMapAdversario;
	}

	/**
	 * @param panelMapAdversario the panelMapAdversario to set
	 */
	public void setPanelMapAdversario(JPanel panelMapAdversario) {
		this.panelMapAdversario = panelMapAdversario;
	}

	/**
	 * @return the panelEmbarcacoes
	 */
	public JPanel getPanelEmbarcacoes() {
		return panelEmbarcacoes;
	}

	/**
	 * @param panelEmbarcacoes the panelEmbarcacoes to set
	 */
	public void setPanelEmbarcacoes(JPanel panelEmbarcacoes) {
		this.panelEmbarcacoes = panelEmbarcacoes;
	}

	/**
	 * @return the panelMapaJogador
	 */
	public JButton getPanelMapaJogador() {
		return panelMapaJogador;
	}

	/**
	 * @param panelMapaJogador the panelMapaJogador to set
	 */
	public void setPanelMapaJogador(JButton panelMapaJogador) {
		this.panelMapaJogador = panelMapaJogador;
	}

	/**
	 * @return the coordenadasmap
	 */
	public Map<String, JButton> getCoordenadasmap() {
		return coordenadasmap;
	}

	/**
	 * @param coordenadasmap the coordenadasmap to set
	 */
	public void setCoordenadasmap(Map<String, JButton> coordenadasmap) {
		this.coordenadasmap = coordenadasmap;
	}

	/**
	 * @return the mapaJogadorGrid
	 */
	public GridLayout getMapaJogadorGrid() {
		return mapaJogadorGrid;
	}

	/**
	 * @param mapaJogadorGrid the mapaJogadorGrid to set
	 */
	public void setMapaJogadorGrid(GridLayout mapaJogadorGrid) {
		this.mapaJogadorGrid = mapaJogadorGrid;
	}

	/**
	 * @return the mapaAdversarioGrid
	 */
	public GridLayout getMapaAdversarioGrid() {
		return mapaAdversarioGrid;
	}

	/**
	 * @param mapaAdversarioGrid the mapaAdversarioGrid to set
	 */
	public void setMapaAdversarioGrid(GridLayout mapaAdversarioGrid) {
		this.mapaAdversarioGrid = mapaAdversarioGrid;
	}

	/**
	 * @return the btnDisparar
	 */
	public JButton getBtnDisparar() {
		return btnDisparar;
	}

	/**
	 * @param btnDisparar the btnDisparar to set
	 */
	public void setBtnDisparar(JButton btnDisparar) {
		this.btnDisparar = btnDisparar;
	}

	/**
	 * @return the lblPontos
	 */
	public JLabel getLblPontos() {
		return lblPontos;
	}

	/**
	 * @param lblPontos the lblPontos to set
	 */
	public void setLblPontos(JLabel lblPontos) {
		this.lblPontos = lblPontos;
	}

	/**
	 * @return the panelJogador
	 */
	public JPanel getPanelJogador() {
		return panelJogador;
	}

	/**
	 * @param panelJogador the panelJogador to set
	 */
	public void setPanelJogador(JPanel panelJogador) {
		this.panelJogador = panelJogador;
	}
}
