package xadrez.pecas;

import java.util.Arrays;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PartidaXadrez;
import xadrez.PešaXadrez;

public class Peao extends PešaXadrez {

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
			if (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)
					&& getTabuleiro().posišaoExistente(p2) && !getTabuleiro().ExisteUmaPeša(p2)
					&& getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// movimento especial en Passant white
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posišaoExistente(esquerda) && existePešaAdversaria(esquerda)
						&& getTabuleiro().peša(esquerda) == partidaXadrez.getEnPassant()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posišaoExistente(direita) && existePešaAdversaria(direita)
						&& getTabuleiro().peša(direita) == partidaXadrez.getEnPassant()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}

		} else {
			// tratando a regra do peao branco
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)
					&& getTabuleiro().posišaoExistente(p2) && !getTabuleiro().ExisteUmaPeša(p2)
					&& getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			// testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// movimento especial en Passant black
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posišaoExistente(esquerda) && existePešaAdversaria(esquerda)
						&& getTabuleiro().peša(esquerda) == partidaXadrez.getEnPassant()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}

				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posišaoExistente(direita) && existePešaAdversaria(direita)
						&& getTabuleiro().peša(direita) == partidaXadrez.getEnPassant()) {
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
