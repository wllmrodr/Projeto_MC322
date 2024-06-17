package models;

import java.util.ArrayList;
import java.util.Collections;  // Importa a classe Collections para embaralhamento

public class BaralhoGeral extends Baralho {
    public BaralhoGeral() {
        super();
        this.baralho = new ArrayList<Carta>();
    }
    
    // Método para embaralhar as cartas no baralho
    public void embaralhar() {
        Collections.shuffle(baralho);  // Embaralha o ArrayList de Carta
    }
}

