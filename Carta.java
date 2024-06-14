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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFofura() {
        return fofura;
    }

    public void setFofura(int fofura) {
        this.fofura = fofura;
    }

    public int getBrincalhao() {
        return brincalhao;
    }

    public void setBrincalhao(int brincalhao) {
        this.brincalhao = brincalhao;
    }

    public int getAgressividade() {
        return agressividade;
    }

    public void setAgressividade(int agressividade) {
        this.agressividade = agressividade;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getObediencia() {
        return obediencia;
    }

    public void setObediencia(int obediencia) {
        this.obediencia = obediencia;
    }
}
