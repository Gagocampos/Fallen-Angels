package fallenAngels;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Golpe implements Jogada {
    float danoBase, range;
    String name;

    public void criarGolpe(String name, float danoBase, float range){
        this.name = name;
        this.danoBase = danoBase;
        this.range = range;
    }
}
