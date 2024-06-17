package models;

public abstract class Jogador {
    protected BaralhoMao baralhoMao;

    public void receberCarta (Carta carta){
        baralhoMao.getBaralho().add(carta);
    }

}
