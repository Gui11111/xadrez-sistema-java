package xadrez;

import jogoTabuleiro.ExcecaoTabuleiro;
import jogoTabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecaoTabuleiro("Erro ist�nciando ExcecaoPosicao. Valores v�lidos s�o de a1 at� h8. ");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	// somente m�todo get para n�o alterar os valores da coluna e linha
	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	// m�todo que dada uma posicao de xadrez que converte para uma posicao da matriz 
	protected Posicao toPosition() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	// m�todo que dada uma posicao na matriz que converte para uma posicao de xadrez
	protected static PosicaoXadrez fromPosition(Posicao posicao) {
		return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; // concatena��o de String
	}
}
