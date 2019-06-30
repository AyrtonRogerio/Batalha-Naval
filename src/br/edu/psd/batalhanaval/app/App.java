/**
 * 
 */
package br.edu.psd.batalhanaval.app;

import br.edu.psd.batalhanaval.controller.Controller;
import br.edu.psd.batalhanaval.controller.ControllerTelaCliente;
import br.edu.psd.batalhanaval.controller.ControllerTelaCriarMapa;
import br.edu.psd.batalhanaval.view.Tela;
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
		
		Tela tela = new Tela();
		TelaCriarMapa telaCriarMapa = new TelaCriarMapa();
		new Controller(tela.getTelaEscolha(), tela);
		new ControllerTelaCliente(tela.getTelaEscolha().getTelaCliente(), tela, telaCriarMapa);
		new ControllerTelaCriarMapa(telaCriarMapa);
		



	}

}
