package br.com.adrianbatista.youtube;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void logout() {
        try {
			App.setRoot("login");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a Janela de Login!");
		}
    }
}