package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialização do controlador, se necessário
    }

    @FXML
    private void handleStartGame(ActionEvent event) {
        // Lógica para começar o jogo
        System.out.println("Iniciando o jogo...");

        // Exemplo: Carregar uma nova cena ou tela após iniciar o jogo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/jogo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
