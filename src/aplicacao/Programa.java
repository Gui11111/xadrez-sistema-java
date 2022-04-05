package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<PeçaXadrez> captura = new ArrayList<>();
		
		//função responsável por imprimir as peças da partida 
		while (!partida.getCheckMate()) {
			try {
				UI.LimpaTela(); //chamando o método LimpaTela
				UI.printPartida(partida, captura); // user interface (UI) / imprimi o tabuleiro na tela 
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.LerPosicaoXadrez(sc); // lê a posicao de origem
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.LimpaTela();
				UI.printTabuleiro(partida.getPeça(), movimentosPossiveis); //responsavel por imprimir o tabuleiro com cor
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.LerPosicaoXadrez(sc);
				
				PeçaXadrez peçaCapturada = partida.MovimentoXadrez(origem, destino);
				
				if(peçaCapturada != null) {
					captura.add(peçaCapturada);
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
