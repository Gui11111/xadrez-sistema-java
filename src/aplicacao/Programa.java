package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		//fun��o respons�vel por imprimir as pe�as da partida 
		while (true) {
			try {
				UI.LimpaTela(); //chamando o m�todo LimpaTela
				UI.printPartida(partida); // user interface (UI) / imprimi o tabuleiro na tela 
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.LerPosicaoXadrez(sc); // l� a posicao de origem
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.LimpaTela();
				UI.printTabuleiro(partida.getPe�a(), movimentosPossiveis); //responsavel por imprimir o tabuleiro com cor
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.LerPosicaoXadrez(sc);
				
				Pe�aXadrez pe�aCapturada = partida.MovimentoXadrez(origem, destino);
				}
			catch (ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
	}

}
