package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import fallenAngels.Arena;

public class Mensagem implements Jogada {
	private Arena arena;

	public Mensagem(Arena arena) {
		super();
		this.arena = arena;
	}

	public Arena getMensagem() {
		return this.arena;
	}
	
}
