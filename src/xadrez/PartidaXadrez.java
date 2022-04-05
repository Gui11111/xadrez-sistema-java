package xadrez;

import java.util.ArrayList;
import java.util.List;

import jogoTabuleiro.Peça;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private int turno;
	private Cores jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peça> peçasDoTabuleiro = new ArrayList<>();
	private List<Peça> capturaPeças = new ArrayList<>();

	// construtor padrão
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8); // dimensão do tabuleiro
		turno = 1;
		jogadorAtual = Cores.WHITE;
		ConfiguracaoInicial();
	}
	
	public int getTurno() {
		return turno;
	}

	public Cores getJogadorAtual() {
		return jogadorAtual;
	}

	public PeçaXadrez[][] getPeça() { // método que retorna uma matriz correspondente a PartidaXadrez
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		// percorrer a matriz de peças do tabuleiro
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}
	
	//método que serve para imprimir as posições possiveis a a partir de uma posicao de origem na classe Programa
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosition();
		validaPosicaoOrigem(posicao);
		return tabuleiro.peça(posicao).movimentosPossiveis();//retornar os movimentos possiveis dessa peça nessa posicao
	}
	
	
	public PeçaXadrez MovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosition(); // converter para posicao da matriz
		Posicao destino = posicaoDestino.toPosition();
		validaPosicaoOrigem(origem);//validar se nessa posicao de origem existe uma peça 
		validaPosicaoDestino(origem, destino);
		Peça capturaPeça = fazerMover(origem, destino);
		proximoTurno();
		return (PeçaXadrez) capturaPeça;
	}
	
	private Peça fazerMover(Posicao origem, Posicao destino) {
		Peça p = tabuleiro.removePeça(origem);
		Peça peçaCapturada = tabuleiro.removePeça(destino);
		tabuleiro.PosicaoPeça(p, destino);
		
		if(peçaCapturada != null) {
			peçasDoTabuleiro.remove(peçaCapturada);
			capturaPeças.add(peçaCapturada);
		}
		return peçaCapturada;
	}
	
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ExisteUmaPeça(posicao)) { // se nao existir uma peça nessa posicao eu vou dar uma exceção
			throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
		}
		if(jogadorAtual != ((PeçaXadrez)tabuleiro.peça(posicao)).getCores()) {
			throw new ExcecaoXadrez("A peca escolhida nao e sua");
		}
		if(!tabuleiro.peça(posicao).ExisteAlgumMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if(! tabuleiro.peça(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	// método que troca o turno
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}
	
	// método que recebe as coordenadas do xadrez
	private void NovaPosicaoPeça(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.PosicaoPeça(peça, new PosicaoXadrez(coluna, linha).toPosition()); // recebe uma linha e coluna só que converte para posicao de matriz
		peçasDoTabuleiro.add(peça);
	}

	private void ConfiguracaoInicial() {
		NovaPosicaoPeça('c', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('c', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('d', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('e', 2, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('e', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('d', 1, new Rei(tabuleiro, Cores.WHITE));

		NovaPosicaoPeça('c', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('c', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('d', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('e', 7, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('e', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('d', 8, new Rei(tabuleiro, Cores.BLACK));
	}
}
