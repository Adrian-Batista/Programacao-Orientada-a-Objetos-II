package br.com.adrianbatista.youtube;

import javafx.fxml.FXML;

public class MainController {
	
	
	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
		
	}

    }

