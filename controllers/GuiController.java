package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private Label jogadorRealCartas;
    @FXML
    private Label jogadorMaquinaCartas;
    @FXML
    private TextArea cartaAtual;
    @FXML
    private ImageView cartaImagem;
    @FXML
    private AnchorPane anchorPane;

    private Jogo jogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyComboBox.getItems().addAll("Fácil", "Médio", "Difícil", "Impossível");
    }
    
    @FXML
    private void handleStartGame(ActionEvent event) {
        String selectedDifficulty = difficultyComboBox.getValue();
        if (selectedDifficulty == null) {
            System.out.println("Selecione uma dificuldade!");
            return;
        }

        DificuldadeJogo dificuldadeJogo;
        switch (selectedDifficulty) {
            case "Fácil":
                dificuldadeJogo = DificuldadeJogo.FACIL;
                break;
            case "Médio":
                dificuldadeJogo = DificuldadeJogo.MEDIO;
                break;
            case "Difícil":
                dificuldadeJogo = DificuldadeJogo.DIFICIL;
                break;
            case "Impossível":
                dificuldadeJogo = DificuldadeJogo.IMPOSSIVEL;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedDifficulty);
        }

        String nome = "Jogador Real";
        JogadorReal jogadorReal = new JogadorReal(nome);
        JogadorMaquina jogadorMaquina = new JogadorMaquina(dificuldadeJogo);
        BaralhoGeral baralhoGeral = new BaralhoGeral();
        jogo = Jogo.getInstance(jogadorMaquina, jogadorReal, baralhoGeral);
        LerCarta lerCarta = new LerCarta();
        lerCarta.lerArquivo(jogo, "views/Cartas.xml");
        baralhoGeral.instanciarMedia();

        jogo.embaralharEDistribuirCartas();

        // Configurar imagem de fundo
        Image imagemDeFundo = new Image("/views/fundoTela.jpg");
        if (anchorPane != null) {
            anchorPane.setStyle("-fx-background-image: url('/views/fundoTela.jpg');" +
                    "-fx-background-size: cover;");
        }

        atualizarInterface(); // Verifique se 'atualizarInterface' está tratando todos os elementos FXML corretamente
    }


    @FXML
    private void handleEscolherAtributo(ActionEvent event) {
        String atributo = ((Label) event.getSource()).getText(); // Exemplo: aqui você ajusta para capturar o atributo correto
        if (jogo != null) {
            boolean jogadorRealVenceu = jogo.compararCategoria(atributo);
            atualizarInterface();
            if (jogo.verificarSeAcabou()) {
                System.out.println("Fim do jogo!");
            } else if (!jogadorRealVenceu) {
                handleTurnoComputador();
            }
        }
    }

    private void handleTurnoComputador() {
        if (jogo != null && !jogo.isJogadorRealTurno()) {
            Carta cartaComputador = jogo.getJogadorMaquina().getBaralhoMao().getBaralho().get(0);
            String escolhaComputador = jogo.getJogadorMaquina().escolheCategoria(jogo.getJogadorMaquina().getDificuldadeJogo(), cartaComputador);
            System.out.println("Computador escolheu o atributo " + escolhaComputador);
            boolean computadorVenceu = jogo.compararCategoria(escolhaComputador);
            atualizarInterface();
            if (jogo.verificarSeAcabou()) {
                System.out.println("Fim do jogo!");
            }
        }
    }

    private void atualizarInterface() {
        if (jogo == null) return;

        jogadorRealCartas.setText("Cartas do Jogador: " + jogo.getJogadorReal().getBaralhoMao().getBaralho().size());
        jogadorMaquinaCartas.setText("Cartas da Máquina: " + jogo.getJogadorMaquina().getBaralhoMao().getBaralho().size());

        Carta cartaAtualJogador = jogo.getJogadorReal().getBaralhoMao().getBaralho().get(0);
        cartaAtual.setText("Carta Atual: \nNome: " + cartaAtualJogador.getNome()
                + "\nFofura: " + cartaAtualJogador.getFofura()
                + "\nAgilidade: " + cartaAtualJogador.getAgilidade()
                + "\nAgressividade: " + cartaAtualJogador.getAgressividade()
                + "\nBrincalhão: " + cartaAtualJogador.getBrincalhao()
                + "\nObediência: " + cartaAtualJogador.getObediencia());

        // Carregar a imagem correspondente à carta atual
        int indiceCarta = jogo.getJogadorReal().getBaralhoMao().getBaralho().indexOf(cartaAtualJogador) + 1;
        String caminhoImagem = "/views/assets/cartas/carta_" + indiceCarta + ".png";
        Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
        cartaImagem.setImage(imagem);
    }
}
