package xadrez;

import jogoTabuleiro.Tabuleiro;


public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private Tabuleiro tabuleiro;
	
	//construtor padrão
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8); // dimensão do tabuleiro
		ConfiguracaoInicial();
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
	private void ConfiguracaoInicial() {
		/*NovaPosicaoPeça('c', 1, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPeça('c', 2, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPeça('d', 2,new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPeça('e', 2, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPeça('e', 1, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPeça('d', 1,new Rei(tabuleiro, Cores.BRANCO));
		
		NovaPosicaoPeça('c', 7, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPeça('c', 8, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPeça('d', 7,new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPeça('e', 7, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPeça('e', 8, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPeça('d', 8,new Rei(tabuleiro, Cores.PRETO));*/
	}
}
