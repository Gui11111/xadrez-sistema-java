package xadrez;

import jogoTabuleiro.Tabuleiro;


public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private Tabuleiro tabuleiro;
	
	//construtor padr�o
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8); // dimens�o do tabuleiro
		ConfiguracaoInicial();
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
	private void ConfiguracaoInicial() {
		/*NovaPosicaoPe�a('c', 1, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPe�a('c', 2, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPe�a('d', 2,new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPe�a('e', 2, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPe�a('e', 1, new Torre(tabuleiro, Cores.BRANCO));
		NovaPosicaoPe�a('d', 1,new Rei(tabuleiro, Cores.BRANCO));
		
		NovaPosicaoPe�a('c', 7, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPe�a('c', 8, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPe�a('d', 7,new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPe�a('e', 7, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPe�a('e', 8, new Torre(tabuleiro, Cores.PRETO));
		NovaPosicaoPe�a('d', 8,new Rei(tabuleiro, Cores.PRETO));*/
	}
}
