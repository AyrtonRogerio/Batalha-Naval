/**
 * 
 */
package br.edu.psd.batalhanaval.app;

import br.edu.psd.batalhanaval.controller.ControlerTelaEscolherOponente;
import br.edu.psd.batalhanaval.controller.Controller;
import br.edu.psd.batalhanaval.controller.ControllerTelaCliente;
import br.edu.psd.batalhanaval.controller.ControllerTelaCriarMapa;
import br.edu.psd.batalhanaval.controller.ControllerTelaJogo;
import br.edu.psd.batalhanaval.view.Tela;
import br.edu.psd.batalhanaval.view.TelaCriarMapa;
import br.edu.psd.batalhanaval.view.TelaEscolherOponente;
import br.edu.psd.batalhanaval.view.TelaJogo;

/**
 * @author ayrton
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Teste tela de inser��o de Navio.

		System.out.println();
		Tela tela = new Tela();
		TelaCriarMapa telaCriarMapa = new TelaCriarMapa();
		TelaEscolherOponente telaEscolherOponente = new TelaEscolherOponente();
		TelaJogo telaJogo = new TelaJogo();
	
		Controller controller = new Controller(tela.getTelaEscolha(), tela);
		ControllerTelaCliente cTelaCliente = new ControllerTelaCliente(tela.getTelaEscolha().getTelaCliente(), tela, telaCriarMapa, telaEscolherOponente);
		
		ControllerTelaCriarMapa cTelaCriarMapa = new ControllerTelaCriarMapa(telaCriarMapa, telaJogo);
		new ControllerTelaJogo(telaJogo );

	}

}
