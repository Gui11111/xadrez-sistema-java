package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PešaXadrez;

public class Bispo extends PešaXadrez{

	// construtor
		public Bispo(Tabuleiro tabuleiro, Cores cores) {
			super(tabuleiro, cores);
		}

		@Override
		public String toString() {
			return "B";
		}

		// testando as posiš§es livres da Torre
		@Override
		public boolean[][] movimentosPossiveis() {
			boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

			Posicao p = new Posicao(0, 0);

			// verifcar noroeste da peša
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe peša adversaria na posicao
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar nordeste da peša
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() - 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe peša adversaria na posicao
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			
			
			// verifcar sudeste da peša
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() + 1);
			}
			// verificar quando terminar o while, se existe peša adversaria na posicao
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// verifcar sudoeste da peša
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValor(p.getLinha() + 1, p.getColuna() - 1);
			}
			// verificar quando terminar o while, se existe peša adversaria na posicao
			if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			return mat;
		}
}
