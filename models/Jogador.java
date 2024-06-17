package models;

public abstract class Jogador {
    protected BaralhoMao baralhoMao;

    public Jogador() {
        this.baralhoMao = new BaralhoMao();
    }

    public void receberCarta(Carta carta) {
        baralhoMao.colocarCartaNoFim(carta);
    }

    public Carta perderCarta() {
        if (!baralhoMao.getBaralho().isEmpty()) {
            return baralhoMao.getBaralho().remove(0);
        }
        return null;
    }

    public BaralhoMao getBaralhoMao() {
        return baralhoMao;
    }
}
