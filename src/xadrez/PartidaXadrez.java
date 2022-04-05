package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoTabuleiro.Pe�a;
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
	
	private List<Pe�a> pe�asDoTabuleiro = new ArrayList<>();
	private List<Pe�a> capturaPe�as = new ArrayList<>();

	// construtor padr�o
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8); // dimens�o do tabuleiro
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
	
	//m�todo que serve para imprimir as posi��es possiveis a a partir de uma posicao de origem na classe Programa
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosition();
		validaPosicaoOrigem(posicao);
		return tabuleiro.pe�a(posicao).movimentosPossiveis();//retornar os movimentos possiveis dessa pe�a nessa posicao
	}
	
	
	public Pe�aXadrez MovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosition(); // converter para posicao da matriz
		Posicao destino = posicaoDestino.toPosition();
		validaPosicaoOrigem(origem);//validar se nessa posicao de origem existe uma pe�a 
		validaPosicaoDestino(origem, destino);
		Pe�a capturaPe�a = fazerMover(origem, destino);
		
		//testar se esse movimento colocou o pr�prio jogador em check;
		if(testaCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturaPe�a);
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
		
		return (Pe�aXadrez) capturaPe�a;
	}
	
	// m�todo para fazer movimento
	private Pe�a fazerMover(Posicao origem, Posicao destino) {
		Pe�a p = tabuleiro.removePe�a(origem);
		Pe�a pe�aCapturada = tabuleiro.removePe�a(destino);
		tabuleiro.PosicaoPe�a(p, destino);
		
		if(pe�aCapturada != null) {
			pe�asDoTabuleiro.remove(pe�aCapturada);
			capturaPe�as.add(pe�aCapturada);
		}
		return pe�aCapturada;
	}
	
	
	// m�todo para desfazer movimento caso o usu�rio entre em xeque
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Pe�a capturape�a) {
		Pe�a p = tabuleiro.removePe�a(destino); // tira a pe�a do destino 
		tabuleiro.PosicaoPe�a(p, origem); //devolve a pe�a do destino para a posicao de origem
		
		if(capturape�a != null) {
			tabuleiro.PosicaoPe�a(capturape�a, destino);
			capturaPe�as.remove(capturape�a);
			pe�asDoTabuleiro.add(capturape�a);
		}
		
	}
	private void validaPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.ExisteUmaPe�a(posicao)) { // se nao existir uma pe�a nessa posicao eu vou dar uma exce��o
			throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
		}
		if(jogadorAtual != ((Pe�aXadrez)tabuleiro.pe�a(posicao)).getCores()) {
			throw new ExcecaoXadrez("A peca escolhida nao e sua");
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
	
	// m�todo que troca o turno
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}
	
	//m�todo devolve o oponente de uma cor
	private Cores oponente(Cores cor) {
		return (cor == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}
	
	
	// m�todo para localizar o rei de uma determinada cor
	private Pe�aXadrez rei(Cores cor) {
		//filtragem da lista
		List<Pe�a> list = pe�asDoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCores() == cor).collect(Collectors.toList());
		for(Pe�a p : list) {
			if(p instanceof Rei) {
				return (Pe�aXadrez)p;
			}
		}
		// se o conpilador nao encontrar lan�a uma exce�ao
		throw new IllegalStateException("Nao existe o rei da " + cor + " no tabuleiro");
	}
	
	
	//m�todo para testar check
	private boolean testaCheck(Cores cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosition(); // pega a posicao do rei na posicao de matriz
		List<Pe�a> oponentePe�as = pe�asDoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCores() == oponente(cor)).collect(Collectors.toList());
		for(Pe�a p : oponentePe�as) {
			boolean[][] mat = p.movimentosPossiveis(); //matriz de movimentos possiveis dessa pe�a advers�ria p
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	//m�todo para testar check mate
	private boolean testaCheckMate(Cores cor) {
		if(!testaCheck(cor)) {
			return false;
		}
		List<Pe�a> list = pe�asDoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCores() == cor).collect(Collectors.toList());
		for(Pe�a p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao origem = ((Pe�aXadrez)p).getPosicaoXadrez().toPosition();
						Posicao destino = new Posicao(i, j);
						Pe�a pe�aCapturada = fazerMover(origem, destino);
						boolean testCheck = testaCheck(cor);
						desfazerMovimento(origem, destino, pe�aCapturada);
						
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	// m�todo que recebe as coordenadas do xadrez
	private void NovaPosicaoPe�a(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.PosicaoPe�a(pe�a, new PosicaoXadrez(coluna, linha).toPosition()); // recebe uma linha e coluna s� que converte para posicao de matriz
		pe�asDoTabuleiro.add(pe�a);
	}

	private void ConfiguracaoInicial() {
		NovaPosicaoPe�a('h', 7, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('d', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe�a('e', 1, new Rei(tabuleiro, Cores.WHITE));

		NovaPosicaoPe�a('b', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe�a('a', 8, new Rei(tabuleiro, Cores.BLACK));
	}
}
