package br.edu.psd.batalhanaval.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.edu.psd.batalhanaval.view.TelaCriarMapa;

public class ControllerTelaCriarMapa implements ActionListener{
	private TelaCriarMapa telaCMapa;
	public ControllerTelaCriarMapa(TelaCriarMapa telaCMapa) {
		this.telaCMapa=telaCMapa;
		this.telaCMapa.getSelecionarCruzButton().addActionListener(this);
		this.telaCMapa.getSelecionarEncButton().addActionListener(this);
		this.telaCMapa.getSelecionarHidroButton().addActionListener(this);
		this.telaCMapa.getSelecionarPortaAvButton().addActionListener(this);
		this.telaCMapa.getSelecionarSubButton().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(this.telaCMapa.getSelecionarCruzButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarCruzButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarEncButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarEncButton().setBackground(Color.BLACK);

		if(this.telaCMapa.getSelecionarHidroButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarHidroButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarPortaAvButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarPortaAvButton().setBackground(Color.BLACK);
		
		if(this.telaCMapa.getSelecionarSubButton().getBackground()==Color.BLUE)
			this.telaCMapa.getSelecionarSubButton().setBackground(Color.BLACK);
		
		if(evt.getSource()==this.telaCMapa.getSelecionarCruzButton()) {
			if(this.telaCMapa.getSelecionarCruzButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarCruzButton().setBackground(Color.BLUE);
			}
		}else if(evt.getSource()==this.telaCMapa.getSelecionarEncButton()) {
			if(this.telaCMapa.getSelecionarEncButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarEncButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarHidroButton()) {
			if(this.telaCMapa.getSelecionarHidroButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarHidroButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarPortaAvButton()) {
			if(this.telaCMapa.getSelecionarPortaAvButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarPortaAvButton().setBackground(Color.BLUE);
			}
		}
		else if(evt.getSource()==this.telaCMapa.getSelecionarSubButton()) {
			if(this.telaCMapa.getSelecionarSubButton().getBackground()==Color.black) {
				this.telaCMapa.getSelecionarSubButton().setBackground(Color.BLUE);
			}
		}
	}
	private void mudarcor(JButton b) {
		
	}
}
