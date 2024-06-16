package models;

public class JogadorMaquina extends Jogador{
    private DificuldadeJogo dificuldadeJogo;
    public JogadorMaquina(DificuldadeJogo dificuldadeJogo) {
        super();
        this.dificuldadeJogo = dificuldadeJogo;
    }
}
