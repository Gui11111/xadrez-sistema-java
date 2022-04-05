package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<Pe�aXadrez> captura = new ArrayList<>();
		
		//fun��o respons�vel por imprimir as pe�as da partida 
		while (!partida.getCheckMate()) {
			try {
				UI.LimpaTela(); //chamando o m�todo LimpaTela
				UI.printPartida(partida, captura); // user interface (UI) / imprimi o tabuleiro na tela 
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
				
				if(pe�aCapturada != null) {
					captura.add(pe�aCapturada);
				}
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
		UI.LimpaTela();
		UI.printPartida(partida, captura);
	}
}
