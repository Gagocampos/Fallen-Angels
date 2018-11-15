package fallenAngels;

public class AtorJogador extends Arena{
    String jogadorId, servidor;
    boolean vencedor = false;
    boolean vezAtual = false;

    public void conectar(String servidor, String jogadorId, int nPersonagem){
        atribuirConectado(true);
        InterfaceInicial game = new InterfaceInicial();
        game.screenRender(true);

    }

    public void tratarLance(int seta1, int seta2){
        processarJogada(seta1, seta2);
    }
}
