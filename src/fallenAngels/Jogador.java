package fallenAngels;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.util.ArrayList;

public class Jogador implements Jogada {
    String name, url;
    int ptsVida, vidaMaxima;
    float fatorReducao;
    Golpe golpe1 = new Golpe();
    Golpe golpe2 = new Golpe();
    Golpe golpe3 = new Golpe();
    Heal heal1 = new Heal();
    Heal heal2 = new Heal();
    Heal heal3 = new Heal();

    public void criarJogador(int nPersonagem){
        fatorReducao = 1;
        switch (nPersonagem){
            case 0:
                name = "Bal'Uruk, Demonio Rei";
                url = "https://i.imgur.com/2FB1LnO.png";
                ptsVida = 2000;
                vidaMaxima = ptsVida;

                golpe1.criarGolpe("Condenação",750, 100);
                golpe2.criarGolpe("Sugar alma",250, 10);
                golpe3.criarGolpe("Coração demoníaco",400, 25);

                heal1.criarHeal("Poção de cristal",600, 15, 25);
                heal2.criarHeal("Pacto negro", 350, 50, 15);
                heal3.criarHeal("Sacrifício eterno", 500, 60, 90);
                break;

            case 1:
                name = "Bardawulf";
                url = "https://i.imgur.com/asIIA77.png";
                ptsVida = 1950;
                vidaMaxima = ptsVida;

                golpe1.criarGolpe("Morder",300, 150);
                golpe2.criarGolpe("Destroçar",200, 10);
                golpe3.criarGolpe("Rugido alucinante",500, 75);

                heal1.criarHeal("Cura rápida",700, 15, 25);
                heal2.criarHeal("Uivo selvagem", 250, 60, 15);
                heal3.criarHeal("Armadura de ossos", 50, 75, 100);
                break;

            case 2:
                name = "Archon, Guardião das Cinzas";
                url = "https://i.imgur.com/HmQPDgb.png";
                ptsVida = 2200;
                vidaMaxima = ptsVida;

                golpe1.criarGolpe("Fúria de vingança",450, 20);
                golpe2.criarGolpe("Consagração",220, 10);
                golpe3.criarGolpe("Ashmourne",550, 80);

                heal1.criarHeal("Benção dos deuses",100, 80, 100);
                heal2.criarHeal("Luz sagrada", 600, 20, 15);
                heal3.criarHeal("Cura Eterna", 1500, 0, 1);
                break;

            case 3:
                name = "Taurus";
                url = "https://i.imgur.com/a6BczbN.png";
                ptsVida = 2500;
                vidaMaxima = ptsVida;

                golpe1.criarGolpe("Pulverizar",800, 110);
                golpe2.criarGolpe("Debandada",400, 75);
                golpe3.criarGolpe("Chifrada",300, 300);

                heal1.criarHeal("Vontade inabalável",50, 85, 200);
                heal2.criarHeal("Gênese súbita", 500, 45, 15);
                heal3.criarHeal("Fator Reegen", 800, 20, 50);
                break;
        }
    }
}
