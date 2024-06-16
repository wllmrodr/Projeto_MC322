package models;

public class JogadorReal extends Jogador{
    private String nome;
    public JogadorReal(String nome) {
        super();
        this.nome = nome;
        this.baralhoMao = new BaralhoMao();
    }
}
