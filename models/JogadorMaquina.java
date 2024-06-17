package models;

public class JogadorMaquina extends Jogador{
    private DificuldadeJogo dificuldadeJogo;
    public JogadorMaquina(DificuldadeJogo dificuldadeJogo) {
        super();
        this.dificuldadeJogo = dificuldadeJogo;
    }

    public BaralhoMao getBaralhoMao(){
        return baralhoMao;
    }
}
