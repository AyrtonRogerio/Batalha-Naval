package br.edu.psd.batalhanaval.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.SocketUtil;

public class TelaCriarMapa extends JFrame implements ActionListener{//Vou colocar o evento aqui, pq não tem o botao de entrar nessa tela pronto, quando for criado eu modifico e trago o respeito ao mvc.
	private GridLayout mapaGrid;
	private  Map<String,JButton>coordenadasmap = new HashMap<String,JButton>();
	private JPanel panelEmbarcacoes;
	private JPanel 	panelMapa;
	private JButton selecionarPortaAvButton;
	private JLabel quantPortaAvLabel;
	private JLabel quantencouracadoLabel;
	private JButton selecionarEncButton;
	private JButton selecionarCruzButton;
	private JLabel quantCruzLabel;
	private JLabel quantHidroLabel;
	private JButton selecionarHidroButton ;
	private JLabel quantSubLabel;
	private JButton selecionarSubButton;
	public TelaCriarMapa() {

		setLayout(new BorderLayout(0, 0));
		this.setBackground(Color.white);
		setSize(800,700);
		setLocationRelativeTo(null);
		
		panelEmbarcacoes = new JPanel();
		panelEmbarcacoes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "EMBARCACOES", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.SANS_SERIF,Font.BOLD,14), new Color(0, 0, 0)));
		panelEmbarcacoes.setBackground(Color.WHITE);
		add(panelEmbarcacoes, BorderLayout.SOUTH);
		panelEmbarcacoes.setLayout(null);

		panelMapa = new JPanel();
		panelMapa.setBackground(Color.white);
		add(panelMapa, BorderLayout.CENTER);

		panelMapa.setLayout(mapaGrid = new GridLayout(17, 17, 0, 0));
		montarMapaVazio();

		panelEmbarcacoes.setPreferredSize(new Dimension(450, 200));

		JButton portaAv1 = new JButton("");
		portaAv1.setBackground(Color.BLACK);
		portaAv1.setBounds(11, 38, 22, 23);
		panelEmbarcacoes.add(portaAv1);

		JButton portaAv2 = new JButton("");
		portaAv2.setBackground(Color.BLACK);
		portaAv2.setBounds(33, 38, 22, 23);
		panelEmbarcacoes.add(portaAv2);

		JButton portaAv3 = new JButton("");
		portaAv3.setBackground(Color.BLACK);
		portaAv3.setBounds(55, 38, 22, 23);
		panelEmbarcacoes.add(portaAv3);

		JButton portaAv4 = new JButton("");
		portaAv4.setBackground(Color.BLACK);
		portaAv4.setBounds(77, 38, 22, 23);
		panelEmbarcacoes.add(portaAv4);

		JButton portaAv5 = new JButton("");
		portaAv5.setBackground(Color.BLACK);
		portaAv5.setBounds(99, 38, 22, 23);
		panelEmbarcacoes.add(portaAv5);

		JLabel lblPortaAvies = new JLabel("PORTA AVIOES");
		lblPortaAvies.setBounds(10, 11, 111, 33);
		panelEmbarcacoes.add(lblPortaAvies);

		selecionarPortaAvButton = new JButton("Select");
		selecionarPortaAvButton.setToolTipText("posicionar 1a Pe\u00E7a");
		selecionarPortaAvButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionarPortaAvButton.setForeground(Color.WHITE);
		selecionarPortaAvButton.setBackground(Color.BLUE);
		selecionarPortaAvButton.setBounds(131, 38, 68, 49);
		panelEmbarcacoes.add(selecionarPortaAvButton);

		quantPortaAvLabel = new JLabel("1");
		quantPortaAvLabel.setBounds(65, 68, 30, 19);
		panelEmbarcacoes.add(quantPortaAvLabel);

		JLabel lblRestam = new JLabel("Restam: ");
		lblRestam.setBounds(10, 68, 56, 19);
		panelEmbarcacoes.add(lblRestam);

		JLabel encouracadoLabel = new JLabel("Encoura\u00E7ado");
		encouracadoLabel.setBounds(11, 91, 111, 33);
		panelEmbarcacoes.add(encouracadoLabel);

		JButton encouracado1 = new JButton("");
		encouracado1.setBackground(Color.BLACK);
		encouracado1.setBounds(11, 115, 22, 23);
		panelEmbarcacoes.add(encouracado1);

		JButton encouracado2 = new JButton("");
		encouracado2.setBackground(Color.BLACK);
		encouracado2.setBounds(33, 115, 22, 23);
		panelEmbarcacoes.add(encouracado2);

		JButton encouracado3 = new JButton("");
		encouracado3.setBackground(Color.BLACK);
		encouracado3.setBounds(55, 115, 22, 23);
		panelEmbarcacoes.add(encouracado3);

		JButton encouracado = new JButton("");
		encouracado.setBackground(Color.BLACK);
		encouracado.setBounds(77, 115, 22, 23);
		panelEmbarcacoes.add(encouracado);

		JLabel label = new JLabel("Restam: ");
		label.setBounds(11, 144, 66, 19);
		panelEmbarcacoes.add(label);

		quantencouracadoLabel = new JLabel("2");
		quantencouracadoLabel.setBounds(69, 144, 30, 19);
		panelEmbarcacoes.add(quantencouracadoLabel);

		selecionarEncButton = new JButton("Select");
		selecionarEncButton.setToolTipText("posicionar 1a Pe\u00E7a");
		selecionarEncButton.setForeground(Color.WHITE);
		selecionarEncButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionarEncButton.setBackground(Color.BLACK);
		selecionarEncButton.setBounds(131, 115, 68, 49);
		panelEmbarcacoes.add(selecionarEncButton);

		JLabel curzadoresLabel = new JLabel("Cruzadores");
		curzadoresLabel.setBounds(230, 11, 111, 33);
		panelEmbarcacoes.add(curzadoresLabel);

		JButton cruzadores1 = new JButton("");
		cruzadores1.setBackground(Color.BLACK);
		cruzadores1.setBounds(230, 38, 22, 23);
		panelEmbarcacoes.add(cruzadores1);

		JButton cruzadores2 = new JButton("");
		cruzadores2.setBackground(Color.BLACK);
		cruzadores2.setBounds(251, 38, 22, 23);
		panelEmbarcacoes.add(cruzadores2);

		selecionarCruzButton = new JButton("Select");
		selecionarCruzButton.setToolTipText("posicionar 1a Pe\u00E7a");
		selecionarCruzButton.setForeground(Color.WHITE);
		selecionarCruzButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionarCruzButton.setBackground(Color.BLACK);
		selecionarCruzButton.setBounds(295, 38, 68, 49);
		panelEmbarcacoes.add(selecionarCruzButton);

		JLabel label_1 = new JLabel("Restam: ");
		label_1.setBounds(209, 68, 64, 19);
		panelEmbarcacoes.add(label_1);

		quantCruzLabel = new JLabel("3");
		quantCruzLabel.setBounds(272, 68, 30, 19);
		panelEmbarcacoes.add(quantCruzLabel);

		JLabel hidroAvLabel = new JLabel("Hidro Avioes");
		hidroAvLabel.setBounds(228, 91, 111, 33);
		panelEmbarcacoes.add(hidroAvLabel);

		JButton button = new JButton("");
		button.setBackground(Color.BLACK);
		button.setBounds(230, 138, 22, 23);
		panelEmbarcacoes.add(button);

		JButton button_1 = new JButton("");
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(251, 115, 22, 23);
		panelEmbarcacoes.add(button_1);

		JButton button_2 = new JButton("");
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(272, 138, 22, 23);
		panelEmbarcacoes.add(button_2);

		JLabel label_2 = new JLabel("Restam: ");
		label_2.setBounds(230, 172, 64, 19);
		panelEmbarcacoes.add(label_2);

		quantHidroLabel = new JLabel("5");
		quantHidroLabel.setBounds(282, 172, 30, 19);
		panelEmbarcacoes.add(quantHidroLabel);

		selecionarHidroButton = new JButton("Select");
		selecionarHidroButton.setToolTipText("posicionar 1a Pe\u00E7a");
		selecionarHidroButton.setForeground(Color.WHITE);
		selecionarHidroButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionarHidroButton.setBackground(Color.BLACK);
		selecionarHidroButton.setBounds(295, 114, 68, 49);
		panelEmbarcacoes.add(selecionarHidroButton);

		JLabel lblSubmarino = new JLabel("Submarino");
		lblSubmarino.setBounds(370, 11, 111, 33);
		panelEmbarcacoes.add(lblSubmarino);

		JButton sub1 = new JButton("");
		sub1.setBackground(Color.BLACK);
		sub1.setBounds(373, 38, 22, 23);
		panelEmbarcacoes.add(sub1);

		JLabel label_4 = new JLabel("Restam: ");
		label_4.setBounds(369, 68, 56, 19);
		panelEmbarcacoes.add(label_4);

		quantSubLabel = new JLabel("4");
		quantSubLabel.setBounds(418, 68, 22, 19);
		panelEmbarcacoes.add(quantSubLabel);

		selecionarSubButton = new JButton("Select");
		selecionarSubButton.setToolTipText("posicionar 1a Pe\u00E7a");
		selecionarSubButton.setForeground(Color.WHITE);
		selecionarSubButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionarSubButton.setBackground(Color.BLACK);
		selecionarSubButton.setBounds(442, 38, 68, 49);
		panelEmbarcacoes.add(selecionarSubButton);
	}

	private void montarMapaVazio() {
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
						this.panelMapa.add(botao);
					}
					else {
						botao = new JButton(j+"");
						botao.setName("[NUM]");
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						this.panelMapa.add(botao);
					}
				}
				else {//Se não for primeira ou ultima linha
					if(i==0 && j==0 || i==16 && j==16 || i==0 && j==16 || i==16 && j==0) {//Captura as extremidades.
						botao = new JButton(" ");
						botao.setName(i + "," + j);
						botao.setToolTipText(i + "," + j);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						botao.setBorder(null);
						this.panelMapa.add(botao);
					}
					else if(j==0 || j==16) {//Bordas Laterais
						botao = new JButton(SocketUtil.converterNumeroEmLetra(i));
						botao.setName(i + "," + j);
						botao.setToolTipText(i + "," + j);
						botao.setBorder(null);
						botao.setBackground(Color.white);
						botao.setOpaque(false);
						this.panelMapa.add(botao);
					}else {//Miolo do mapa
						botao = new JButton(" ");
						botao.setName("[POS]");//Vai conter o COD da embarcação.
						botao.setToolTipText(i + "," + j);//Vai conter a coordenada.
						botao.setBackground(Color.cyan);
						botao.setBorder(new LineBorder(Color.BLACK,1,false));
						botao.addActionListener(this);
						coordenadasmap.put(i + "," + j, botao);
						this.panelMapa.add(botao);
					}
				}
			} 
	}

	public JButton getSelecionarPortaAvButton() {
		return selecionarPortaAvButton;
	}
	public void setSelecionarPortaAvButton(JButton selecionarPortaAvButton) {
		this.selecionarPortaAvButton = selecionarPortaAvButton;
	}

	public JLabel getQuantPortaAvLabel() {
		return quantPortaAvLabel;
	}

	public void setQuantPortaAvLabel(JLabel quantPortaAvLabel) {
		this.quantPortaAvLabel = quantPortaAvLabel;
	}

	public JLabel getQuantencouracadoLabel() {
		return quantencouracadoLabel;
	}

	public void setQuantencouracadoLabel(JLabel quantencouracadoLabel) {
		this.quantencouracadoLabel = quantencouracadoLabel;
	}

	public JButton getSelecionarEncButton() {
		return selecionarEncButton;
	}

	public void setSelecionarEncButton(JButton selecionarEncButton) {
		this.selecionarEncButton = selecionarEncButton;
	}

	public JButton getSelecionarCruzButton() {
		return selecionarCruzButton;
	}

	public void setSelecionarCruzButton(JButton selecionarCruzButton) {
		this.selecionarCruzButton = selecionarCruzButton;
	}

	public JLabel getQuantCruzLabel() {
		return quantCruzLabel;
	}

	public void setQuantCruzLabel(JLabel quantCruzLabel) {
		this.quantCruzLabel = quantCruzLabel;
	}

	public JLabel getQuantHidroLabel() {
		return quantHidroLabel;
	}

	public void setQuantHidroLabel(JLabel quantHidroLabel) {
		this.quantHidroLabel = quantHidroLabel;
	}

	public JButton getSelecionarHidroButton() {
		return selecionarHidroButton;
	}

	public void setSelecionarHidroButton(JButton selecionarHidroButton) {
		this.selecionarHidroButton = selecionarHidroButton;
	}

	public JLabel getQuantSubLabel() {
		return quantSubLabel;
	}

	public void setQuantSubLabel(JLabel quantSubLabel) {
		this.quantSubLabel = quantSubLabel;
	}

	public JButton getSelecionarSubButton() {
		return selecionarSubButton;
	}

	public void setSelecionarSubButton(JButton selecionarSubButton) {
		this.selecionarSubButton = selecionarSubButton;
	}
	public Map<String, JButton> getCoordenadasmap() {
		return coordenadasmap;
	}

	public void setCoordenadasmap(Map<String, JButton> coordenadasmap) {
		this.coordenadasmap = coordenadasmap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = ((JButton)(e.getSource()));
		System.out.println(button.getName());
		System.out.println(button.getToolTipText());	
		if(this.selecionarEncButton.getBackground()==Color.BLUE) {//então esta selecionado.
			System.out.println("Entrou");
			if(EmbarcacoesUtil.getEncouracadosPosicionados()>=EmbarcacoesUtil.NUMEMCOURACADO) {//Olha se já chegou no limite de embarcações.
				JOptionPane.showMessageDialog(null," Não eh possivel posicionar mais Encouraçados.");
				this.selecionarEncButton.setBackground(Color.red);
				return;
			}else {
				if(!button.getName().equalsIgnoreCase("[POS]")) {
					JOptionPane.showMessageDialog(null," Não eh possivel posicionar navio!");
					return;
				}else {
					this.inserirEmbarcacao(button, "ENCOURACADO");
				}
			}
			//Mesma coisa pros outros.
		}
		else if(this.selecionarCruzButton.getBackground()==Color.BLUE) {//então esta selecionado.
			System.out.println("Entrou CRUZ");
			if(EmbarcacoesUtil.getCruzadoresPosicionados()>=EmbarcacoesUtil.NUMCRUZADORES) {//Olha se já chegou no limite de embarcações.
				JOptionPane.showMessageDialog(null," Não eh possivel posicionar mais CRUZA.");
				this.selecionarCruzButton.setBackground(Color.red);
				return;
			}else {
				if(!button.getName().equalsIgnoreCase("[POS]")) {
					JOptionPane.showMessageDialog(null," Não eh possivel posicionar navio!");
					return;
				}else {
					this.inserirEmbarcacao(button, "CRUZADORES");
				}
			}
		}
		else if(this.selecionarHidroButton.getBackground()==Color.BLUE) {//então esta selecionado.
			System.out.println("Entrou HIDRO");
			if(EmbarcacoesUtil.getHidroAvPosicionados()>=EmbarcacoesUtil.NUMHIDROAV) {//Olha se já chegou no limite de embarcações.
				JOptionPane.showMessageDialog(null," Não eh possivel posicionar mais HIDRO.");
				this.selecionarHidroButton.setBackground(Color.red);
				return;
			}else {
				if(!button.getName().equalsIgnoreCase("[POS]")) {
					JOptionPane.showMessageDialog(null," Não eh possivel posicionar navio!");
					return;
				}else {
					this.inserirEmbarcacao(button, "HIDROAVIAO");
				}
			}
		}
		else if(this.selecionarPortaAvButton.getBackground()==Color.BLUE) {//então esta selecionado.
			System.out.println("Entrou PA");
			if(EmbarcacoesUtil.getPortaAvPosicionados()>=EmbarcacoesUtil.NUMPORTAAV) {//Olha se já chegou no limite de embarcações.
				JOptionPane.showMessageDialog(null," Não eh possivel posicionar mais PA.");
				this.selecionarPortaAvButton.setBackground(Color.red);
				return;
			}else {
				if(!button.getName().equalsIgnoreCase("[POS]")) {
					JOptionPane.showMessageDialog(null," Não eh possivel posicionar navio!");
					return;
				}else {
					this.inserirEmbarcacao(button, "PORTAAVIOES");
				}
			}
		}
		else if(this.selecionarSubButton.getBackground()==Color.BLUE) {//então esta selecionado.
			System.out.println("Entrou SUB");
			if(EmbarcacoesUtil.getSubimarinosPosicionados()>=EmbarcacoesUtil.NUMSUBMARINO) {//Olha se já chegou no limite de embarcações.
				JOptionPane.showMessageDialog(null," Não eh possivel posicionar mais SUB.");
				this.selecionarSubButton.setBackground(Color.red);
				return;
			}else {
				if(!button.getName().equalsIgnoreCase("[POS]")) {
					JOptionPane.showMessageDialog(null," Não eh possivel posicionar navio!");
					return;
				}else {
					this.inserirEmbarcacao(button, "SUBMARINO");
				}
			}
		}
	}
	private void inserirEmbarcacao(JButton b,String navio) {//Lembrando que o user escolhe apenas a primeira posicao e o  rresto é preenchida automaticamente
		String s[] = b.getToolTipText().split(",");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		try {
			if(navio.equals("ENCOURACADO")) {//se possivel transformar num enum esse nome.

				if(!coordenadasmap.get(x+","+y).getName().equals("[POS]") || !coordenadasmap.get(x+","+(y+1)).getName().equals("[POS]")||
						!coordenadasmap.get(x+","+(y+2)).getName().equals("[POS]")|| !coordenadasmap.get(x+","+(y+3)).getName().equals("[POS]")) {
					JOptionPane.showMessageDialog(null, "Pelo Tamanho do navio não eh possivel inserilo nesta Posição");
					return;
				}else {
					coordenadasmap.get(x+","+y).setName("ENC");
					coordenadasmap.get(x+","+y).setBackground(Color.BLACK);

					coordenadasmap.get(x+","+(y+1)).setName("ENC");
					coordenadasmap.get(x+","+(y+1)).setBackground(Color.BLACK);

					coordenadasmap.get(x+","+(y+2)).setName("ENC");
					coordenadasmap.get(x+","+(y+2)).setBackground(Color.BLACK);

					coordenadasmap.get(x+","+(y+3)).setName("ENC");
					coordenadasmap.get(x+","+(y+3)).setBackground(Color.BLACK);
					EmbarcacoesUtil.setEncouracadosPosicionados(EmbarcacoesUtil.getEncouracadosPosicionados()+1);
					this.quantencouracadoLabel.setText(EmbarcacoesUtil.NUMEMCOURACADO-EmbarcacoesUtil.getEncouracadosPosicionados()+"");
					System.out.println(EmbarcacoesUtil.getEncouracadosPosicionados()+"  POSICIO");
					if(EmbarcacoesUtil.NUMEMCOURACADO == EmbarcacoesUtil.getEncouracadosPosicionados()) {
						this.selecionarEncButton.setBackground(Color.RED);
					}
				}
			}
			else if(navio.equals("PORTAAVIOES")) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).getName().equals("[POS]") || !coordenadasmap.get(x+","+(y+1)).getName().equals("[POS]")||
						!coordenadasmap.get(x+","+(y+2)).getName().equals("[POS]")|| !coordenadasmap.get(x+","+(y+3)).getName().equals("[POS]") || !coordenadasmap.get(x+","+(y+4)).getName().equals("[POS]")) {
					JOptionPane.showMessageDialog(null, "Pelo Tamanho do navio não eh possivel inserilo nesta Posição");
					return;
				}else {
					coordenadasmap.get(x+","+y).setName("PA");
					coordenadasmap.get(x+","+y).setBackground(Color.red);

					coordenadasmap.get(x+","+(y+1)).setName("PA");
					coordenadasmap.get(x+","+(y+1)).setBackground(Color.red);

					coordenadasmap.get(x+","+(y+2)).setName("PA");
					coordenadasmap.get(x+","+(y+2)).setBackground(Color.red);

					coordenadasmap.get(x+","+(y+3)).setName("PA");
					coordenadasmap.get(x+","+(y+3)).setBackground(Color.red);

					coordenadasmap.get(x+","+(y+4)).setName("PA");
					coordenadasmap.get(x+","+(y+4)).setBackground(Color.red);

					EmbarcacoesUtil.setPortaAvPosicionados(EmbarcacoesUtil.getPortaAvPosicionados()+1);
					this.quantPortaAvLabel.setText(EmbarcacoesUtil.NUMPORTAAV-EmbarcacoesUtil.getPortaAvPosicionados()+"");
					System.out.println(EmbarcacoesUtil.getPortaAvPosicionados()+"  POSICIO");
					if(EmbarcacoesUtil.NUMPORTAAV == EmbarcacoesUtil.getPortaAvPosicionados()) {
						this.selecionarPortaAvButton.setBackground(Color.RED);
					}
				}
			}
			else if(navio.equals("CRUZADORES")) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).getName().equals("[POS]") || !coordenadasmap.get(x+","+(y+1)).getName().equals("[POS]")) {
					JOptionPane.showMessageDialog(null, "Pelo Tamanho do navio não eh possivel inserilo nesta Posição");
					return;
				}else {
					coordenadasmap.get(x+","+y).setName("CRUZ");
					coordenadasmap.get(x+","+y).setBackground(Color.yellow);

					coordenadasmap.get(x+","+(y+1)).setName("CRUZ");
					coordenadasmap.get(x+","+(y+1)).setBackground(Color.yellow);

					EmbarcacoesUtil.setCruzadoresPosicionados(EmbarcacoesUtil.getCruzadoresPosicionados()+1);
					this.quantCruzLabel.setText(EmbarcacoesUtil.NUMCRUZADORES-EmbarcacoesUtil.getCruzadoresPosicionados()+"");
					System.out.println(EmbarcacoesUtil.getPortaAvPosicionados()+"  POSICIO");
					if(EmbarcacoesUtil.NUMCRUZADORES== EmbarcacoesUtil.getCruzadoresPosicionados()) {
						this.selecionarCruzButton.setBackground(Color.RED);
					}
				}
			}
			if(navio.equals("HIDROAVIAO")) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).getName().equals("[POS]") || !coordenadasmap.get((x+1)+","+(y-1)).getName().equals("[POS]") ||  !coordenadasmap.get((x+1)+","+(y+1)).getName().equals("[POS]")) {
					JOptionPane.showMessageDialog(null, "Pelo Tamanho do navio não eh possivel inserilo nesta Posição");
					return;
				}else {
					coordenadasmap.get(x+","+y).setName("HIDRO");
					coordenadasmap.get(x+","+y).setBackground(Color.GREEN);

					coordenadasmap.get((x+1)+","+(y-1)).setName("HIDRO");
					coordenadasmap.get((x+1)+","+(y-1)).setBackground(Color.GREEN);
					
					coordenadasmap.get((x+1)+","+(y+1)).setName("HIDRO");
					coordenadasmap.get((x+1)+","+(y+1)).setBackground(Color.GREEN);

					EmbarcacoesUtil.setHidroAvPosicionados(EmbarcacoesUtil.getHidroAvPosicionados()+1);
					this.quantHidroLabel.setText(EmbarcacoesUtil.NUMHIDROAV-EmbarcacoesUtil.getHidroAvPosicionados()+"");
					System.out.println(EmbarcacoesUtil.getHidroAvPosicionados()+"  POSICIO");
					if(EmbarcacoesUtil.NUMHIDROAV== EmbarcacoesUtil.getHidroAvPosicionados()) {
						this.selecionarHidroButton.setBackground(Color.RED);
					}
				}
			}
			else if(navio.equals("SUBMARINO")) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).getName().equals("[POS]")) {
					JOptionPane.showMessageDialog(null, "Pelo Tamanho do navio não eh possivel inserilo nesta Posição");
					return;
				}
				else {
					coordenadasmap.get(x+","+y).setName("SUB");
					coordenadasmap.get(x+","+y).setBackground(Color.DARK_GRAY);
					
					EmbarcacoesUtil.setSubimarinosPosicionados(EmbarcacoesUtil.getSubimarinosPosicionados()+1);
					this.quantSubLabel.setText(EmbarcacoesUtil.NUMSUBMARINO-EmbarcacoesUtil.getSubimarinosPosicionados()+"");
					System.out.println(EmbarcacoesUtil.getSubimarinosPosicionados()+"  POSICIO");
					if(EmbarcacoesUtil.NUMSUBMARINO== EmbarcacoesUtil.getSubimarinosPosicionados()) {
						this.selecionarSubButton.setBackground(Color.RED);
					}
				}
			}
		}catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não eh possivel inserir navio!");
		}
	}
	
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new TelaCriarMapa());
		f.setVisible(true);
	}

}