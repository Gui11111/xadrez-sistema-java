package aplicacao;

import xadrez.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partida = new PartidaXadrez();
		
		//fun��o respons�vel por imprimir as pe�as da partida 
		UI.printTabuleiro(partida.getPe�a()); // user interface (UI)
	}

}
