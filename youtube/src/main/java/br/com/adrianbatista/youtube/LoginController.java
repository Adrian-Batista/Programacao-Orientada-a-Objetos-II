package br.com.adrianbatista.youtube;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {
	@FXML
	private Button btnLogin;
	
	@FXML
	private void login() {
		try {
			App.setRoot("main");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a Janela Principal!");
		}
			
	}

}