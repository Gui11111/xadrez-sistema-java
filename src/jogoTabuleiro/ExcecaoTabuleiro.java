package jogoTabuleiro;

public class ExcecaoTabuleiro extends RuntimeException { // exce�ao opicional de ser tratada
	
	private static final long serialVersionUID = 1L;
	
	// construtor que recebe o string
	public ExcecaoTabuleiro(String msg) {
		super(msg);
	}
}
