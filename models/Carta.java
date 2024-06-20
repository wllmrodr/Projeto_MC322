package models;

public class Carta {
    private String nome;
    private int fofura;
    private int agilidade;
    private int agressividade;
    private int brincalhao;
    private int obediencia;

    public Carta(String nome, int fofura, int agilidade, int agressividade, int brincalhao, int obediencia) {
        this.nome = nome;
        this.fofura = fofura;
        this.brincalhao = brincalhao;
        this.agressividade = agressividade;
        this.agilidade = agilidade;
        this.obediencia = obediencia;
    }

    public String getNome() {
        return nome;
    }

    public int getFofura() {
        return fofura;
    }


    public int getBrincalhao() {
        return brincalhao;
    }


    public int getAgressividade() {
        return agressividade;
    }


    public int getAgilidade() {
        return agilidade;
    }


    public int getObediencia() {
        return obediencia;
    }


    public void printarCarta() {
            System.out.println("Nome: " + nome
                    + "\nFofura: " + fofura
                    + "\nAgilidade: " + agilidade
                    + "\nAgressividade: " + agressividade
                    + "\nBrincalhão: " + brincalhao
                    + "\nObediência: " + obediencia
                    + "\n");
        }

}
