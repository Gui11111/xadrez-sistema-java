package aplicacao;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		//fun��o respons�vel por imprimir as pe�as da partida 
		while (true) {
			UI.printTabuleiro(partida.getPe�a()); // user interface (UI) / imprimi o tabuleiro na tela 
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.LerPosicaoXadrez(sc); // l� a posicao de origem
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.LerPosicaoXadrez(sc);
			
			Pe�aXadrez pe�aCapturada = partida.MovimentoXadrez(origem, destino);
		}
		
	}

}
