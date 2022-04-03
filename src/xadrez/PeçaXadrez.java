package xadrez;

import jogoTabuleiro.Pe�a;
import jogoTabuleiro.Tabuleiro;

public class Pe�aXadrez extends Pe�a{ // heran�a

	private Cores cores;

	public Pe�aXadrez(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores = cores;
	}

	// tem somente o get porque nao a cor da pe�a nao pode mudar
	public Cores getCores() {
		return cores;
	}
}
