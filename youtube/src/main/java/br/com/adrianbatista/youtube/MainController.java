package br.com.adrianbatista.youtube;

import javafx.fxml.FXML;

public class MainController {	
	
	
	@FXML
	private void logout() {
		App.setRoot("login");
		App.changeResizable();
	}
	
	@FXML
	private void player() {
		App.setRoot("play");
		App.changeResizable();
	}

   }

