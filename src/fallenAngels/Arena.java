package fallenAngels;

public class Arena {
    public boolean partidaAndamento = false;
    public boolean conectado = false;
    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    InterfaceJogo jogo = new InterfaceJogo();

    public static void main(String args[]){
        InterfaceInicial game = new InterfaceInicial();
        game.screenRender(false);
    }

    public void iniciarPartida(){
        jogador1.criarJogador(2);
        jogador2.criarJogador(1);

        partidaAndamento = true;
        jogo.renderGameScreen(jogador1, jogador2, 0, 0);
    }

    public void processarJogada(int seta1, int seta2){
        Golpe golpe = new Golpe();
        Heal heal = new Heal();
        switch (seta2){
            case 1:
                if(seta1 == 0 || seta1 == 2){
                    golpe = jogador1.golpe1;
                }else {
                    heal = jogador1.heal1;
                }
                break;
            case 2:
                if(seta1 == 0 || seta1 == 2){
                    golpe = jogador1.golpe2;
                }else {
                    heal = jogador1.heal2;
                }
                break;
            case 3:
                if(seta1 == 0 || seta1 == 2){
                    golpe = jogador1.golpe3;
                }else {
                    heal = jogador1.heal3;
                }
                break;
        }
        if(seta1 == 0 || seta1 == 2){
            double random = ((Math.random() * golpe.range/100 - golpe.range/200)*2)+1;
            jogador2.ptsVida -= ((golpe.danoBase * random)*jogador2.fatorReducao);
            jogador1.fatorReducao = 1;
            jogo.notificarDano(((golpe.danoBase * random)*jogador2.fatorReducao));
            jogo.renderGameScreen(jogador1, jogador2, 0,0);
        }else{
            double random = ((Math.random() * heal.range/100 - heal.range/200)*2)+1;
            jogador1.ptsVida += (heal.danoBase * random);
            jogador1.fatorReducao = 1 - heal.fatorReducao;
        }
    }

    public boolean informarConectado(){
        return conectado;
    }

    public void atribuirConectado(boolean valor){
        conectado = valor;
    }
}
