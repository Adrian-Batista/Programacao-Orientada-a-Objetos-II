package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.App;
import javafx.fxml.FXML;

public class PerfilController {
	
	@FXML
	private void retornar() {
		App.setRoot("main");
		App.changeResizable();
		App.centralizar();
	}

}
