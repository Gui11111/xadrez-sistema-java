package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoTabuleiro.Peça;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private int turno;
	private Cores jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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
		
		//testar se esse movimento colocou o próprio jogador em check;
		if(testaCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturaPeça);
			throw new ExcecaoXadrez("Voce nao pode se colocar em check");
		}
		
		//testar se o oponente ficou em check
		check = (testaCheck(oponente(jogadorAtual))) ? true : false;
		
		// se a jogada deixou o oponente em checkMate o jogo vai ter que aprar
		if(testaCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (PeçaXadrez) capturaPeça;
	}
	
	// método para fazer movimento
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
	
	
	// método para desfazer movimento caso o usuário entre em xeque
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peça capturapeça) {
		Peça p = tabuleiro.removePeça(destino); // tira a peça do destino 
		tabuleiro.PosicaoPeça(p, origem); //devolve a peça do destino para a posicao de origem
		
		if(capturapeça != null) {
			tabuleiro.PosicaoPeça(capturapeça, destino);
			capturaPeças.remove(capturapeça);
			peçasDoTabuleiro.add(capturapeça);
		}
		
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
	
	//método devolve o oponente de uma cor
	private Cores oponente(Cores cor) {
		return (cor == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}
	
	
	// método para localizar o rei de uma determinada cor
	private PeçaXadrez rei(Cores cor) {
		//filtragem da lista
		List<Peça> list = peçasDoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCores() == cor).collect(Collectors.toList());
		for(Peça p : list) {
			if(p instanceof Rei) {
				return (PeçaXadrez)p;
			}
		}
		// se o conpilador nao encontrar lança uma exceçao
		throw new IllegalStateException("Nao existe o rei da " + cor + " no tabuleiro");
	}
	
	
	//método para testar check
	private boolean testaCheck(Cores cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosition(); // pega a posicao do rei na posicao de matriz
		List<Peça> oponentePeças = peçasDoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCores() == oponente(cor)).collect(Collectors.toList());
		for(Peça p : oponentePeças) {
			boolean[][] mat = p.movimentosPossiveis(); //matriz de movimentos possiveis dessa peça adversária p
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	//método para testar check mate
	private boolean testaCheckMate(Cores cor) {
		if(!testaCheck(cor)) {
			return false;
		}
		List<Peça> list = peçasDoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCores() == cor).collect(Collectors.toList());
		for(Peça p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao origem = ((PeçaXadrez)p).getPosicaoXadrez().toPosition();
						Posicao destino = new Posicao(i, j);
						Peça peçaCapturada = fazerMover(origem, destino);
						boolean testCheck = testaCheck(cor);
						desfazerMovimento(origem, destino, peçaCapturada);
						
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	// método que recebe as coordenadas do xadrez
	private void NovaPosicaoPeça(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.PosicaoPeça(peça, new PosicaoXadrez(coluna, linha).toPosition()); // recebe uma linha e coluna só que converte para posicao de matriz
		peçasDoTabuleiro.add(peça);
	}

	private void ConfiguracaoInicial() {
		NovaPosicaoPeça('h', 7, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('d', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPeça('e', 1, new Rei(tabuleiro, Cores.WHITE));

		NovaPosicaoPeça('b', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPeça('a', 8, new Rei(tabuleiro, Cores.BLACK));
	}
}
