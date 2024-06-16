public class Jogo {
    private JogadorMaquina jogadorMaquina;
    private JogadorReal jogadorReal;
    private  BaralhoGeral baralhoGeral;
    private BaralhoMesa baralhoMesa;

    public Jogo(JogadorMaquina jogadorMaquina, JogadorReal jogadorReal, BaralhoGeral baralhoGeral, BaralhoMesa baralhoMesa) {
        this.jogadorMaquina = jogadorMaquina;
        this.jogadorReal = jogadorReal;
        this.baralhoGeral = baralhoGeral;
        this.baralhoMesa = baralhoMesa;
    }
    public BaralhoGeral getBaralhoGeral() {
        return baralhoGeral;
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
