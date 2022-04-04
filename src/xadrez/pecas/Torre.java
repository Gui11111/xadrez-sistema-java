package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PeçaXadrez;

public class Torre extends PeçaXadrez {

	// construtor
	public Torre(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "T";
	}

	// testando as posições livres da Torre
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// verifcar acima da peça
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		// verificar quando terminar o while, se existe peça adversaria na posicao
		if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar esquerda da peça
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		// verificar quando terminar o while, se existe peça adversaria na posicao
		if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar direita da peça
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		// verificar quando terminar o while, se existe peça adversaria na posicao
		if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar abaixo da peça
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posiçaoExistente(p) && !getTabuleiro().ExisteUmaPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		// verificar quando terminar o while, se existe peça adversaria na posicao
		if (getTabuleiro().posiçaoExistente(p) && existePeçaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
