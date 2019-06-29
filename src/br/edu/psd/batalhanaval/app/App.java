/**
 * 
 */
package br.edu.psd.batalhanaval.app;

import javax.swing.JFrame;

import br.edu.psd.batalhanaval.controller.ControllerTelaCriarMapa;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;

/**
 * @author ayrton
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Teste tela de inserção de Navio.
		TelaCriarMapa tcm = new TelaCriarMapa();
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(tcm);
		new ControllerTelaCriarMapa(tcm);
		f.setVisible(true);



	}

}
