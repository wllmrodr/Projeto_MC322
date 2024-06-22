package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class FimDeJogoController {

    @FXML
    private ImageView resultadoImagem;

    public void setImagemDeResultado(String caminhoImagem) {
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
            resultadoImagem.setImage(imagem);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
            resultadoImagem.setImage(null);
        }
    }

    @FXML
    private void handleVoltarInicio() {
        try {
            // Carregar o arquivo FXML da tela inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/inicio.fxml"));
            Parent root = loader.load();

            // Mostrar a nova cena
            Stage stage = (Stage) resultadoImagem.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
