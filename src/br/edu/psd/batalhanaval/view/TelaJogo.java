/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.Util.Enum.CodigoButtonEnum;
import javax.swing.border.TitledBorder;

/**
 * @author ayrton
 *
 */
public class TelaJogo extends JFrame {

	private JPanel panel, panelMapJogador, panelMapAdversario, panelEmbarcacoes;

	private JButton panelMapaJogador;
	
	private  Map<String,JButton>coordenadasmapCliente = new HashMap<String,JButton>();
	private  Map<String,JButton>coordenadasmapAdversario = new HashMap<String,JButton>();
	
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

	private JButton btnDisparar;
	
	private JLabel lblPontos;
	
	private JPanel panelJogador;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelCenter;
	
	/**
	 * Create the panel.
	 */
	public TelaJogo() {
		
		setBackground(Color.BLACK);
		setSize(850,700);
		setLocationRelativeTo(null);

		
		panel = new JPanel();
		panel.setSize(new Dimension(1024, 400));
		panel.setBounds(0, 0, 800, 700);
		panel.setLayout(new BorderLayout());
		
		this.initPanelNorth();
		this.initPanelCenter();
		this.initPanelSouth();
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		criarPanelMapJogador();

		
		JPanel panelesquerdaCentro = new JPanel(new BorderLayout());
		panelesquerdaCentro.add(panelMapJogador);
		panelesquerdaCentro.setPreferredSize(new Dimension(400,400));
		panel.add(panelesquerdaCentro,BorderLayout.WEST);
		criarPanelMapAdversario();
		
		JPanel panelDireita = new JPanel(new BorderLayout());
		panelDireita.add(panelMapAdversario,BorderLayout.CENTER);
		
		panel.add(panelDireita,BorderLayout.EAST);
		lblSeuJogo = new JLabel("Seu jogo");
		lblSeuJogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuJogo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSeuJogo.setBounds(124, 12, 131, 32);
		panelesquerdaCentro.add(lblSeuJogo,BorderLayout.NORTH);

		lblJogoAdversario = new JLabel("Adversario");
		lblJogoAdversario.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogoAdversario.setFont(new Font("Dialog", Font.BOLD, 25));
		lblJogoAdversario.setBounds(459, 6, 251, 32);
		panelDireita.add(lblJogoAdversario,BorderLayout.NORTH);
		panelDireita.setPreferredSize(new Dimension(400,400));
		//panel.setVisible(false);
		this.setVisible(false);
		
	}
	public static void main(String[] args) {
		new TelaJogo();
	}
	private void initPanelNorth() {
		panelNorth = new JPanel(new BorderLayout());
		panelNorth.setPreferredSize(new Dimension(400, 100));
		panelNorth.setBackground(Color.DARK_GRAY);
		JLabel lblSeujogo = new JLabel("PARTIDA");
		lblSeujogo.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorth.add(lblSeujogo);
		lblSeujogo.setForeground(Color.WHITE);
		lblSeujogo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 50));
		
		getContentPane().add(panelNorth,BorderLayout.NORTH);
	}
	private void initPanelCenter() {
		panelCenter = new JPanel(new BorderLayout());
		panelCenter.setPreferredSize(new Dimension(400,400));
		panelCenter.add(panel,BorderLayout.CENTER);
		getContentPane().add(panelCenter,BorderLayout.CENTER);
	}
	private void initPanelSouth() {
		panelSouth = new JPanel(new BorderLayout());
		panelSouth.setPreferredSize(new Dimension(400, 250));
		panelEmbarcacoes = new JPanel();
		panelEmbarcacoes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Embarcacoes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEmbarcacoes.setPreferredSize(new Dimension(400, 120));
		FlowLayout flowLayout = (FlowLayout) panelEmbarcacoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelEmbarcacoes.setBounds(35, 413, 700, 131);
		panelSouth.add(panelEmbarcacoes, BorderLayout.NORTH);
		
		JPanel panelAux = new JPanel(new GridLayout(4,5));
		panelSouth.add(panelAux);
		
		lblPontos = new JLabel("Pontos: ");
		lblPontos.setFont(new Font("Dialog", Font.BOLD, 15));
		//panelAux.add(lblPontos);
		panelAux.add(new JLabel("  "));
		
		lblTiro1 = new JLabel("Tiro 1:");
		lblTiro1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTiro1.setBounds(303, 564, 59, 15);
		panelAux.add(lblTiro1);

		lblTiro2 = new JLabel("Tiro 2:");
		lblTiro2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTiro2.setBounds(374, 564, 46, 15);
		panelAux.add(lblTiro2);

		lblTiro3 = new JLabel("Tiro 3:");
		lblTiro3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTiro3.setBounds(442, 564, 46, 15);
		panelAux.add(lblTiro3);
		
		lblCoordX = new JLabel("Coordenada X:");
		lblCoordX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoordX.setBounds(184, 593, 111, 15);
		panelAux.add(lblCoordX);
		
		txtFieldX1 = new JTextField();
		txtFieldX1.setToolTipText("X");
		//txtFieldX1.setBounds(303, 591, 46, 19);
		panelAux.add(txtFieldX1);
		txtFieldX1.setColumns(10);
		
		txtFieldX2 = new JTextField();
		txtFieldX2.setToolTipText("X");
		txtFieldX2.setColumns(10);
		//txtFieldX2.setBounds(374, 591, 46, 19);
		panelAux.add(txtFieldX2);

		txtFieldX3 = new JTextField();
		txtFieldX3.setToolTipText("X");
		txtFieldX3.setColumns(10);
		//txtFieldX3.setBounds(442, 591, 46, 19);
		panelAux.add(txtFieldX3);
		
		lblY = new JLabel("Coordenada Y:");
		lblY.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblY.setBounds(184, 619, 111, 15);
		panelAux.add(lblY);
			
		txtFieldY1 = new JTextField();
		txtFieldY1.setToolTipText("Y");
		txtFieldY1.setColumns(10);
		txtFieldY1.setBounds(303, 617, 46, 19);
		panelAux.add(txtFieldY1);

		

		txtFieldY2 = new JTextField();
		txtFieldY2.setToolTipText("Y");
		txtFieldY2.setColumns(10);
		txtFieldY2.setBounds(374, 617, 46, 19);
		panelAux.add(txtFieldY2);

	
	

		txtFieldY3 = new JTextField();
		txtFieldY3.setToolTipText("Y");
		txtFieldY3.setColumns(10);
		txtFieldY3.setBounds(442, 617, 46, 19);
		panelAux.add(txtFieldY3);

		panelAux.add(new JLabel(" "));
		btnDisparar = new JButton("Disparar");
		btnDisparar.setBounds(540, 614, 116, 25);
		panelAux.add(btnDisparar);
		getContentPane().add(panelSouth,BorderLayout.SOUTH);
	}
	/**
	 * Cria o panel do jogador vazio
	 */
	public void criarPanelMapJogador() {

		panelMapJogador = new JPanel();
		panelMapJogador.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelMapJogador.setBounds(35, 50, 300, 300);
		panelMapJogador.setLayout(mapaJogadorGrid = new GridLayout(17, 17,0,0));

		montarMapaVazio(panelMapJogador,coordenadasmapCliente);
		//panelMapaJogador.setVisible(true);



	}


	/**
	 * Cria o panel do adversário vazio
	 */
	
	public void criarPanelMapAdversario() {//Cria o mapa vazio!

		panelMapAdversario = new JPanel();
		panelMapAdversario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelMapAdversario.setBounds(435, 50, 300, 300);
		panelMapAdversario.setLayout( mapaAdversarioGrid = new GridLayout(17, 17,0,0));
		montarMapaVazio(panelMapAdversario,coordenadasmapAdversario);
		//panelMapAdversario.setVisible(false);
	}

	/**
	 * Montar o mapa vazio
	 * @param panel
	 */
	private void montarMapaVazio(JPanel panel1, Map<String,JButton>coordenadasmap) {
		JButton botao = null;
		for(int i = 0; i < 17; i++)
			for(int j = 0; j < 17; j++){
				if(i == 0 || i==16) { //Se primeira ou ultima linha
					if(j==0 || j==16) {//Extremidades
						botao = new JButton(" ");
						botao.setName(CodigoButtonEnum.VAZIO.getDescricao());//V de Vazio
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						panel1.add(botao);
					}
					else {
						botao = new JButton(j+"");
						botao.setName(CodigoButtonEnum.NUMERO.getDescricao());//num de numero!
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						panel1.add(botao);
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
						panel1.add(botao);
					}
					else if(j==0 || j==16) {//Bordas Laterais
						botao = new JButton(SocketUtil.converterNumeroEmLetra(i));
						botao.setName(i + "," + j);
						botao.setToolTipText(i + "," + j);
						botao.setBorder(null);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						panel1.add(botao);
					}else {//Miolo do mapa
						botao = new JButton(" ");
						botao.setName(CodigoButtonEnum.POSICAO.getDescricao());//Vai conter o COD da embarca��o.
						botao.setToolTipText(i + "," + j);//Vai conter a coordenada.
						botao.setBackground(Color.cyan);
						botao.setBorder(new LineBorder(Color.BLACK,1,false));
						//botao.addActionListener(this);
						coordenadasmap.put(i + "," + j, botao);
						panel1.add(botao);
					}
				}
			} 
	}
	public void limparTela() {
		for (String key : this.coordenadasmapAdversario.keySet()) {//recoloca as cores.
			JButton b = coordenadasmapAdversario.get(key);
			b.setBackground(Color.cyan);
			b.setName(CodigoButtonEnum.POSICAO.getDescricao());
			b.setEnabled(true);
		}
		for (String key : this.coordenadasmapCliente.keySet()) {
			JButton b = coordenadasmapCliente.get(key);
			b.setBackground(Color.cyan);
			b.setName(CodigoButtonEnum.POSICAO.getDescricao());
			b.setEnabled(true);
		}
		this.txtFieldX1.setText("");
		this.txtFieldX2.setText("");
		this.txtFieldX3.setText("");
		this.txtFieldY1.setText("");
		this.txtFieldY2.setText("");
		this.txtFieldY3.setText("");	
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
	public Map<String, JButton> getCoordenadasmapCliente() {
		return coordenadasmapCliente;
	}

	/**
	 * @param coordenadasmap the coordenadasmap to set
	 */
	public void setCoordenadasmapCliente(Map<String, JButton> coordenadasmap) {
		this.coordenadasmapCliente = coordenadasmap;
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

	public Map<String, JButton> getCoordenadasmapAdversario() {
		return coordenadasmapAdversario;
	}

	public void setCoordenadasmapAdversario(Map<String, JButton> coordenadasmapAdversario) {
		this.coordenadasmapAdversario = coordenadasmapAdversario;
	}

	public JLabel getLblSeuJogo() {
		return lblSeuJogo;
	}

	public JLabel getLblJogoAdversario() {
		return lblJogoAdversario;
	}

	public JLabel getLblTiro1() {
		return lblTiro1;
	}

	public JLabel getLblTiro2() {
		return lblTiro2;
	}

	public JLabel getLblTiro3() {
		return lblTiro3;
	}

	public JLabel getLblCoordX() {
		return lblCoordX;
	}

	public JTextField getTxtFieldX1() {
		return txtFieldX1;
	}

	public JTextField getTxtFieldY1() {
		return txtFieldY1;
	}

	public JTextField getTxtFieldY2() {
		return txtFieldY2;
	}

	public JTextField getTxtFieldX2() {
		return txtFieldX2;
	}

	public JTextField getTxtFieldX3() {
		return txtFieldX3;
	}

	public JTextField getTxtFieldY3() {
		return txtFieldY3;
	}

	
}
