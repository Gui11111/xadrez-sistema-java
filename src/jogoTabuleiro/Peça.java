package jogoTabuleiro;

//classe abstrata 
public abstract class Pe�a {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	// Construtor - classe Pe�a associa ao Tabuleiro
	public Pe�a(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null; 
	}

	// metodos get somente, pois o tabuleiro nao pode ser alterado
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	//opera��o abstrata 
	public abstract boolean[][] movimentosPossiveis();
	
	/*opera��o movimentosPossiveis que recebe uma posic�o e vai retornar verdadeiro 
	ou falso caso tenha como da pe�a ser movida*/
	public boolean movimentoPossivel(Posicao posicao) { 
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	// m�todo responder por informar se existe pelo menos um movimento possivel para essa pe�a
	public boolean ExisteAlgumMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
