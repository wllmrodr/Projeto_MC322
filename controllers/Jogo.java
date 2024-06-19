package controllers;

import models.*;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private JogadorMaquina jogadorMaquina;
    private JogadorReal jogadorReal;
    private BaralhoGeral baralhoGeral;
    private Jogador perdedorAnterior; // Novo campo para rastrear o perdedor anterior
    private boolean primeiraRodada; // Novo campo para verificar se é a primeira rodada

    public Jogo(JogadorMaquina jogadorMaquina, JogadorReal jogadorReal, BaralhoGeral baralhoGeral) {
        this.jogadorMaquina = jogadorMaquina;
        this.jogadorReal = jogadorReal;
        this.baralhoGeral = baralhoGeral;
        this.primeiraRodada = true; // Inicializa como verdadeira
    }

    public BaralhoGeral getBaralhoGeral() {
        return baralhoGeral;
    }

    private boolean verificaSeAcabou() {
        if (jogadorReal.getBaralhoMao().getBaralho().size() == 32) {
            System.out.println("PARABÉNS! VOCÊ GANHOU!!");
            return false;
        }
        if (jogadorMaquina.getBaralhoMao().getBaralho().size() == 32) {
            System.out.println("Infelizmente o computador te superou no trunfo...");
            return false;
        }
        return true;
    }

    private boolean compararCategoria(String categoria) {
        while (true) {
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
                perdedorAnterior = jogadorMaquina; // Atualiza o perdedor anterior
                primeiraRodada = false; // Define como não sendo mais a primeira rodada
                return true;
            } else if (valorJogador < valorMaquina) {
                jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
                perdedorAnterior = jogadorReal; // Atualiza o perdedor anterior
                primeiraRodada = false; // Define como não sendo mais a primeira rodada
                return false;
            } else {
                // Empate: aplica o novo critério de desempate
                if (primeiraRodada) {
                    // Se for a primeira rodada, Jogador Real ganha
                    jogadorReal.ganharCartaDeOutroJogador(jogadorMaquina, cartaMaquina);
                    primeiraRodada = false; // Define como não sendo mais a primeira rodada
                    return true;
                } else if (perdedorAnterior == null) {
                    // Se não houver um perdedor anterior, considere empate novamente
                    jogadorReal.getBaralhoMao().colocarCartaNoFim(cartaJogador);
                    jogadorMaquina.getBaralhoMao().colocarCartaNoFim(cartaMaquina);
                } else if (perdedorAnterior instanceof JogadorReal) {
                    jogadorReal.ganharCartaDeOutroJogador(jogadorMaquina, cartaMaquina);
                } else {
                    jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
                }
                return true; // Após o desempate, jogadorReal começa a próxima rodada
            }
        }
    }

    public void loopDeJogo() {
        baralhoGeral.embaralhar();
        baralhoGeral.distribuirCartas(jogadorReal, jogadorMaquina);
        Random rand = new Random();
        int aleatorio = rand.nextInt(2);
        boolean vencedor = aleatorio == 0;  // Se vencedor = true, rodada jogador Real. Se false, computador escolhe.

        boolean parada = true;
        while (parada) {
            if (vencedor) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Qual atributo você escolhe? Digite o número do atributo desejado.\n\n" +
                        "1) Fofura\n2) Agilidade\n3) Agressividade\n4) Brincalhão\n5) Obediência");
                String escolha = scanner.nextLine();
                vencedor = compararCategoria(escolha);  // Determina o vencedor da próxima rodada com base na comparação
                parada = verificaSeAcabou();
            } else {
                // Lógica para o computador escolher um atributo (simplesmente escolhemos um atributo aleatório aqui)
                String[] atributos = {"1", "2", "3", "4", "5"};
                String escolhaComputador = atributos[rand.nextInt(atributos.length)];
                System.out.println("Computador escolheu o atributo " + escolhaComputador);
                vencedor = !compararCategoria(escolhaComputador);  // O computador escolhe, então inverte o resultado
                parada = verificaSeAcabou();
            }
        }
    }

    public void verCartasJogo() {
        for (Carta carta : baralhoGeral.getBaralho()) {
            System.out.println("Nome: " + carta.getNome()
                    + "\nFofura: " + carta.getFofura()
                    + "\nAgilidade: " + carta.getAgilidade()
                    + "\nAgressividade: " + carta.getAgressividade()
                    + "\nBrincalhão: " + carta.getBrincalhao()
                    + "\nObediência: " + carta.getObediencia()
                    + "\n");
        }
    }
}

