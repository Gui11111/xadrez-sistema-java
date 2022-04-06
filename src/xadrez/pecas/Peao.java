package xadrez.pecas;

import java.util.Arrays;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;

public class Peao extends Pe�aXadrez {

	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cores cores, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cores);
		this.partidaXadrez = partidaXadrez;

	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// tratando a regra do peao branco
		if (getCores() == Cores.WHITE) {
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)
					&& getTabuleiro().posi�aoExistente(p2) && !getTabuleiro().ExisteUmaPe�a(p2)
					&& getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// movimento especial en Passant white
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posi�aoExistente(esquerda) && existePe�aAdversaria(esquerda)
						&& getTabuleiro().pe�a(esquerda) == partidaXadrez.getEnPassant()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posi�aoExistente(direita) && existePe�aAdversaria(direita)
						&& getTabuleiro().pe�a(direita) == partidaXadrez.getEnPassant()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}

		} else {
			// tratando a regra do peao branco
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)
					&& getTabuleiro().posi�aoExistente(p2) && !getTabuleiro().ExisteUmaPe�a(p2)
					&& getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// movimento especial en Passant black
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posi�aoExistente(esquerda) && existePe�aAdversaria(esquerda)
						&& getTabuleiro().pe�a(esquerda) == partidaXadrez.getEnPassant()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posi�aoExistente(direita) && existePe�aAdversaria(direita)
						&& getTabuleiro().pe�a(direita) == partidaXadrez.getEnPassant()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
