package br.edu.psd.batalhanaval.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.edu.psd.batalhanaval.model.TableModel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class TelaEscolherOponente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnDesafiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherOponente frame = new TelaEscolherOponente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolherOponente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblOpa = new JLabel("Jogadores online");
		lblOpa.setBackground(Color.WHITE);
		lblOpa.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblOpa.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOpa,BorderLayout.NORTH);
		TableModel tableModel = new TableModel();
		table = new JTable(tableModel);
		table.setRowHeight(50);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(450, 40));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);
		
		btnDesafiar = new JButton("Desafiar");
		btnDesafiar.setBounds(335, 11, 89, 23);
		panel_1.add(btnDesafiar);
	
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnDesafiar() {
		return btnDesafiar;
	}
	
	
}
