package jogoTabuleiro;

public class Peça {

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
}
