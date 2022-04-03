package xadrez;

import jogoTabuleiro.Peça;
import jogoTabuleiro.Tabuleiro;

public class PeçaXadrez extends Peça{ // herança

	private Cores cores;

	public PeçaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	// tem somente o get porque nao a cor da peça nao pode mudar
	public Cores getCores() {
		return cores;
	}
}
