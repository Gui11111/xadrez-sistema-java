package xadrez;

import jogoTabuleiro.ExcecaoTabuleiro;

public class ExcecaoXadrez extends ExcecaoTabuleiro{

	private static final long serialVersionUID = 1L;

	// construtor que recebe o string
	public ExcecaoXadrez(String msg) {
		super(msg);
	}
}
