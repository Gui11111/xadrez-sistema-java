package aplicacao;

import xadrez.Pe�aXadrez;

public class UI {
	
	// m�todo respons�vel por criar as linhas do tabuleiro
		public static void printTabuleiro(Pe�aXadrez[][] pe�as) {
			for (int i=0; i<pe�as.length; i++) {
				System.out.print((8 - i) + " ");
				for (int j=0; j<pe�as.length;j++) {
					PrintPe�as(pe�as[i][j]); // imprimir a pe�a
				}
				System.out.println();
			}
			System.out.println("  a b c d e f g h");
		}
		
		//m�todo auxiliar para imprimir somente uma pe�a
		private static void PrintPe�as(Pe�aXadrez pe�a) { 
			if (pe�a == null) { 
				System.out.print("-");
			}
			else {
				/*if(pe�a.getCores() == Cores.BRANCO) {
					System.out.print(ANSI_WHITE + pe�a + ANSI_RESET);
				}
				else {
					System.out.println(ANSI_YELLOW + pe�a + ANSI_RESET);
				}*/
				System.out.println(pe�a);
			}
			System.out.print(" "); 
		}
}
