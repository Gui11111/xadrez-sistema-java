package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

public class Rei extends Pe�aXadrez {

	// construtor
	public Rei(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "R";
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

		// verifcar acima da pe�a
		p.setValor(posicao.getLinha() - 1, posicao.getColuna()); // posicao acima do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar abaixo da pe�a
		p.setValor(posicao.getLinha() + 1, posicao.getColuna()); // posicao abaixo do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar esquerda da pe�a
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1); // posicao esquerda do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar direita da pe�a
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1); // posicao direita do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar noroeste da pe�a
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1); // posicao noroeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar nordeste da pe�a
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1); // posicao nordeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar sudoeste da pe�a
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1); // posicao sudoeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar sudeste da pe�a
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1); // posicao sudeste do rei
		if (getTabuleiro().posi�aoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
