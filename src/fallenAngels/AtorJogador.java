package fallenAngels;

import rede.AtorRede;
import rede.Mensagem;

public class AtorJogador extends Arena{
    boolean vencedor = false;
    boolean conectado = false;
    int nPersonagem;
    AtorRede rede;
    Arena arena;
    InterfaceJogo jogo;
    int rodada = 0;

    public static void main(String args[]){
        InterfaceInicial game = new InterfaceInicial();
        game.screenRender(new AtorJogador());
    }

    public AtorJogador(){
        super();
        rede = new AtorRede(this);
    }

    public void conectar(String servidor, String jogadorId, int nPersonagem){
        this.nPersonagem = nPersonagem;
        conectado = rede.conectar(jogadorId, "localhost");
        InterfaceInicial game = new InterfaceInicial();
        game.screenRender(this);

    }

    public void acaoIniciarPartida(){
        rede.iniciarPartidaRede();
    }

	public void iniciarPartidaRede() {
		arena = new Arena();
		arena.inicializarArena(nPersonagem);
		jogo = new InterfaceJogo();
		jogo.renderGameScreen(this);
	}

	public void novaMensagem(){
        if(rede.ehMinhaVez()){
            arena.processarJogada(jogo.seta1, jogo.seta2);
            rede.enviarJogada(arena);
        }else{
            jogo.notificarErroVez();
        }
        jogo.tela(this);
    }

	public void receberMensagemRede(Arena arena) {
		this.arena = arena;
		jogo.tela(this);
	}
}
