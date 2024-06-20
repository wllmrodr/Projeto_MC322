package models;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Baralho {
    protected ArrayList<Carta> baralho;
    private static float fofuraMedia;
    private static float agilidadeMedia;
    private static float agressividadeMedia;
    private static float brincalhaoMedia;
    private static float obedienciaMedia;
    private int quantidade;

    public Baralho() {
        this.baralho = new ArrayList<>();
    }

    public void instanciarMedia() {
        float i = 0;
        float fofuraMedia_ = 0;
        float brincalhaoMedia_ = 0;
        float agressividadeMedia_ = 0;
        float agilidadeMedia_ = 0;
        float obedienciaMedia_ = 0;
        for (Carta carta : baralho) {
            i++;
            fofuraMedia_ = fofuraMedia_ + carta.getFofura();
            brincalhaoMedia_ = brincalhaoMedia_ + carta.getBrincalhao();
            agressividadeMedia_ = agressividadeMedia_ + carta.getAgressividade();
            agilidadeMedia_ = agilidadeMedia_ + carta.getAgilidade();
            obedienciaMedia_ = obedienciaMedia_ + carta.getObediencia();
        }
        fofuraMedia = fofuraMedia_ / i;
        brincalhaoMedia = brincalhaoMedia_ / i;
        agressividadeMedia = agressividadeMedia_ / i;
        agilidadeMedia = agilidadeMedia_ / i;
        obedienciaMedia = obedienciaMedia_ / i;

    }

    public static float getFofuraMedia() {
        return fofuraMedia;
    }


    public static float getBrincalhaoMedia() {
        return brincalhaoMedia;
    }

    public static float getAgressividadeMedia() {
        return agressividadeMedia;
    }

    public static float getAgilidadeMedia() {
        return agilidadeMedia;
    }
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

}
