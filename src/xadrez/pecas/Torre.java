package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PešaXadrez;

public class Torre extends PešaXadrez {

	// construtor
	public Torre(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "T";
	}

	// testando as posiš§es livres da Torre
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// verifcar acima da peša
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		// verificar quando terminar o while, se existe peša adversaria na posicao
		if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar esquerda da peša
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		// verificar quando terminar o while, se existe peša adversaria na posicao
		if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar direita da peša
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		// verificar quando terminar o while, se existe peša adversaria na posicao
		if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		
		// verifcar abaixo da peša
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		// verificar quando terminar o while, se existe peša adversaria na posicao
		if (getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
}
