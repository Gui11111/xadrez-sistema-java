package xadrez;

import jogoTabuleiro.ExcecaoTabuleiro;
import jogoTabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecaoTabuleiro("Erro istânciando ExcecaoPosicao. Valores válidos são de a1 até h8. ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	// somente método get para não alterar os valores da coluna e linha
	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	// método que dada uma posicao de xadrez que converte para uma posicao da matriz 
	protected Posicao toPosition() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	// método que dada uma posicao na matriz que converte para uma posicao de xadrez
	protected static PosicaoXadrez fromPosition(Posicao posicao) {
		return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; // concatenação de String
	}
}
