package controllers;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
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
    private ImageView cartaImagemJogadorReal;
    @FXML
    private ImageView cartaImagemJogadorMaquina;
    @FXML
    private AnchorPane anchorPane; // Referência ao AnchorPane do game.fxml

    private Jogo jogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (difficultyComboBox != null) {
            difficultyComboBox.getItems().addAll("Fácil", "Médio", "Difícil", "Impossível");
        }
    }

    @FXML
    private void handleStartGame(ActionEvent event) {
        String selectedDifficulty = difficultyComboBox.getValue();
        if (selectedDifficulty.equals(null)) {
            System.out.println("Selecione uma dificuldade!");
            return;
        }
        DificuldadeJogo dificuldadeJogo = null;
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
        Carta cartaAtualJogadorMaquina = jogo.getJogadorMaquina().getBaralhoMao().getBaralho().get(0);
        String caminhoImagemMaquina = "/views/assets/cartas/" + cartaAtualJogadorMaquina.getNome() + ".png";
        if (jogo != null) {
            boolean jogadorRealVenceu = jogo.compararCategoria(atributo);
            // Atualiza a imagem da carta do jogador da máquina
            try {
                Image imagem = new Image(getClass().getResourceAsStream(caminhoImagemMaquina));
                cartaImagemJogadorMaquina.setImage(imagem);
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
                // Tratar exceção ao carregar a imagem
                cartaImagemJogadorMaquina.setImage(null);
            }

            // Adiciona um atraso de 2 segundos antes de atualizar a interface
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> {
                atualizarInterface(); // Atualiza a interface após a jogada
                if (jogo.verificarSeAcabou()) {
                    mostrarTelaFimDeJogo(jogo.isJogadorRealVencedor());
                } else if (!jogadorRealVenceu) {
                    handleTurnoComputador();
                }
            });
            delay.play();
        }
    }

    private void handleTurnoComputador() {
        if (jogo != null) {
            Carta cartaComputador = jogo.getJogadorMaquina().getBaralhoMao().getBaralho().get(0);

            // Mostrar a carta do computador virada para baixo
            String caminhoImagemMaquinaVerso = "/views/assets/cartas/verso.png";
            try {
                Image imagemVerso = new Image(getClass().getResourceAsStream(caminhoImagemMaquinaVerso));
                cartaImagemJogadorMaquina.setImage(imagemVerso);
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
                cartaImagemJogadorMaquina.setImage(null);
            }

            // Primeiro atraso de 2 segundos para revelar a escolha do computador
            PauseTransition delay1 = new PauseTransition(Duration.seconds(2));
            delay1.setOnFinished(e1 -> {
                String escolhaComputador = jogo.getJogadorMaquina().escolheCategoria(jogo.getJogadorMaquina().getDificuldadeJogo(), cartaComputador);
                String categoria = null;
                if(escolhaComputador.equals("1")){
                    categoria = "Fofura";
                }
                if(escolhaComputador.equals("2")){
                    categoria = "Agilidade";
                }
                if(escolhaComputador.equals("3")){
                    categoria = "Agressividade";
                }
                if(escolhaComputador.equals("4")){
                    categoria = "Brincalhão";
                }
                if(escolhaComputador.equals("5")){
                    categoria = "Obediência";
                }
                System.out.println("Computador escolheu o atributo " + categoria);

                // Mostrar a carta do computador virada para cima
                String caminhoImagemMaquina = "/views/assets/cartas/" + cartaComputador.getNome() + ".png";
                try {
                    Image imagem = new Image(getClass().getResourceAsStream(caminhoImagemMaquina));
                    cartaImagemJogadorMaquina.setImage(imagem);
                } catch (Exception e) {
                    System.out.println("Erro ao carregar imagem: " + e.getMessage());
                    cartaImagemJogadorMaquina.setImage(null);
                }

                // Segundo atraso de 2 segundos para atualizar a interface
                PauseTransition delay2 = new PauseTransition(Duration.seconds(2));
                delay2.setOnFinished(e2 -> {
                    boolean jogadorRealVenceu = jogo.compararCategoria(escolhaComputador);
                    atualizarInterface();
                    if(!jogadorRealVenceu){
                        handleTurnoComputador();
                    }
                    if (jogo.verificarSeAcabou()) {
                        mostrarTelaFimDeJogo(jogo.isJogadorRealVencedor());
                    }
                });
                delay2.play();
            });
            delay1.play();
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

            String caminhoImagemMaquina = "/views/assets/cartas/verso.png";


            // Carrega a imagem correspondente
            try {
                Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
                cartaImagemJogadorReal.setImage(imagem);
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
                // Tratar exceção ao carregar a imagem
                cartaImagemJogadorReal.setImage(null);
            }
            try {
                Image imagem = new Image(getClass().getResourceAsStream(caminhoImagemMaquina));
                cartaImagemJogadorMaquina.setImage(imagem);
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
                // Tratar exceção ao carregar a imagem
                cartaImagemJogadorMaquina.setImage(null);
            }
        } else {
            // Se não há cartas na mão do jogador, define uma imagem padrão ou vazia
            cartaImagemJogadorMaquina.setImage(null);
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

            // Criar uma nova cena com o root carregado do FXML
            Scene scene = new Scene(root, 800, 600);

            // Criar um novo Stage para a cena
            Stage stage = new Stage();
            stage.setScene(scene);

            // Mostrar o Stage (janela)
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
