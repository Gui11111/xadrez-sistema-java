package xadrez.pecas;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.Pe�aXadrez;

	public class Torre extends Pe�aXadrez{

		//construtor
		public Torre(Tabuleiro tabuleiro, Cores cores) {
			super(tabuleiro, cores);
		}

		@Override
		public String toString() {
			return "T";
		}
}
