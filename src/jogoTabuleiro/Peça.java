package jogoTabuleiro;

public class Pe�a {

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
}
