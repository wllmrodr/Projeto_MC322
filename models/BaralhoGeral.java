package models;
import java.util.ArrayList;
import java.util.Collections;

public class BaralhoGeral extends Baralho {
    public BaralhoGeral() {
        super();
        this.baralho = new ArrayList<Carta>();
    }

    // Método para embaralhar as cartas no baralho
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    // Método para distribuir cartas entre dois jogadores
    public void distribuirCartas(Jogador jogadorReal, JogadorMaquina jogadorMaquina) {
        int totalCartas = baralho.size();
        int metade = totalCartas / 2;

        // Distribui a primeira metade das cartas para o jogador
        for (int i = 0; i < metade; i++) {
            Carta carta = baralho.remove(0);
            jogadorReal.receberCarta(carta);
        }

        // Distribui a segunda metade das cartas para o jogador máquina
        while (!baralho.isEmpty()) {
            Carta carta = baralho.remove(0);
            jogadorMaquina.receberCarta(carta);
        }
    }
}
