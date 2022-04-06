package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PeçaXadrez;

public class Bispo extends PeçaXadrez{

	// construtor
		public Bispo(Tabuleiro tabuleiro, Cores cores) {
			super(tabuleiro, cores);
		}

		@Override
		public String toString() {
			return "B";
		}

		// testando as posições livres da Torre
		@Override
		public boolean[][] movimentosPossiveis() {
			boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

			Posicao p = new Posicao(0, 0);

			// verifcar noroeste da peça
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe peça adversaria na posicao
			if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar nordeste da peça
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe peça adversaria na posicao
			if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar sudeste da peça
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe peça adversaria na posicao
			if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// verifcar sudoeste da peça
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe peça adversaria na posicao
			if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			return mat;
		}
}
