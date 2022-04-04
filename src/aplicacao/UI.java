package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cores;
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
			throw new InputMismatchException("Erro lendo a posição de xadrez. Valores válidos são de a1 até h8.");
		}
	}
	
	// método responsável por criar as linhas do tabuleiro
		public static void printTabuleiro(PeçaXadrez[][] peças) {
			for (int i=0; i<peças.length; i++) {
				System.out.print((8 - i) + " ");
				for (int j=0; j<peças.length;j++) {
					PrintPeças(peças[i][j]); // imprimir a peça
				}
				System.out.println();
			}
			System.out.println("  a b c d e f g h");
		}
		
		//método auxiliar para imprimir somente uma peça
		private static void PrintPeças(PeçaXadrez peça) { 
			if (peça == null) { 
				System.out.print("-");
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
}
