package jogoTabuleiro;

//classe abstrata 
public abstract class Peça {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	// Construtor - classe Peça associa ao Tabuleiro
	public Peça(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null; 
	}

	// metodos get somente, pois o tabuleiro nao pode ser alterado
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	//operação abstrata 
	public abstract boolean[][] movimentosPossiveis();
	
	/*operação movimentosPossiveis que recebe uma posicão e vai retornar verdadeiro 
	ou falso caso tenha como da peça ser movida*/
	public boolean movimentoPossivel(Posicao posicao) { 
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	// método responder por informar se existe pelo menos um movimento possivel para essa peça
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
