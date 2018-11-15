package fallenAngels;

public class Heal {
    float danoBase, fatorReducao, range;
    String name;

    public void criarHeal(String name, float danoBase, float fatorReducao, float range){
        this.name = name;
        this.danoBase = danoBase;
        this.fatorReducao = fatorReducao;
        this.range = range;
    }
}
