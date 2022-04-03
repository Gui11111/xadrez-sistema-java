package aplicacao;

import xadrez.PeçaXadrez;

public class UI {
	
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
				/*if(peça.getCores() == Cores.BRANCO) {
					System.out.print(ANSI_WHITE + peça + ANSI_RESET);
				}
				else {
					System.out.println(ANSI_YELLOW + peça + ANSI_RESET);
				}*/
				System.out.println(peça);
			}
			System.out.print(" "); 
		}
}
