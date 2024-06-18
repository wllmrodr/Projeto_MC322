package models;

import java.util.Random;

public class JogadorMaquina extends Jogador{
    private DificuldadeJogo dificuldadeJogo;
    public JogadorMaquina(DificuldadeJogo dificuldadeJogo) {
        super();
        this.dificuldadeJogo = dificuldadeJogo;
    }

    public BaralhoMao getBaralhoMao(){
        return baralhoMao;
    }

    private boolean ehMaior(float a, float b, float c, float d, float e){
        if(a>b && a>c && a>d && a>e){
            return true;
        }
        return false;
    }

    private String escolheCategoriaDadoProb(double probabilidade, Carta carta) {
        float fofura = carta.getFofura() - BaralhoGeral.getFofuraMedia();
        float agilidade = carta.getAgilidade() - BaralhoGeral.getAgilidadeMedia();
        float agressividade = carta.getAgressividade() - BaralhoGeral.getAgressividadeMedia();
        float brincalhao = carta.getBrincalhao() - BaralhoGeral.getBrincalhaoMedia();
        float obediencia = carta.getObediencia() - BaralhoGeral.getBrincalhaoMedia();

        if (Math.random() < probabilidade) {
            if (ehMaior(fofura, agilidade, agressividade, brincalhao, obediencia)) {
                return "1";
            }
            if (ehMaior(agilidade, fofura, agressividade, brincalhao, obediencia)) {
                return "2";
            }
            if (ehMaior(agressividade, fofura, agilidade, brincalhao, obediencia)) {
                return "3";
            }
            if (ehMaior(brincalhao, fofura, agressividade, agilidade, obediencia)) {
                return "4";
            }
            if (ehMaior(obediencia, fofura, agressividade, brincalhao, agilidade)) {
                return "5";
            }
        }
        // escolhe aleatoriamente

        double aleatorio = Math.random();

        if(aleatorio < 0.2){
            return "1";
        }
        if(aleatorio < 0.4){
            return "2";
        }
        if(aleatorio < 0.6){
            return "3";
        }
        if(aleatorio < 0.8){
            return "4";
        }
        if(aleatorio < 1){
            return "5";
        }
        return null;
    }

    public String escolheCategoria(DificuldadeJogo dificuldadeJogo, Carta carta){
        double probabilidade = 0;
        switch (dificuldadeJogo){
            case FACIL -> probabilidade = 0.5;
            case MEDIO -> probabilidade = 0.6;
            case DIFICIL -> probabilidade= 0.8;
            case IMPOSSIVEL -> probabilidade = 1.0;
        }
        return escolheCategoriaDadoProb(probabilidade, carta);
    }
}
