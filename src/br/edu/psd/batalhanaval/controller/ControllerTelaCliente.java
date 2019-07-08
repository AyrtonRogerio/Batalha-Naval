package br.edu.psd.batalhanaval.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.edu.psd.batalhanaval.Util.ProtocoloUtil;
import br.edu.psd.batalhanaval.Util.SocketUtil;
import br.edu.psd.batalhanaval.model.socket.Cliente;
import br.edu.psd.batalhanaval.view.PanelCliente;
import br.edu.psd.batalhanaval.view.Tela;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;

public class ControllerTelaCliente implements ActionListener {
	private PanelCliente telaCliente;
	private TelaCriarMapa telaCriarMapa;
	private TelaEscolherOponente telaEscolherOponente;
	private Tela telaEscolhaClienteOrServer;
	private Cliente c;

	public ControllerTelaCliente(PanelCliente cliente, Tela t, TelaCriarMapa telaCriarMapa, 
			TelaEscolherOponente telaEscolherOponente) {
		this.telaCliente = cliente;
		this.telaEscolhaClienteOrServer = t;
		this.telaCriarMapa = telaCriarMapa;
		this.telaEscolherOponente = telaEscolherOponente;
		this.telaCliente.getBtnJogar().addActionListener(this);
		this.telaCliente.getBtnVoltar().addActionListener(this);
		this.telaCliente.getRdBtnJogarOff().addActionListener(this);
		this.telaCliente.getRdBtnJogarOn().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.telaCliente.getRdBtnJogarOff()) {

			if (this.telaCliente.getRdBtnJogarOff().isSelected()) {
				this.telaCliente.getTxtFieldIpServidor().setEditable(false);
				this.telaCliente.getTxtFieldPorta().setEditable(false);
				this.telaCliente.getRdBtnJogarOn().setSelected(false);
				this.telaCliente.getRdBtnJogarOff().setSelected(true);

			}

		}

		if (e.getSource() == this.telaCliente.getRdBtnJogarOn()) {

			if (this.telaCliente.getRdBtnJogarOn().isSelected()) {

				this.telaCliente.getTxtFieldIpServidor().setEditable(true);
				this.telaCliente.getTxtFieldPorta().setEditable(true);
				this.telaCliente.getRdBtnJogarOn().setSelected(true);
				this.telaCliente.getRdBtnJogarOff().setSelected(false);

			}

		}

		if (this.telaCliente.getBtnJogar() == e.getSource()) {
			try {

				if (this.telaCliente.getRdBtnJogarOn().isSelected()) {
					SocketUtil.offiline = false;
					if (!(this.telaCliente.getTxtFieldIpServidor().getText().trim().isEmpty()
							|| this.telaCliente.getTxtFieldNome().getText().trim().isEmpty()
							|| this.telaCliente.getTxtFieldPorta().getText().trim().isEmpty())) {

						c = new Cliente(Integer.parseInt(this.telaCliente.getTxtFieldPorta().getText().trim()),
						this.telaCliente.getTxtFieldIpServidor().getText().trim(), this.telaCliente.getTxtFieldNome().getText().trim(),telaEscolherOponente);
						SocketUtil.setClienteCorrente(c);
						telaEscolhaClienteOrServer.setVisible(false);
						new Thread(c).start();
						c.enviaMensagem(ProtocoloUtil.NOME+";"+c.getNome());
						//c.enviaMensagem(ProtocoloUtil.LISTA_USER_ONLINE+";"+c.getNome());
						telaEscolherOponente.setVisible(true);
						telaCriarMapa.setVisible(false);
						ControlerTelaEscolherOponente controlerTelaEscolherOponente = new ControlerTelaEscolherOponente(getC(),getTelaEscolherOponente(),telaCriarMapa);
					} else {
						JOptionPane.showMessageDialog(null, "Pro favor, preencha os campos para poder jogar Online.",
								"ATENÇÃO!", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				if (this.telaCliente.getRdBtnJogarOff().isSelected()) {
					SocketUtil.offiline = true;
					if (!(this.telaCliente.getTxtFieldNome().getText().trim().isEmpty())) {
						Cliente c = new Cliente(this.telaCliente.getTxtFieldNome().getText().trim());
						SocketUtil.setClienteCorrente(c);
						//new Thread(c).start();
						telaEscolhaClienteOrServer.setVisible(false);
						this.telaCriarMapa.resetarLabelsNavios();
						telaCriarMapa.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Pro favor, informe o seu nome para poder jogar.",
								"ATENÇÃO!", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getSource() == this.telaCliente.getBtnVoltar()) {

			this.telaCliente.setVisible(false);
			telaEscolhaClienteOrServer.getTelaEscolha().setVisible(true);

		}

	}

	public Cliente getC() {
		return c;
	}

	public TelaEscolherOponente getTelaEscolherOponente() {
		return telaEscolherOponente;
	}
	
	
}
