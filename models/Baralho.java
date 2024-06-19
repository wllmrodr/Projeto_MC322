package models;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Baralho {
    protected ArrayList<Carta> baralho;

    public Baralho() {
        this.baralho = new ArrayList<>();
    }

    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(ArrayList<Carta> baralho) {
        this.baralho = baralho;
    }

    public Carta removerCartaDoTopo() {
        if (!baralho.isEmpty()) {
            return baralho.remove(0);
        }
        return null;
    }

    public void adicionarCarta(Carta carta) {
        baralho.add(carta);
    }
}
