package xadrez;

import jogoTabuleiro.Pe�a;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class Pe�aXadrez extends Pe�a{ // heran�a

	private Cores cores;

	public Pe�aXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	// tem somente o get porque nao a cor da pe�a nao pode mudar
	public Cores getCores() {
		return cores;
	}
	
	// testando se a cor da pe�a nessa posicao (adversaria) � diferente da cor da minha pe�a
	protected boolean existePe�aAdversaria(Posicao posicao) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posicao);
		return p != null && p.getCores() != cores;
	}
}
