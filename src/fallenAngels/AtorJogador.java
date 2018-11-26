package fallenAngels;

import rede.AtorRede;
import rede.Mensagem;

public class AtorJogador extends Arena{
    boolean vencedor = false;
    boolean conectado = false;
    int nPersonagem;
    InterfaceInicial game;
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
        game = new InterfaceInicial();
        game.screenRender(this);

    }

    public void acaoIniciarPartida(){
        rede.iniciarPartidaRede();
    }

	public void iniciarPartidaRede() {
		arena = new Arena();
        arena.inicializarArena(nPersonagem);
        enviarEstadoInicial();
        jogo = new InterfaceJogo();
        jogo.renderGameScreen(this);
        System.out.println("Imprimiu tela!");

	}

	public void enviarEstadoInicial(){
        rede.enviarJogada(arena);
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
        if(rodada == 0){
            rodada += 1;
            this.arena.jogador2 = arena.jogador1;
        }else {
            this.arena.jogador2 = arena.jogador1;
            this.arena.jogador1 = arena.jogador2;
            if (arena.jogador1.ptsVida == 0) {
                jogo.notificarVencedor(2);
                rede.enviarJogada(arena);
            } else if (arena.jogador2.ptsVida == 0) {
                jogo.notificarVencedor(2);
                rede.enviarJogada(arena);
            }
            jogo.tela(this);
        }
	}
}
