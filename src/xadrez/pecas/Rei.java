package xadrez.pecas;

import jogoTabuleiro.Posicao;
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

	// método que vai falar se o rei pode se mover para uma data posição
	private boolean podeMover(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(posicao);
		return p == null || p.getCores() != getCores();
	}

	// testando as posições livres do Rei
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// verifcar acima da peça
		p.setValor(posicao.getLinha() - 1, posicao.getColuna()); // posicao acima do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar abaixo da peça
		p.setValor(posicao.getLinha() + 1, posicao.getColuna()); // posicao abaixo do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar esquerda da peça
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1); // posicao esquerda do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar direita da peça
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1); // posicao direita do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar noroeste da peça
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1); // posicao noroeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar nordeste da peça
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1); // posicao nordeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar sudoeste da peça
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1); // posicao sudoeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verifcar sudeste da peça
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1); // posicao sudeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
