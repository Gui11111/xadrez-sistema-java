package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

public class Bispo extends Pe�aXadrez{

	// construtor
		public Bispo(Tabuleiro tabuleiro, Cores cores) {
			super(tabuleiro, cores);
		}

		@Override
		public String toString() {
			return "B";
		}

		// testando as posi��es livres da Torre
		@Override
		public boolean[][] movimentosPossiveis() {
			boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

			Posicao p = new Posicao(0, 0);

			// verifcar noroeste da pe�a
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe pe�a adversaria na posicao
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar nordeste da pe�a
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe pe�a adversaria na posicao
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar sudeste da pe�a
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe pe�a adversaria na posicao
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// verifcar sudoeste da pe�a
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe pe�a adversaria na posicao
			if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			return mat;
		}
}
