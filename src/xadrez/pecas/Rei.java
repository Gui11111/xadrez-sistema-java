package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;

public class Rei extends Pe�aXadrez {

	private PartidaXadrez partidaXadrez;
	// construtor
	public Rei(Tabuleiro tabuleiro, Cores cores, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cores);
		this.partidaXadrez = partidaXadrez;
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

	private boolean testTorreRoque(Posicao posicao) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posicao);
		return p != null && p instanceof Torre && p.getCores() == getCores() && p.getContagemMovimentos() == 0;
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

		// verificar se pode fazer a jogada Roque
		if(getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			// roque do lado do rei
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}

			// roque do lado da rainha
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null && getTabuleiro().pe�a(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		return mat;
	}
}
