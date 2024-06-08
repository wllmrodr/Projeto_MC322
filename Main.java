public class Main {
    public static void main(String[] args){

        LerCarta lerCarta = new LerCarta();
        JogadorReal jogadorReal = new JogadorReal();
        JogadorMaquina jogadorMaquina = new JogadorMaquina();
        BaralhoMesa baralhoMesa = new BaralhoMesa();
        BaralhoGeral baralhoGeral = new BaralhoGeral();

        Jogo jogo = new Jogo(jogadorMaquina, jogadorReal, baralhoGeral, baralhoMesa);

        lerCarta.lerArquivo(jogo, "Cartas.xml");
        jogo.verCartasJogo();
    }
}
