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
    private TextArea cartaAtual;
    @FXML
    private ImageView cartaImagem;
    @FXML
    private AnchorPane anchorPane; // Referência ao AnchorPane do game.fxml

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
        jogadorRealCartas.setText("Cartas do Jogador: " + jogo.getJogadorReal().getBaralhoMao().getBaralho().size());
        jogadorMaquinaCartas.setText("Cartas da Maquina: " + jogo.getJogadorMaquina().getBaralhoMao().getBaralho().size());

        // Obter a carta atual do jogador real
        Carta cartaAtualJogador = jogo.getJogadorReal().getBaralhoMao().getBaralho().get(0);

        // Atualizar o texto no TextArea para exibir informações da carta
        cartaAtual.setText("Carta Atual: \nNome: " + cartaAtualJogador.getNome()
                + "\nFofura: " + cartaAtualJogador.getFofura()
                + "\nAgilidade: " + cartaAtualJogador.getAgilidade()
                + "\nAgressividade: " + cartaAtualJogador.getAgressividade()
                + "\nBrincalhão: " + cartaAtualJogador.getBrincalhao()
                + "\nObediência: " + cartaAtualJogador.getObediencia());

        // Determinar o índice da carta atual para montar o caminho da imagem
        int indiceCarta = jogo.getJogadorReal().getBaralhoMao().getBaralho().indexOf(cartaAtualJogador) + 1;

        // Montar o caminho completo da imagem da carta baseado no índice
        String caminhoImagem = "C:/JMC17/Projeto_MC322/views/assets/cartas/carta_" + indiceCarta + ".png";

        // Carregar a imagem correspondente
        Image imagem = new Image(new File(caminhoImagem).toURI().toString());

        // Definir a imagem no ImageView cartaImagem
        cartaImagem.setImage(imagem);
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
}
