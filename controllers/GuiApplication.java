package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));

        // Configura o título da janela principal
        primaryStage.setTitle("Super Trunfo");

        Image icon = new Image("views/assets/icon.jpg");
        primaryStage.getIcons().add(icon);

        // Define a cena com o root carregado do FXML e um tamanho inicial razoável
        primaryStage.setScene(new Scene(root, 800, 600)); // Ajusta tamanho conforme necessário

        // Deixa em tela cheia
        primaryStage.setFullScreen(true);

        // Exibe a janela
        primaryStage.show();
    }
}
