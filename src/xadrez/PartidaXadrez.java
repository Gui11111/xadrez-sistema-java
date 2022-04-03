package xadrez;

import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private Tabuleiro tabuleiro;
	
	//construtor padrão
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8); // dimensão do tabuleiro
	}
	
	public PeçaXadrez[][] getPeças() { // método que retorna uma matriz correspondente a PartidaXadrez
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()] [tabuleiro.getColunas()];
		
		// percorrer a matriz de peças do tabuleiro
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}
}
