/**
 * 
 */
package br.edu.psd.batalhanaval.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 * @author ayrton
 *
 */
public class TelaEscolherOponente extends JFrame {

	private JPanel contentPane, panel;
	private JScrollPane scrollPane;
	private JLabel lblJogadoresOnline;
	
	
	/**
	 * Create the frame.
	 */
	public TelaEscolherOponente() {
		
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(panel);
		
		lblJogadoresOnline = new JLabel("Jogadores Online");
		panel.add(lblJogadoresOnline);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
	}

	
	/**
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return contentPane;
	}



	/**
	 * @param contentPane the contentPane to set
	 */
	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
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
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}



	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}



	public static void main(String[] args) {
		new TelaEscolherOponente();
	}
	
}
