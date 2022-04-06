package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

public class Cavalo extends Pe�aXadrez{

	// construtor
	public Cavalo(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "C";
	}

	// m�todo que vai falar se o rei pode se mover para uma data posi��o
	private boolean podeMover(Posicao posicao) {
		Pe�aXadrez p = (Pe�aXadrez) getTabuleiro().pe�a(posicao);
		return p == null || p.getCores() != getCores();
	}

	// testando as posi��es livres do Rei
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2); 
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1); 
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1); 
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2); 
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2); 
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1); // posicao nordeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1); // posicao sudoeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2); // posicao sudeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
