package aplicacao;

import xadrez.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partida = new PartidaXadrez();
		
		//função responsável por imprimir as peças da partida 
		UI.printTabuleiro(partida.getPeça()); // user interface (UI)
	}

}
