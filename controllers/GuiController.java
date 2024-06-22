package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import models.*;

import java.io.File;
import java.io.IOException;
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
    private ImageView cartaImagem;
    @FXML
    private AnchorPane anchorPane; // Referência ao AnchorPane do game.fxml

    private Jogo jogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (difficultyComboBox != null) {
            difficultyComboBox.getItems().addAll("Fácil", "Médio", "Difícil", "Impossível");
        } else {
            System.out.println("difficultyComboBox is null!");
        }
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/game.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);

            GuiController controller = loader.getController();
            controller.setJogo(jogo);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            // Configura a imagem de fundo
            controller.setImagemDeFundo();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEscolherAtributo(ActionEvent event) {
        String atributo = ((Node) event.getSource()).getId();
        if (jogo != null) {
            boolean jogadorRealVenceu = jogo.compararCategoria(atributo);
            atualizarInterface(); // Atualiza a interface após a jogada
            if (jogo.verificarSeAcabou()) {
                mostrarTelaFimDeJogo(jogo.isJogadorRealVencedor());
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
                mostrarTelaFimDeJogo(jogo.isJogadorRealVencedor());
            }
        }
    }

    private void atualizarInterface() {
        jogadorRealCartas.setText("Cartas do Jogador: " + jogo.getJogadorReal().getBaralhoMao().getBaralho().size());
        jogadorMaquinaCartas.setText("Cartas da Máquina: " + jogo.getJogadorMaquina().getBaralhoMao().getBaralho().size());

        // Verifica se há cartas na mão do jogador real
        if (!jogo.getJogadorReal().getBaralhoMao().getBaralho().isEmpty()) {
            // Obtém a carta atual do jogador real
            Carta cartaAtualJogador = jogo.getJogadorReal().getBaralhoMao().getBaralho().get(0);

            // Monta o caminho completo da imagem da carta baseado no nome da carta
            String nomeCarta = cartaAtualJogador.getNome();
            String caminhoImagem = "/views/assets/cartas/" + nomeCarta + ".png";

            // Carrega a imagem correspondente
            try {
                Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
                cartaImagem.setImage(imagem);
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
                // Tratar exceção ao carregar a imagem
                cartaImagem.setImage(null);
            }
        } else {
            // Se não há cartas na mão do jogador, define uma imagem padrão ou vazia
            cartaImagem.setImage(null);
        }
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
        atualizarInterface();
    }

    private void setImagemDeFundo() {
        // Carrega a imagem de fundo
        Image imagemDeFundo = new Image("/views/fundoTela.jpg");

        // Configura a imagem de fundo no AnchorPane
        BackgroundImage backgroundImage = new BackgroundImage(
                imagemDeFundo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
    }

    private void mostrarTelaFimDeJogo(boolean jogadorRealGanhou) {
        try {
            // Carregar o arquivo FXML da tela de fim de jogo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fimDeJogo.fxml"));
            Parent root = loader.load();

            // Obter o controlador da tela de fim de jogo
            FimDeJogoController fimDeJogoController = loader.getController();

            // Configurar a imagem de vitória ou derrota
            if (jogadorRealGanhou) {
                fimDeJogoController.setImagemDeResultado("/views/assets/victory.png");
            } else {
                fimDeJogoController.setImagemDeResultado("/views/assets/defeat.png");
            }

            // Mostrar a nova cena
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
