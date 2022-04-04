package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

public class Torre extends Pe�aXadrez {

	// construtor
	public Torre(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "T";
	}

	// testando as posi��es livres da Torre
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// verifcar acima da pe�a
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		// verificar quando terminar o while, se existe pe�a adversaria na posicao
		if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar esquerda da pe�a
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		// verificar quando terminar o while, se existe pe�a adversaria na posicao
		if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar direita da pe�a
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		// verificar quando terminar o while, se existe pe�a adversaria na posicao
		if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar abaixo da pe�a
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		// verificar quando terminar o while, se existe pe�a adversaria na posicao
		if (getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
