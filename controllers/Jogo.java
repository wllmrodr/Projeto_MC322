package controllers;

import models.*;

import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private JogadorMaquina jogadorMaquina;
    private JogadorReal jogadorReal;
    private  BaralhoGeral baralhoGeral;

    public Jogo(JogadorMaquina jogadorMaquina, JogadorReal jogadorReal, BaralhoGeral baralhoGeral) {
        this.jogadorMaquina = jogadorMaquina;
        this.jogadorReal = jogadorReal;
        this.baralhoGeral = baralhoGeral;
    }
    public BaralhoGeral getBaralhoGeral() {
        return baralhoGeral;
    }

    public void loopDeJogo(){
        baralhoGeral.distribuirCartas(jogadorReal, jogadorMaquina);
        Random rand = new Random();
        int aleatorio = rand.nextInt(2);
        boolean vencedor;
        if(aleatorio == 0){
            vencedor = true;
        }else {
            vencedor = false;
        }
        //se vencedor = true, rodada jogador Real. Se false, computador que escolhe.

        boolean parada = true;
        while (parada){
            if(vencedor){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Qual atributo você escolhe? Digite o número do atributo desejado.\n\n" +
                        "1) Fofura\n2) Agilidade\n3) Agressividade\n4) Brincalhão\n5)Obediência");
                String escolha = scanner.nextLine();
                switch (escolha){
                    case "1":

                }


            }
        }
    }

    public void verCartasJogo(){
        for (Carta carta : baralhoGeral.getBaralho()){
            System.out.println("Nome: "+ carta.getNome()
                    + "\nFofura: "+ carta.getFofura()
                    + "\nAgilidade: "+ carta.getAgilidade()
                    + "\nAgressividade: "+ carta.getAgressividade()
                    + "\nBrincalhão: "+ carta.getBrincalhao()
                    + "\nObediência: "+ carta.getObediencia()
                    + "\n"
            );
        }
    }
}
