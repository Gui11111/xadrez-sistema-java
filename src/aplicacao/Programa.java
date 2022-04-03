package aplicacao;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		//função responsável por imprimir as peças da partida 
		while (true) {
			UI.printTabuleiro(partida.getPeça()); // user interface (UI) / imprimi o tabuleiro na tela 
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.LerPosicaoXadrez(sc); // lê a posicao de origem
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.LerPosicaoXadrez(sc);
			
			PeçaXadrez peçaCapturada = partida.MovimentoXadrez(origem, destino);
		}
		
	}

}
