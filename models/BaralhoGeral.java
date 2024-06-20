package models;

import java.util.ArrayList;
import java.util.Collections;

public class BaralhoGeral extends Baralho {
    public BaralhoGeral() {
        super();
        this.baralho = new ArrayList<>();
    }

    public void embaralhar() {
        Collections.shuffle(baralho);
    }
    public void distribuirCartas(Jogador jogadorReal, JogadorMaquina jogadorMaquina) {
        int metade = baralho.size() / 2;
        for (int i = 0; i < metade; i++) {
            jogadorReal.receberCarta(baralho.get(i));
        }
        for (int i = metade; i < baralho.size(); i++) {
            jogadorMaquina.receberCarta(baralho.get(i));
        }
        baralho.clear();
    }
}
