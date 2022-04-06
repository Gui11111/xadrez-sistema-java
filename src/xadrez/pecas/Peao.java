package xadrez.pecas;

import java.util.Arrays;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

public class Peao extends Pe�aXadrez{

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
			if(getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p) && getTabuleiro().posi�aoExistente(p2) && !getTabuleiro().ExisteUmaPe�a(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if(getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			//tratando a regra do peao branco
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posi�aoExistente(p) && !getTabuleiro().ExisteUmaPe�a(p) && getTabuleiro().posi�aoExistente(p2) && !getTabuleiro().ExisteUmaPe�a(p2) && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//testar as duas casas horizontais do tabuleiro
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if(getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().posi�aoExistente(p) && existePe�aAdversaria(p)) {
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
