package xadrez;

import jogoTabuleiro.Peça;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PeçaXadrez extends Peça{ // herança

	private Cores cores;

	public PeçaXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	// tem somente o get porque nao a cor da peça nao pode mudar
	public Cores getCores() {
		return cores;
	}
	
	// testando se a cor da peça nessa posicao (adversaria) é diferente da cor da minha peça
	protected boolean existePeçaAdversaria(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p != null && p.getCores() != cores;
	}
}
