package br.edu.psd.batalhanaval.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;

import br.edu.psd.batalhanaval.Util.EmbarcacoesUtil;
import br.edu.psd.batalhanaval.Util.Enum.CodigoButtonEnum;
import br.edu.psd.batalhanaval.Util.Enum.TipoEmbarcacaoEnum;

public class MontadorDeMapa {

	public static void montarMapaMeuJogoAtual(Map<String,JButton>cm, Map<String,String>coordenadasMeuJogoAtual) {
		coordenadasMeuJogoAtual.clear();
		for (String key : cm.keySet()) {
			JButton b = cm.get(key);
			coordenadasMeuJogoAtual.put(b.getToolTipText(),b.getName());
		}
		//		for (Component c: components) {
		//			if(c instanceof JButton) {
		//				JButton button = ((JButton) c);
		//				if(button.getName().equalsIgnoreCase(CodigoButtonEnum.NUMERO.getDescricao()) || button.getName().equalsIgnoreCase(CodigoButtonEnum.VAZIO.getDescricao()) || 
		//						"ABCDEFGHIJKLMNOP".contains(button.getName())) {//Então eh um button que contem espaços das extremidades, com letras ou numeros!
		//					return;
		//				}else {
		//					coordenadasMeuJogoAtual.put(button.getToolTipText(),button.getName());//eh adicionada a cordenada!
		//				}
		//			}
		//		}
	}
	public static void montarMapaComputador(Map<String,String>coordenadasMeuJogoAtual) {
		coordenadasMeuJogoAtual.clear();
		popularMapVazioComputador(coordenadasMeuJogoAtual);
		int numEmbarcacoes  = 1;
		while (numEmbarcacoes<=2) {//Posicionar Encouracados!
			try {
				Random r = new Random();
				String point = (r.nextInt(15)+1)+","+(r.nextInt(15)+1);//Linha/coluna
				posicionarNaviosComputador(coordenadasMeuJogoAtual,point, TipoEmbarcacaoEnum.ENCOURACADO.getValor(), numEmbarcacoes);
				System.err.println("NUM EM:"+numEmbarcacoes);
				numEmbarcacoes ++;
				System.err.println("ENC "+(numEmbarcacoes-1)+" "+point);
			}catch(Exception e) {
				System.out.println(e.getMessage()+ e.getLocalizedMessage());
			}
		}
		numEmbarcacoes  = 1;
		while (numEmbarcacoes<2) {//Posicionar PORTAAV!
			try {
				Random r = new Random();
				String point = (r.nextInt(15)+1)+","+(r.nextInt(15)+1);//Linha/coluna
				posicionarNaviosComputador(coordenadasMeuJogoAtual,point, TipoEmbarcacaoEnum.PORTAAVIAO.getValor(), numEmbarcacoes);
				System.err.println("NUM EM:"+numEmbarcacoes);
				numEmbarcacoes ++;
				System.err.println("PA "+(numEmbarcacoes-1)+" "+point);
			}catch(Exception e) {
				System.out.println(e.getMessage()+ e.getLocalizedMessage());
			}
		}
		numEmbarcacoes  = 1;
		while (numEmbarcacoes<=5) {//Posicionar HidroAvioes!
			try {
				Random r = new Random();
				String point = (r.nextInt(15)+1)+","+(r.nextInt(15)+1);//Linha/coluna
				posicionarNaviosComputador(coordenadasMeuJogoAtual,point, TipoEmbarcacaoEnum.HIDROAVIAO.getValor(), numEmbarcacoes);
				System.err.println("NUM EM:"+numEmbarcacoes);
				numEmbarcacoes ++;
				System.err.println("HA "+(numEmbarcacoes-1)+" "+point);
			}catch(Exception e) {
				System.out.println(e.getMessage()+ e.getLocalizedMessage());
			}
		}
		numEmbarcacoes  = 1;
		while (numEmbarcacoes<=3) {//Posicionar cruzadores!
			try {
				Random r = new Random();
				String point = (r.nextInt(15)+1)+","+(r.nextInt(15)+1);//Linha/coluna
				posicionarNaviosComputador(coordenadasMeuJogoAtual,point, TipoEmbarcacaoEnum.CRUZADORES.getValor(), numEmbarcacoes);
				System.err.println("NUM EM:"+numEmbarcacoes);
				numEmbarcacoes ++;
				System.err.println("CRUZADOR "+(numEmbarcacoes-1)+""+point);
			}catch(Exception e) {
				System.out.println(e.getMessage()+ e.getLocalizedMessage());
			}
		}
		numEmbarcacoes  = 1;
		while (numEmbarcacoes<=4) {//Posicionar Submarinos!
			try {
				Random r = new Random();
				String point = (r.nextInt(15)+1)+","+(r.nextInt(15)+1);//Linha/coluna
				posicionarNaviosComputador(coordenadasMeuJogoAtual,point, TipoEmbarcacaoEnum.SUBMARINO.getValor(), numEmbarcacoes);
				System.err.println("NUM EM:"+numEmbarcacoes);
				numEmbarcacoes ++;
				System.err.println("SUB"+(numEmbarcacoes-1)+" "+point);
			}catch(Exception e) {
				System.out.println(e.getMessage()+ e.getLocalizedMessage());
			}
		}
		System.err.println("FIMMM");
	}
	public static void main(String[] args) {
		System.out.println("Ma");
		MontadorDeMapa.montarMapaComputador(new HashMap<String, String>());

	}
	public static void montarMapaAdversarioOnline(Map<String,String>coordenadasMeuJogoAtual) {

	}

	private static void posicionarNaviosComputador(Map<String,String>coordenadasmap,String coordenada, String navio, int numEmbarcacao) throws Exception {
		String s[] = coordenada.split(",");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		try {
			if(navio.equals(TipoEmbarcacaoEnum.ENCOURACADO.getValor())) {
				if(!coordenadasmap.get(x+","+y).equals(CodigoButtonEnum.POSICAO.getDescricao()) || !coordenadasmap.get(x+","+(y+1)).equals(CodigoButtonEnum.POSICAO.getDescricao())||
						!coordenadasmap.get(x+","+(y+2)).equals(CodigoButtonEnum.POSICAO.getDescricao())|| !coordenadasmap.get(x+","+(y+3)).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
					throw new Exception("Pelo tamnaho do navio nao foi possivel inserilo!");
				}else {
					
					coordenadasmap.replace((x+","+y),(TipoEmbarcacaoEnum.ENCOURACADO.getValor()+";"+(numEmbarcacao)+";P1"));

					coordenadasmap.replace(x+","+(y+1),TipoEmbarcacaoEnum.ENCOURACADO.getValor()+";"+(numEmbarcacao)+";P2");

					coordenadasmap.replace(x+","+(y+2),TipoEmbarcacaoEnum.ENCOURACADO.getValor()+";"+(numEmbarcacao)+";P3");

					coordenadasmap.replace(x+","+(y+3),TipoEmbarcacaoEnum.ENCOURACADO.getValor()+";"+(numEmbarcacao)+";P4");				
				}
			}
			else if(navio.equals(TipoEmbarcacaoEnum.PORTAAVIAO.getValor())) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).equals(CodigoButtonEnum.POSICAO.getDescricao()) || !coordenadasmap.get(x+","+(y+1)).equals(CodigoButtonEnum.POSICAO.getDescricao())||
						!coordenadasmap.get(x+","+(y+2)).equals(CodigoButtonEnum.POSICAO.getDescricao())|| !coordenadasmap.get(x+","+(y+3)).equals(CodigoButtonEnum.POSICAO.getDescricao()) || !coordenadasmap.get(x+","+(y+4)).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
					throw new Exception("Pelo tamanho do navio nao foi possivel inserilo!");
				}else {

					coordenadasmap.replace(x+","+y,TipoEmbarcacaoEnum.PORTAAVIAO.getValor()+";"+(numEmbarcacao)+";P1");

					coordenadasmap.replace(x+","+(y+1),TipoEmbarcacaoEnum.PORTAAVIAO.getValor()+";"+(numEmbarcacao)+";P2");

					coordenadasmap.replace(x+","+(y+2),TipoEmbarcacaoEnum.PORTAAVIAO.getValor()+";"+(numEmbarcacao)+";P3");

					coordenadasmap.replace(x+","+(y+3), TipoEmbarcacaoEnum.PORTAAVIAO.getValor()+";"+(numEmbarcacao)+";P4");

					coordenadasmap.replace(x+","+(y+4), TipoEmbarcacaoEnum.PORTAAVIAO.getValor()+";"+(numEmbarcacao)+";P5");
				}
			}
			else if(navio.equals(TipoEmbarcacaoEnum.CRUZADORES.getValor())) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).equals(CodigoButtonEnum.POSICAO.getDescricao()) || !coordenadasmap.get(x+","+(y+1)).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
					throw new Exception("Pelo tamanho do navio nao foi possivel inserilo!");
				}else {
					coordenadasmap.replace(x+","+y,TipoEmbarcacaoEnum.CRUZADORES.getValor()+";"+numEmbarcacao+";P1");

					coordenadasmap.replace(x+","+(y+1),TipoEmbarcacaoEnum.CRUZADORES.getValor()+";"+numEmbarcacao+";P2");
				}
			}
			else if(navio.equals(TipoEmbarcacaoEnum.HIDROAVIAO.getValor())) {
				if(!coordenadasmap.get(x+","+y).equals(CodigoButtonEnum.POSICAO.getDescricao()) || !coordenadasmap.get((x+1)+","+(y-1)).equals(CodigoButtonEnum.POSICAO.getDescricao()) ||  !coordenadasmap.get((x+1)+","+(y+1)).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
					throw new Exception("Pelo tamanho do navio nao foi possivel inserilo!");
				}else {
					coordenadasmap.replace(x+","+y,TipoEmbarcacaoEnum.HIDROAVIAO.getValor()+";"+numEmbarcacao+";P1");

					coordenadasmap.replace((x+1)+","+(y-1), TipoEmbarcacaoEnum.HIDROAVIAO.getValor()+";"+numEmbarcacao+";P2");

					coordenadasmap.replace((x+1)+","+(y+1),TipoEmbarcacaoEnum.HIDROAVIAO.getValor()+";"+numEmbarcacao+";P3");
				}
			}
			else if(navio.equals(TipoEmbarcacaoEnum.SUBMARINO.getValor())) {//se possivel transformar num enum esse nome.
				if(!coordenadasmap.get(x+","+y).equals(CodigoButtonEnum.POSICAO.getDescricao())) {
					throw new Exception("Pelo tamnaho do navio nao foi possivel inserilo!");
				}
				else {
					coordenadasmap.replace(x+","+y, TipoEmbarcacaoEnum.SUBMARINO.getValor()+";"+numEmbarcacao+";P1");
				}
			}
		}catch(Exception e1) {
			//e1.printStackTrace();
			throw new Exception("ESPACO OCUPADO!!!");
			//JOptionPane.showMessageDialog(null, "Nï¿½o eh possivel inserir navio!");
		}
	}
	private static void popularMapVazioComputador(Map<String,String>coordenadasMeuJogoAtual) {
		for(int i = 0; i < 17; i++)
			for(int j = 0; j < 17; j++){
				if(i == 0 || i==16) { //Se primeira ou ultima linha
					//não faz nada!
				}
				else {//Se nao for primeira ou ultima linha
					if(i==0 && j==0 || i==16 && j==16 || i==0 && j==16 || i==16 && j==0) {//Captura as extremidades.
						//extremidades
						//Nao faz nada!
					}
					else if(j==0 || j==16) {//Bordas Laterais

					}else {//Miolo do mapa
						coordenadasMeuJogoAtual.put(i + "," + j,CodigoButtonEnum.POSICAO.getDescricao());
					}
				}
		} 
	}
	public static void resetarTelaMapa() {
		
	}
}
