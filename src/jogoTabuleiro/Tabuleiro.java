package jogoTabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Pe�a[][] pe�as; //matriz de Pe�a - pe�as
	
	// construtor somente com linhas e colunas
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro criando tabuleiro, � necess�rio que haja pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pe�as = new Pe�a[linhas][colunas];
	}

	// metodos getters de linhas e colunas somente, porque nao pode alterar linhas ou colunas
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	// m�todo para retornar a pe�a dada uma linha e coluna
	public Pe�a pe�a(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new ExcecaoTabuleiro("Posi��o inexistente no tabuleiro");
		}
		return pe�as[linha][coluna];
	}
	
	// sobrecarga do m�todo Pe�a s� que recebe posicao;
	public Pe�a pe�a(Posicao posicao) {
		if (!posi�aoExistente(posicao)) {
			throw new ExcecaoTabuleiro("Posi��o inexistente no tabuleiro ");
		}
		return pe�as[posicao.getLinha()][posicao.getColuna()];
	}
	
	// pega a matriz e na posi�ao dada, atribuir a ela a pe�a que eu informei
	public void PosicaoPe�a(Pe�a pe�a, Posicao posicao) {
		if(ExisteUmaPe�a(posicao)) {
			throw new ExcecaoTabuleiro("J� existe uma pe�a nessa posi��o " + posicao);
		}
		pe�as[posicao.getLinha()][posicao.getColuna()] = pe�a;
		pe�a.posicao = posicao;
	}
	
	
	public Pe�a removePe�a(Posicao posicao) {
		if(!posi�aoExistente(posicao)) {
			throw new ExcecaoTabuleiro("Posi��o inexistente no tabuleiro ");
		}
		if(pe�a(posicao) == null) { // testar se a pe�a do tabuleiro nessa posicao � igual a nulo 
			return null;
		}
		Pe�a aux = pe�a(posicao);
		aux.posicao = null; //essa pe�a foi retirada do tabuleiro
		pe�as [posicao.getLinha()][posicao.getColuna()] = null; //indica que nao tem mais pe�as nessa matriz
		return aux;
	}
	
	
	
	//m�todo auxiliar 
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posi�aoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean ExisteUmaPe�a(Posicao posicao) {
		if (!posi�aoExistente(posicao)) {
			throw new ExcecaoTabuleiro("Posi��o inexistente no tabuleiro ");
		}
		return pe�a(posicao) != null; // testar se tem uma pe�a nessa posi�o
	}
}
