package controllers;

import models.*;

public class Jogo {
    private static Jogo instancia;
    private JogadorMaquina jogadorMaquina;
    private JogadorReal jogadorReal;
    private BaralhoGeral baralhoGeral;
    private Jogador perdedorAnterior; // Campo para rastrear o perdedor anterior
    private boolean primeiraRodada; // Campo para verificar se é a primeira rodada
    private boolean jogadorRealTurno;

    private Jogo(JogadorMaquina jogadorMaquina, JogadorReal jogadorReal, BaralhoGeral baralhoGeral) {
        this.jogadorMaquina = jogadorMaquina;
        this.jogadorReal = jogadorReal;
        this.baralhoGeral = baralhoGeral;
        this.primeiraRodada = true; // Inicializa como verdadeira
        this.jogadorRealTurno = true; // Jogador Real começa a primeira rodada
    }

    public static Jogo getInstance(JogadorMaquina jogadorMaquina, JogadorReal jogadorReal, BaralhoGeral baralhoGeral){
        if(instancia == null){
            instancia = new Jogo(jogadorMaquina,jogadorReal, baralhoGeral);
        }
        return instancia;
    }

    public BaralhoGeral getBaralhoGeral() {
        return baralhoGeral;
    }

    public JogadorReal getJogadorReal() {
        return jogadorReal;
    }

    public JogadorMaquina getJogadorMaquina() {
        return jogadorMaquina;
    }

    public boolean isJogadorRealTurno() {
        return jogadorRealTurno;
    }

    public boolean verificarSeAcabou() {
        if (jogadorReal.getBaralhoMao().getBaralho().size() == 32) {
            System.out.println("PARABÉNS! VOCÊ GANHOU!!");
            return true;
        }
        if (jogadorMaquina.getBaralhoMao().getBaralho().size() == 32) {
            System.out.println("Infelizmente o computador te superou no trunfo...");
            return true;
        }
        return false;
    }

    public void embaralharEDistribuirCartas() {
        baralhoGeral.embaralhar();
        baralhoGeral.distribuirCartas(jogadorReal, jogadorMaquina);
    }

    public boolean compararCategoria(String categoria) {
        Carta cartaJogador = jogadorReal.getBaralhoMao().getBaralho().get(0);
        Carta cartaMaquina = jogadorMaquina.getBaralhoMao().getBaralho().get(0);

        float valorJogador = 0, valorMaquina = 0;
        switch (categoria) {
            case "1":
                valorJogador = cartaJogador.getFofura();
                valorMaquina = cartaMaquina.getFofura();
                break;
            case "2":
                valorJogador = cartaJogador.getAgilidade();
                valorMaquina = cartaMaquina.getAgilidade();
                break;
            case "3":
                valorJogador = cartaJogador.getAgressividade();
                valorMaquina = cartaMaquina.getAgressividade();
                break;
            case "4":
                valorJogador = cartaJogador.getBrincalhao();
                valorMaquina = cartaMaquina.getBrincalhao();
                break;
            case "5":
                valorJogador = cartaJogador.getObediencia();
                valorMaquina = cartaMaquina.getObediencia();
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
                return false;
        }

        if (valorJogador > valorMaquina) {
            jogadorReal.ganharCartaDeOutroJogador(jogadorMaquina, cartaMaquina);
            perdedorAnterior = jogadorMaquina;
            jogadorRealTurno = true;
            return true;
        } else if (valorJogador < valorMaquina) {
            jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
            perdedorAnterior = jogadorReal;
            jogadorRealTurno = false;
            return false;
        } else {
            if (perdedorAnterior == jogadorReal) {
                jogadorReal.ganharCartaDeOutroJogador(jogadorMaquina, cartaMaquina);
                perdedorAnterior = jogadorMaquina;
            } else {
                jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
                perdedorAnterior = jogadorReal;
            }

            if (primeiraRodada) {
                jogadorRealTurno = true;
                return true;
            } else {
                jogadorRealTurno = perdedorAnterior != jogadorReal;
                return jogadorRealTurno;
            }
        }
    }
}
