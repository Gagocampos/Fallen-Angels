package fallenAngels;

import br.ufsc.inf.leobr.cliente.Jogada;
import rede.AtorRede;
import rede.Mensagem;

public class AtorJogador{
    boolean conectado = false;
    int nPersonagem;
    public InterfaceInicial game;
    AtorRede rede;
    Arena arena;
    InterfaceJogo jogo;
    public int rodada = 0;

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
        boolean sucesso = rede.enviarEstadoInicial(arena);
        if(sucesso){
            jogo = new InterfaceJogo();
            System.out.println("Imprimindo tela...");
            jogo.renderGameScreen(this);
        }else{
            jogo.notificarErroCriacao();
        }

    }

    public void receberEstadoInicial(Arena arena){
        this.arena.jogador2 = arena.jogador1;
        rodada += 1;
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

    public void reiniciar(){
        if(rede.ehMinhaVez()){
            arena.jogador1.ptsVida = arena.jogador1.vidaMaxima;
            arena.jogador2.ptsVida = arena.jogador2.vidaMaxima;
            rede.enviarJogada(arena);
        }else {
            jogo.notificarErroVez();
        }
    }

    public void receberMensagemRede(Arena arena) {
        this.arena.jogador2 = arena.jogador1;
        this.arena.jogador1 = arena.jogador2;
        if (arena.jogador1.ptsVida == 0) {
            jogo.notificarVencedor(2);
            rede.enviarJogada(arena);
            System.exit(0);
        } else if (arena.jogador2.ptsVida == 0) {
            jogo.notificarVencedor(1);
            rede.enviarJogada(arena);
            System.exit(0);
        }
        jogo.tela(this);
    }
    public void desconectar(){
        rede.desconectar();
        conectado = false;
        game = new InterfaceInicial();
        game.screenRender(this);
    }
}
