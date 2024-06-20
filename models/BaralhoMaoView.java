package models;

public class BaralhoMaoView {
    public void verCartasJogo(BaralhoMao baralhoMao) {
        for (Carta carta : baralhoMao.getBaralho()) {
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
