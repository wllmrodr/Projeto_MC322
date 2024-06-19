package models;

public abstract class Jogador {
    protected BaralhoMao baralhoMao;

    public Jogador() {
        this.baralhoMao = new BaralhoMao();
    }

    public void receberCarta(Carta carta) {
        baralhoMao.getBaralho().add(carta);
    }

    public void perderCarta() {
        baralhoMao.perderCarta();
    }

    public BaralhoMao getBaralhoMao() {
        return baralhoMao;
    }

    public void ganharCartaDeOutroJogador(Jogador outroJogador, Carta carta) {
        outroJogador.perderCarta();
        this.receberCarta(carta);
    }
}
