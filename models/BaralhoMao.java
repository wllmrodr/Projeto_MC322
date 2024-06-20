package models;

import java.util.ArrayList;

public class BaralhoMao extends Baralho {
    private final int limite = 32;
    public BaralhoMao() {
        super();
        this.baralho = new ArrayList<Carta>();
    }
    public void verCartasJogo() {
        for (Carta carta : this.getBaralho()) {
            System.out.println("Nome: " + carta.getNome()
                    + "\nFofura: " + carta.getFofura()
                    + "\nAgilidade: " + carta.getAgilidade()
                    + "\nAgressividade: " + carta.getAgressividade()
                    + "\nBrincalhão: " + carta.getBrincalhao()
                    + "\nObediência: " + carta.getObediencia()
                    + "\n");
        }
    }

    public int tamanhoBaralho(){
        return baralho.size();
    }

    // Método para colocar uma carta no fim do baralho
    public void colocarCartaNoFim(Carta carta) {
        if (baralho.size() < limite) {
            baralho.add(carta);
        } else {
            System.out.println("O limite de cartas na mão foi atingido.");
        }
    }

    // Método para perder uma carta e ela ir para o fim do baralho do outro jogador
    public void perderCartaParaOutroJogador(BaralhoMao outroJogador, Carta carta) {
        if (baralho.remove(carta)) {
            outroJogador.colocarCartaNoFim(carta);
        } else {
            System.out.println("A carta não está presente na mão.");
        }
        baralho.add(carta);
    }

    // Método para ganhar uma carta de outro jogador e colocá-la no fim do baralho
    public void ganharCartaDeOutroJogador(BaralhoMao outroJogador, Carta carta) {
        if (outroJogador.getBaralho().remove(carta)) {
            this.colocarCartaNoFim(carta);
        } else {
            System.out.println("O outro jogador não possui a carta.");
        }
    }
    public void perderCarta() {
        if (!baralho.isEmpty()) {
            baralho.remove(0);
        }
    }
}

