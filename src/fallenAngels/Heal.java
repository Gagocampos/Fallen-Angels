package fallenAngels;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Heal implements Jogada {
    float danoBase, fatorReducao, range;
    String name;

    public void criarHeal(String name, float danoBase, float fatorReducao, float range){
        this.name = name;
        this.danoBase = danoBase;
        this.fatorReducao = fatorReducao;
        this.range = range;
    }
}
