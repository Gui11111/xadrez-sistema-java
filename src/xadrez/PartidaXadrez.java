package xadrez;

import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private Tabuleiro tabuleiro;
	
	//construtor padr�o
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8); // dimens�o do tabuleiro
	}
	
	public Pe�aXadrez[][] getPe�as() { // m�todo que retorna uma matriz correspondente a PartidaXadrez
		Pe�aXadrez[][] mat = new Pe�aXadrez[tabuleiro.getLinhas()] [tabuleiro.getColunas()];
		
		// percorrer a matriz de pe�as do tabuleiro
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i, j);
			}
		}
		return mat;
	}
}
