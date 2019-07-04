package br.edu.psd.batalhanaval.Util;

import br.edu.psd.batalhanaval.model.Jogador;
import br.edu.psd.batalhanaval.model.socket.Cliente;

public class SocketUtil {
	private static Cliente clienteCorrente;
	private static Jogador computador;
	public static boolean offiline = false;
	public static String converterNumeroEmLetra(int num) {
		String letra = "";
		if (num == 1)
			return "A";
		if (num == 2)
			return "B";
		if (num == 3) 
			return "C";
		if (num == 4) 
			return "D";
		if (num == 5)
			return "E";
		if (num == 6) 
			return "F";
		if (num == 7)
			return "G";
		if (num == 8) 
			return "H";
		if (num == 9) 
			return "I";
		if (num == 10) 
			return "J";
		if (num == 11)
			return "L";
		if (num == 12)
			return "M";
		if (num == 13) 
			return "N";
		if (num == 14) 
			return "O";
		if (num == 15)
			return "P";
		return letra;
	}
	public static String converteLetraemNumero(String letra) {
		String l = "";
		if (letra.equalsIgnoreCase("A"))
			return "1";
		if (letra.equalsIgnoreCase("B"))
			return "2";
		if (letra.equalsIgnoreCase("C")) 
			return "3";
		if (letra.equalsIgnoreCase("D")) 
			return "4";
		if (letra.equalsIgnoreCase("E"))
			return "5";
		if (letra.equalsIgnoreCase("F")) 
			return "6";
		if (letra.equalsIgnoreCase("G"))
			return "7";
		if (letra.equalsIgnoreCase("H")) 
			return "8";
		if (letra.equalsIgnoreCase("I")) 
			return "9";
		if (letra.equalsIgnoreCase("J")) 
			return "10";
		if (letra.equalsIgnoreCase("L"))
			return "11";
		if (letra.equalsIgnoreCase("M"))
			return "12";
		if (letra.equalsIgnoreCase("N")) 
			return "13";
		if (letra.equalsIgnoreCase("O")) 
			return "14";
		if (letra.equalsIgnoreCase("P"))
			return "15";
		return l;
	}
	public static Cliente getClienteCorrente() {
		return clienteCorrente;
	}
	public static void setClienteCorrente(Cliente clienteCorrente) {
		SocketUtil.clienteCorrente = clienteCorrente;
	}
	public static Jogador getComputador() {
		return computador;
	}
	public static void setComputador(Jogador computador) {
		SocketUtil.computador = computador;
	}
	
}
