package br.com.adrianbatista.youtube;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	
	@FXML
	private void register() {
        try {
        	Stage stage = new Stage();
    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
