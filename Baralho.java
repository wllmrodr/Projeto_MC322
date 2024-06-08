import java.util.ArrayList;

public abstract class Baralho {
    protected ArrayList<Carta> baralho;
    private static int fofuraMedia;
    private static int brincalhaoMedia;
    private static int agressividadeMedia;
    private static int agilidadeMedia;
    private static int obedienciaMedia;
    private int quantidade;

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(ArrayList<Carta> baralho) {
        this.baralho = baralho;
    }

    public static int getFofuraMedia() {
        return fofuraMedia;
    }

    public static void setFofuraMedia(int fofuraMedia) {
        Baralho.fofuraMedia = fofuraMedia;
    }

    public static int getBrincalhaoMedia() {
        return brincalhaoMedia;
    }

    public static void setBrincalhaoMedia(int brincalhaoMedia) {
        Baralho.brincalhaoMedia = brincalhaoMedia;
    }

    public static int getAgressividadeMedia() {
        return agressividadeMedia;
    }

    public static void setAgressividadeMedia(int agressividadeMedia) {
        Baralho.agressividadeMedia = agressividadeMedia;
    }

    public static int getAgilidadeMedia() {
        return agilidadeMedia;
    }

    public static void setAgilidadeMedia(int agilidadeMedia) {
        Baralho.agilidadeMedia = agilidadeMedia;
    }

    public static int getObedienciaMedia() {
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
