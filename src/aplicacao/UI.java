package aplicacao;

import java.awt.Color;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//método para limpar a tela após rodar o programa
	public static void LimpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	//metodo pra ler uma posicao do usuário
	public static PosicaoXadrez LerPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1)); //recorta o string na posicao 1 e converte o resultado para inteiro
		return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo a posicao de xadrez. Valores validos sao de a1 ate h8.");
		}
	}
	public static void printPartida(PartidaXadrez partidaXadrez, List<PeçaXadrez> captura) {
		printTabuleiro(partidaXadrez.getPeça());
		System.out.println();
		printCapturaPecas(captura);
		System.out.println();
		System.out.println("Turno: " + partidaXadrez.getTurno());
		if(!partidaXadrez.getCheckMate()) {
			System.out.println("Esperando o jogador jogar: " + partidaXadrez.getJogadorAtual());
			if(partidaXadrez.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!!");
			System.out.println("Vencedor: " + partidaXadrez.getJogadorAtual());
		}
	}
	
	
	// método responsável por criar as linhas do tabuleiro
		public static void printTabuleiro(PeçaXadrez[][] peças) {
			for (int i=0; i<peças.length; i++) {
				System.out.print((8 - i) + " ");
				for (int j=0; j<peças.length; j++) {
					PrintPeças(peças[i][j], false); // imprimir a peça
				}
				System.out.println();
			}
			System.out.println("  a b c d e f g h");
		}
		
		
		public static void printTabuleiro(PeçaXadrez[][] peças, boolean[][] movimentosPossiveis) {
			for (int i=0; i<peças.length; i++) {
				System.out.print((8 - i) + " ");
				for (int j=0; j<peças.length;j++) {
					PrintPeças(peças[i][j], movimentosPossiveis[i][j]); // imprimir a peça e pinta o fundo colorido dependendo dessa variavel
				}
				System.out.println();
			}
			System.out.println("  a b c d e f g h");
		}
		
		
		//método auxiliar para imprimir somente uma peça
		private static void PrintPeças(PeçaXadrez peça, boolean background) {
			if(background) {
				System.out.print(ANSI_BLUE_BACKGROUND);//mudar a cor do fundo da tela 
			}
			if (peça == null) { 
				System.out.print("-" + ANSI_RESET);
			}
			else {
				if(peça.getCores() == Cores.WHITE) {
					System.out.print(ANSI_WHITE + peça + ANSI_RESET);
				}
				else {
					System.out.print(ANSI_YELLOW + peça + ANSI_RESET);
				}
			}
			System.out.print(" "); 
		}
		
		private static void printCapturaPecas(List<PeçaXadrez> captura) {
			List<PeçaXadrez> white = captura.stream().filter(x -> x.getCores() == Cores.WHITE).collect(Collectors.toList());//operação básica para filtrar a lista
			List<PeçaXadrez> black = captura.stream().filter(x -> x.getCores() == Cores.BLACK).collect(Collectors.toList());//operação básica para filtrar a lista
			
			//lógica para fazer aparecer essas listas
			System.out.println("Capturando pecas: ");
			System.out.print("Pecas Brancas: ");
			System.out.print(ANSI_WHITE);
			System.out.println(Arrays.toString(white.toArray()));
			System.out.print(ANSI_RESET);
	
			System.out.print("Pecas Pretas: ");
			System.out.print(ANSI_YELLOW);
			System.out.println(Arrays.toString(black.toArray()));
			System.out.print(ANSI_RESET);
		}	
}
