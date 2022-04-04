package xadrez;

import jogoTabuleiro.Pe�a;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private Tabuleiro tabuleiro;

	// construtor padr�o
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8); // dimens�o do tabuleiro
		ConfiguracaoInicial();
	}

	public Pe�aXadrez[][] getPe�a() { // m�todo que retorna uma matriz correspondente a PartidaXadrez
		Pe�aXadrez[][] mat = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		// percorrer a matriz de pe�as do tabuleiro
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i, j);
			}
		}
		return mat;
	}
	
	public Pe�aXadrez MovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosition(); // converter para posicao da matriz
		Posicao destino = posicaoDestino.toPosition();
		validaPosicaoOrigem(origem);//validar se nessa posicao de origem existe uma pe�a 
		validaPosicaoDestino(origem, destino);
		Pe�a capturaPe�a = fazerMover(origem, destino);
		return (Pe�aXadrez) capturaPe�a;
	}
	
	private Pe�a fazerMover(Posicao origem, Posicao destino) {
		Pe�a p = tabuleiro.removePe�a(origem);
		Pe�a pe�aCapturada = tabuleiro.removePe�a(destino);
		tabuleiro.PosicaoPe�a(p, destino);
		return pe�aCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ExisteUmaPe�a(posicao)) { // se nao existir uma pe�a nessa posicao eu vou dar uma exce��o
			throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
		}
		if(!tabuleiro.pe�a(posicao).ExisteAlgumMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if(! tabuleiro.pe�a(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	
	// m�todo que recebe as coordenadas do xadrez
	private void NovaPosicaoPe�a(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.PosicaoPe�a(pe�a, new PosicaoXadrez(coluna, linha).toPosition()); // recebe uma linha e coluna s� que
																					// converte para posicao de matriz
	}

	private void ConfiguracaoInicial() {
		NovaPosicaoPe�a('c', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('c', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('d', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('e', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('e', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('d', 1, new Rei(tabuleiro, Cores.WHITE));

		NovaPosicaoPe�a('c', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('c', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('d', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('e', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('e', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('d', 8, new Rei(tabuleiro, Cores.BLACK));
	}
}
