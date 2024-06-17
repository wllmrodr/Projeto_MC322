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
        System.out.println("Iniciando o jogo...");

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        try {
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene onto the stage
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any potential loading errors here
        }
    }
}