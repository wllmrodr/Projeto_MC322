package models;

import java.util.ArrayList;

public class JogadorReal extends Jogador {
    private String nome;
    public JogadorReal(String nome) {
        super();
        this.nome = nome;
        this.baralhoMao = new BaralhoMao();
    }

    public String getNome() {
        return nome;
    }

    public BaralhoMao getBaralhoMao(){
        return baralhoMao;
    }
}
