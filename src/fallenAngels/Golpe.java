package fallenAngels;

public class Golpe {
    float danoBase, range;
    String name;

    public void criarGolpe(String name, float danoBase, float range){
        this.name = name;
        this.danoBase = danoBase;
        this.range = range;
    }
}
