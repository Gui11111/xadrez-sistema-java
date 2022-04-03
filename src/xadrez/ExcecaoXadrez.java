package xadrez;

public class ExcecaoXadrez extends RuntimeException{

	private static final long serialVersionUID = 1L;

	// construtor que recebe o string
	public ExcecaoXadrez(String msg) {
		super(msg);
	}
}
