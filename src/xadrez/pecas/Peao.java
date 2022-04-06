package xadrez.pecas;

import java.util.Arrays;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PešaXadrez;

public class Peao extends PešaXadrez{

	public Peao(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		//tratando a regra do peao branco
		if(getCores() == Cores.WHITE) {
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p) && getTabuleiro().posišaoExistente(p2) && !getTabuleiro().ExisteUmaPeša(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			//tratando a regra do peao branco
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posišaoExistente(p) && !getTabuleiro().ExisteUmaPeša(p) && getTabuleiro().posišaoExistente(p2) && !getTabuleiro().ExisteUmaPeša(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().posišaoExistente(p) && existePešaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
