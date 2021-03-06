package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoTabuleiro.Pe?a;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez { // classe principal do sistema do jogo de xadrez

	private int turno;
	private Cores jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private Pe?aXadrez enPassant;
	private Pe?aXadrez promocao;

	private List<Pe?a> pe?asDoTabuleiro = new ArrayList<>();
	private List<Pe?a> capturaPe?as = new ArrayList<>();

	// construtor padr?o
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8); // dimens?o do tabuleiro
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

	public Pe?aXadrez getEnPassant() {
		return enPassant;
	}

	public Pe?aXadrez getPromocao() {
		return promocao;
	}

	public Pe?aXadrez[][] getPe?a() { // m?todo que retorna uma matriz correspondente a PartidaXadrez
		Pe?aXadrez[][] mat = new Pe?aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		// percorrer a matriz de pe?as do tabuleiro
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (Pe?aXadrez) tabuleiro.pe?a(i, j);
			}
		}
		return mat;
	}

	// m?todo que serve para imprimir as posi??es possiveis a a partir de uma
	// posicao de origem na classe Programa
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosition();
		validaPosicaoOrigem(posicao);
		return tabuleiro.pe?a(posicao).movimentosPossiveis();// retornar os movimentos possiveis dessa pe?a nessa
																// posicao
	}

	public Pe?aXadrez MovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosition(); // converter para posicao da matriz
		Posicao destino = posicaoDestino.toPosition();
		validaPosicaoOrigem(origem);// validar se nessa posicao de origem existe uma pe?a
		validaPosicaoDestino(origem, destino);
		Pe?a capturaPe?a = fazerMover(origem, destino);

		// testar se esse movimento colocou o pr?prio jogador em check;
		if (testaCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturaPe?a);
			throw new ExcecaoXadrez("Voce nao pode se colocar em check");
		}

		Pe?aXadrez pe?aMovida = (Pe?aXadrez) tabuleiro.pe?a(destino);

		// m?todo especial promocao
		promocao = null;
		if(pe?aMovida instanceof Peao) {
			if((pe?aMovida.getCores() == Cores.WHITE && destino.getLinha() == 0) || pe?aMovida.getCores() == Cores.BLACK && destino.getLinha() == 7) {
				promocao = (Pe?aXadrez)tabuleiro.pe?a(destino);
				promocao = substituiPe?aPromovida("A");
			}
		}
		
		// testar se o oponente ficou em check
		check = (testaCheck(oponente(jogadorAtual))) ? true : false;

		// se a jogada deixou o oponente em checkMate o jogo vai ter que aprar
		if (testaCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}

		// testar se a pe?a movida foi um peao que moveu duas casas
		if (pe?aMovida instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassant = pe?aMovida;
		} else {
			enPassant = null;
		}

		return (Pe?aXadrez) capturaPe?a;
	}

	
	public Pe?aXadrez substituiPe?aPromovida(String tipo) {
		if(promocao == null) {
			throw new IllegalStateException("Nao ha peca para ser promovida");
		}
		if(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("A")) {
			return promocao;
		}
		
		Posicao pos = promocao.getPosicaoXadrez().toPosition();
		Pe?a p = tabuleiro.removePe?a(pos);
		pe?asDoTabuleiro.remove(p);
		
		Pe?aXadrez novaPe?a = novaPe?a(tipo, promocao.getCores());
		tabuleiro.PosicaoPe?a(novaPe?a, pos);
		pe?asDoTabuleiro.add(novaPe?a);
		
		return novaPe?a;
		
	}
	// m?todo auxiliar para instanciar a pe?a
	private Pe?aXadrez novaPe?a(String tipo, Cores cor) {
		if(tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if(tipo.equals("T")) return new Torre(tabuleiro, cor);
		return new Rainha(tabuleiro, cor);
	}
	
	// m?todo para fazer movimento
	private Pe?a fazerMover(Posicao origem, Posicao destino) {
		Pe?aXadrez p = (Pe?aXadrez) tabuleiro.removePe?a(origem);
		p.aumentaContagemMovimentos();
		Pe?a pe?aCapturada = tabuleiro.removePe?a(destino);
		tabuleiro.PosicaoPe?a(p, destino);

		if (pe?aCapturada != null) {
			pe?asDoTabuleiro.remove(pe?aCapturada);
			capturaPe?as.add(pe?aCapturada);
		}

		// tratando o Roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			Pe?aXadrez torre = (Pe?aXadrez) tabuleiro.removePe?a(origemT);
			tabuleiro.PosicaoPe?a(torre, destinoT);
			torre.aumentaContagemMovimentos();
		}

		// tratando o Roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			Pe?aXadrez torre = (Pe?aXadrez) tabuleiro.removePe?a(origemT);
			tabuleiro.PosicaoPe?a(torre, destinoT);
			torre.aumentaContagemMovimentos();
		}

		// m?todo especial en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pe?aCapturada == null) {
				Posicao posicaoPeao;
				if (p.getCores() == Cores.WHITE) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pe?aCapturada = tabuleiro.removePe?a(posicaoPeao);
				capturaPe?as.add(pe?aCapturada);
				pe?asDoTabuleiro.remove(pe?aCapturada);
			}
		}

		return pe?aCapturada;
	}

	// m?todo para desfazer movimento caso o usu?rio entre em xeque

	private void desfazerMovimento(Posicao origem, Posicao destino, Pe?a capturape?a) {
		Pe?aXadrez p = (Pe?aXadrez) tabuleiro.removePe?a(destino); // tira a pe?a do destino
		p.diminuiContagemMovimentos();
		tabuleiro.PosicaoPe?a(p, origem); // devolve a pe?a do destino para a posicao de origem
		if (capturape?a != null) {
			tabuleiro.PosicaoPe?a(capturape?a, destino);
			capturaPe?as.remove(capturape?a);
			pe?asDoTabuleiro.add(capturape?a);
		}

		// tratando o Roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			Pe?aXadrez torre = (Pe?aXadrez) tabuleiro.removePe?a(destinoT);
			tabuleiro.PosicaoPe?a(torre, origemT);
			torre.diminuiContagemMovimentos();
		}

		// tratando o Roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			Pe?aXadrez torre = (Pe?aXadrez) tabuleiro.removePe?a(destinoT);
			tabuleiro.PosicaoPe?a(torre, origemT);
			torre.diminuiContagemMovimentos();
		}

		// m?todo especial en passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && capturape?a == enPassant) {
				Pe?aXadrez peao = (Pe?aXadrez)tabuleiro.removePe?a(destino);
				Posicao posicaoPeao;
				if (p.getCores() == Cores.WHITE) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.PosicaoPe?a(peao, posicaoPeao);
			}
		}

	}

	private void validaPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.ExisteUmaPe?a(posicao)) { // se nao existir uma pe?a nessa posicao eu vou dar uma exce??o
			throw new ExcecaoXadrez("Nao existe peca na posicao de origem");
		}
		if (jogadorAtual != ((Pe?aXadrez) tabuleiro.pe?a(posicao)).getCores()) {
			throw new ExcecaoXadrez("A peca escolhida nao e sua");
		}
		if (!tabuleiro.pe?a(posicao).ExisteAlgumMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca escolhida");
		}
	}

	private void validaPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.pe?a(origem).movimentoPossivel(destino)) {
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}

	// m?todo que troca o turno
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	// m?todo devolve o oponente de uma cor
	private Cores oponente(Cores cor) {
		return (cor == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	// m?todo para localizar o rei de uma determinada cor
	private Pe?aXadrez rei(Cores cor) {
		// filtragem da lista
		List<Pe?a> list = pe?asDoTabuleiro.stream().filter(x -> ((Pe?aXadrez) x).getCores() == cor)
				.collect(Collectors.toList());
		for (Pe?a p : list) {
			if (p instanceof Rei) {
				return (Pe?aXadrez) p;
			}
		}
		// se o conpilador nao encontrar lan?a uma exce?ao
		throw new IllegalStateException("Nao existe o rei da " + cor + " no tabuleiro");
	}

	// m?todo para testar check
	private boolean testaCheck(Cores cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosition(); // pega a posicao do rei na posicao de matriz
		List<Pe?a> oponentePe?as = pe?asDoTabuleiro.stream().filter(x -> ((Pe?aXadrez) x).getCores() == oponente(cor))
				.collect(Collectors.toList());
		for (Pe?a p : oponentePe?as) {
			boolean[][] mat = p.movimentosPossiveis(); // matriz de movimentos possiveis dessa pe?a advers?ria p
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	// m?todo para testar check mate
	private boolean testaCheckMate(Cores cor) {
		if (!testaCheck(cor)) {
			return false;
		}
		List<Pe?a> list = pe?asDoTabuleiro.stream().filter(x -> ((Pe?aXadrez) x).getCores() == cor)
				.collect(Collectors.toList());
		for (Pe?a p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((Pe?aXadrez) p).getPosicaoXadrez().toPosition();
						Posicao destino = new Posicao(i, j);
						Pe?a pe?aCapturada = fazerMover(origem, destino);
						boolean testCheck = testaCheck(cor);
						desfazerMovimento(origem, destino, pe?aCapturada);

						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	// m?todo que recebe as coordenadas do xadrez
	private void NovaPosicaoPe?a(char coluna, int linha, Pe?aXadrez pe?a) {
		tabuleiro.PosicaoPe?a(pe?a, new PosicaoXadrez(coluna, linha).toPosition()); // recebe uma linha e coluna s? que
																					// converte para posicao de matriz
		pe?asDoTabuleiro.add(pe?a);
	}

	private void ConfiguracaoInicial() {
		NovaPosicaoPe?a('a', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('b', 1, new Cavalo(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('c', 1, new Bispo(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('d', 1, new Rainha(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('e', 1, new Rei(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('f', 1, new Bispo(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('g', 1, new Cavalo(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('h', 1, new Torre(tabuleiro, Cores.WHITE));
		NovaPosicaoPe?a('a', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('b', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('c', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('d', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('e', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('f', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('g', 2, new Peao(tabuleiro, Cores.WHITE, this));
		NovaPosicaoPe?a('h', 2, new Peao(tabuleiro, Cores.WHITE, this));

		NovaPosicaoPe?a('a', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('b', 8, new Cavalo(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('c', 8, new Bispo(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('d', 8, new Rainha(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('e', 8, new Rei(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('f', 8, new Bispo(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('g', 8, new Cavalo(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('h', 8, new Torre(tabuleiro, Cores.BLACK));
		NovaPosicaoPe?a('a', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('b', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('c', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('d', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('e', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('f', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('g', 7, new Peao(tabuleiro, Cores.BLACK, this));
		NovaPosicaoPe?a('h', 7, new Peao(tabuleiro, Cores.BLACK, this));
	}
}
