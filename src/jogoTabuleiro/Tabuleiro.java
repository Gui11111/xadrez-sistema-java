package jogoTabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peça[][] peças; //matriz de Peça - peças
	
	// construtor somente com linhas e colunas
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			//throw new ExceçaoTabuleiro("Erro criando tabuleiro, é necessário que haja pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	// metodos getters de linhas e colunas somente, porque nao pode alterar linhas ou colunas
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	// método para retornar a peça dada uma linha e coluna
	public Peça peça(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			//throw new ExceçaoTabuleiro("Posição inexistente no tabuleiro");
		}
		return peças[linha][coluna];
	}
	
	// sobrecarga do método Peça só que recebe posicao;
	public Peça peça(Posicao posicao) {
		if (!posiçaoExistente(posicao)) {
			//throw new ExceçaoTabuleiro("Posição inexistente no tabuleiro ");
		}
		return peças[posicao.getLinha()][posicao.getColuna()];
	}
	
	// pega a matriz e na posiçao dada, atribuir a ela a peça que eu informei
	public void PosicaoPeça(Peça peça, Posicao posicao) {
		/*if(ExisteUmaPeça(posicao)) {
			throw new ExceçaoTabuleiro("Já existe uma peça nessa posição " + posicao);
		}*/
		peças[posicao.getLinha()][posicao.getColuna()] = peça;
		peça.posicao = posicao;
	}
	//método auxiliar 
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posiçaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean ExisteUmaPeça(Posicao posicao) {
		if (!posiçaoExistente(posicao)) {
			//throw new ExceçaoTabuleiro("Posição inexistente no tabuleiro ");
		}
		return peça(posicao) != null; // testar se tem uma peça nessa posião
	}
}
