package models;

import java.util.ArrayList;

public class BaralhoMao extends Baralho {
    private final int limite = 4;

    public BaralhoMao() {
        super();
        this.baralho = new ArrayList<>();
    }

    public void colocarCartaNoFim(Carta carta) {
        baralho.add(carta);
    }

    public void perderCarta() {
        if (!baralho.isEmpty()) {
            baralho.remove(0);
        }
    }
}

