package xadrez.pecas;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PeçaXadrez;

	public class Torre extends PeçaXadrez{

		//construtor
		public Torre(Tabuleiro tabuleiro, Cores cores) {
			super(tabuleiro, cores);
		}

		@Override
		public String toString() {
			return "T";
		}
}
