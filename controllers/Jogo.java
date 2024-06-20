package controllers;

import models.*;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jogo {
    private JogadorMaquina jogadorMaquina;
    private JogadorReal jogadorReal;
    private BaralhoGeral baralhoGeral;
    private Jogador perdedorAnterior; // Campo para rastrear o perdedor anterior
    private boolean primeiraRodada; // Campo para verificar se é a primeira rodada

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
                perdedorAnterior = jogadorMaquina;
                return true;
            } else if (valorJogador < valorMaquina) {
                jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
                perdedorAnterior = jogadorReal;
                return false;
            } else {
                // Em caso de empate, as cartas vão para o final dos respectivos baralhos
                if (perdedorAnterior==jogadorReal){
                    jogadorReal.ganharCartaDeOutroJogador(jogadorMaquina, cartaMaquina);
                    perdedorAnterior = jogadorMaquina;
                } else {
                    jogadorMaquina.ganharCartaDeOutroJogador(jogadorReal, cartaJogador);
                    perdedorAnterior = jogadorReal;
                }

                // Verifica se é a primeira rodada
                if (primeiraRodada) {
                    return true; // Jogador Real ganha no desempate da primeira rodada
                } else {
                    // Quem perdeu na rodada anterior ganha no desempate
                    return perdedorAnterior != jogadorReal;
                }
            }
        }
    }

    public void loopDeJogo() {
        baralhoGeral.embaralhar();
        baralhoGeral.distribuirCartas(jogadorReal, jogadorMaquina);
        boolean vencedor = true; // Jogador Real começa a primeira rodada
        primeiraRodada = true; // Reseta a primeira rodada

        boolean parada = true;
        int i =0;
        while (parada) {
            pausinha(2000);
            System.out.println("RODADA "+i);
            System.out.println("Quantidade de cartas "+jogadorReal.getNome() + " :" + jogadorReal.getBaralhoMao().getBaralho().size());
            System.out.println("Quantidade de cartas CPU :" + jogadorMaquina.getBaralhoMao().getBaralho().size());
            if (vencedor) {
                Scanner scanner = new Scanner(System.in);
                pausinha(1000);
                System.out.println("Sua primeira carta do baralho é: ");
                jogadorReal.getBaralhoMao().getBaralho().get(0).printarCarta();
                pausinha(3000);
                System.out.println("Qual atributo você escolhe? Digite o número do atributo desejado.\n\n" +
                        "1) Fofura\n2) Agilidade\n3) Agressividade\n4) Brincalhão\n5) Obediência");
                String escolha = scanner.nextLine();
                pausinha(2000);

                jogadorMaquina.getBaralhoMao().getBaralho().get(0).printarCarta();
                vencedor = compararCategoria(escolha);  // Determina o vencedor da próxima rodada com base na comparação
                parada = verificaSeAcabou();
            } else {
                System.out.println("Agora é a vez do computador escolher... sua carta é:");
                pausinha(2000);
                jogadorReal.getBaralhoMao().getBaralho().get(0).printarCarta();
                pausinha(2000);
                // Lógica para o computador escolher um atributo
                Carta cartaComputador = jogadorMaquina.getBaralhoMao().getBaralho().get(0);
                String escolhaComputador = jogadorMaquina.escolheCategoria(jogadorMaquina.getDificuldadeJogo(), cartaComputador);
                System.out.println("Computador escolheu o atributo " + escolhaComputador);
                pausinha(1000);
                jogadorMaquina.getBaralhoMao().getBaralho().get(0).printarCarta();
                vencedor = compararCategoria(escolhaComputador);  // O computador escolhe, então inverte o resultado
                parada = verificaSeAcabou();
            }
            primeiraRodada = false; // Define que a primeira rodada já foi
            i++;
        }
    }

    public void pausinha(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
