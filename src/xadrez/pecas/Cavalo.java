package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PeçaXadrez;

public class Cavalo extends PeçaXadrez{

	// construtor
	public Cavalo(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	@Override
	public String toString() {
		return "C";
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

		
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2); 
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1); 
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1); 
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2); 
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2); 
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1); // posicao nordeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1); // posicao sudoeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2); // posicao sudeste do rei
		if (getTabuleiro().posiçaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
