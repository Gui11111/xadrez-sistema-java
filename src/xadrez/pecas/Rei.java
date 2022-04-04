package xadrez.pecas;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {

	// construtor
	public Rei(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
