package br.com.adrianbatista.youtube;

import javafx.fxml.FXML;

public class MainController {	
	
	
	@FXML
	private void logout() {
		App.setRoot("login");
		App.changeResizable();
		App.centralizar();
	}
	
	@FXML
	private void player() {
		App.setRoot("play");
		App.changeResizable();
		App.centralizar();
	}

   }

