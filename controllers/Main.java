package controllers;

import models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Esse é o nosso MEGATRUNFO de cachorros! Para você que não está acostumando com as regras do SUPERTRUNFO," +
                "aqui vão elas:\n\n1) O baralho possuí 32 duas cartas que são divididas igualmente entre os dois jogadores no começo do jogo.\n" +
                "\n2)Cada carta é um cachorro, com suas respectivas pontuações para FOFURA, AGILIDADE, AGRESSIVIDADE, BRINCALHÃO e OBEDIÊNCIA." +
                "\n\n3)Você só pode usar a primeira carta do seu baralho." +
                "\n\n4)No começo do jogo, um jogador é sorteado para escolher um atributo, ambas as cartas duelam e a que tiver maior valor ganha e coloca ambas as cartas no final de seu baralho. E inicia uma nova rodada, com o vencedor escolhendo o atributo." +
                "\n\n5)Em caso de empate o jogador que fez a escolha de atributo tem a DESVANTAGEM");

        System.out.println("Digite seu nome:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();

        LerCarta lerCarta = new LerCarta();
        JogadorReal jogadorReal = new JogadorReal(nome);

        System.out.println("Qual dificuldade você deseja? Digite o número do atributo desejado.\n\n" +
                "1) Fácil\n2) Medio\n3) Dificil\n4) Impossivel");

        DificuldadeJogo dificuldadeJogo= null;

        String dificuldade = scanner.nextLine();

        switch (dificuldade){
            case "1": dificuldadeJogo = DificuldadeJogo.FACIL;
            case "2": dificuldadeJogo = DificuldadeJogo.MEDIO;
            case "3": dificuldadeJogo = DificuldadeJogo.DIFICIL;
            case "4": dificuldadeJogo = DificuldadeJogo.IMPOSSIVEL;
        }

        JogadorMaquina jogadorMaquina = new JogadorMaquina(dificuldadeJogo);
        BaralhoGeral baralhoGeral = new BaralhoGeral();

        Jogo jogo = new Jogo(jogadorMaquina, jogadorReal, baralhoGeral);
        lerCarta.lerArquivo(jogo, "views\\Cartas.xml");

        baralhoGeral.instanciarMedia();

        jogo.loopDeJogo();
    }
}
