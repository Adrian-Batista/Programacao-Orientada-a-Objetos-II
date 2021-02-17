package br.com.adrianbatista.youtube;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void logout() {
		App.setRoot("login");
    }
}
