package models;

import java.util.ArrayList;

public abstract class Baralho {
    protected ArrayList<Carta> baralho;
    private static float fofuraMedia;
    private static float brincalhaoMedia;
    private static float agressividadeMedia;
    private static float agilidadeMedia;
    private static float obedienciaMedia;
    private int quantidade;

    public void instanciarMedia(){
        float i = 0;
        float fofuraMedia_ = 0;
        float brincalhaoMedia_ = 0;
        float agressividadeMedia_ = 0;
        float agilidadeMedia_ = 0;
        float obedienciaMedia_ = 0;
        for(Carta carta : baralho){
            i++;
            fofuraMedia_ = fofuraMedia_ + carta.getFofura();
            brincalhaoMedia_ = brincalhaoMedia_ + carta.getBrincalhao();
            agressividadeMedia_ = agressividadeMedia_ + carta.getAgressividade();
            agilidadeMedia_ = agilidadeMedia_ + carta.getAgilidade();
            obedienciaMedia_ = obedienciaMedia_ + carta.getObediencia();
        }
        fofuraMedia = fofuraMedia_/i;
        brincalhaoMedia = brincalhaoMedia_/i;
        agressividadeMedia = agressividadeMedia_/i;
        agilidadeMedia = agilidadeMedia_/i;
        obedienciaMedia = obedienciaMedia_/i;

        System.out.println(fofuraMedia);
        System.out.println(brincalhaoMedia);
        System.out.println(agressividadeMedia);
        System.out.println(agilidadeMedia);
        System.out.println(obedienciaMedia);
    }

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(ArrayList<Carta> baralho) {
        this.baralho = baralho;
    }

    public static float getFofuraMedia() {
        return fofuraMedia;
    }

    public static void setFofuraMedia(int fofuraMedia) {
        Baralho.fofuraMedia = fofuraMedia;
    }

    public static float getBrincalhaoMedia() {
        return brincalhaoMedia;
    }

    public static void setBrincalhaoMedia(int brincalhaoMedia) {
        Baralho.brincalhaoMedia = brincalhaoMedia;
    }

    public static float getAgressividadeMedia() {
        return agressividadeMedia;
    }

    public static void setAgressividadeMedia(int agressividadeMedia) {
        Baralho.agressividadeMedia = agressividadeMedia;
    }

    public static float getAgilidadeMedia() {
        return agilidadeMedia;
    }

    public static void setAgilidadeMedia(int agilidadeMedia) {
        Baralho.agilidadeMedia = agilidadeMedia;
    }

    public static float getObedienciaMedia() {
        return obedienciaMedia;
    }

    public static void setObedienciaMedia(int obedienciaMedia) {
        Baralho.obedienciaMedia = obedienciaMedia;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}