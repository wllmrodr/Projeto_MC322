package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));

        // Configura o título da janela principal
        primaryStage.setTitle("Super Trunfo");

        // Define a cena com o root carregado do FXML
        primaryStage.setScene(new Scene(root, 1920, 1080)); // Ajusta tamanho conforme necessário

        // Exibe a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
