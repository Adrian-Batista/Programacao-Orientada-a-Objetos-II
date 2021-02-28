package br.com.adrianbatista.youtube;

import javafx.fxml.FXML;

public class PerfilController {
	
	@FXML
	private void retornar() {
		App.setRoot("main");
		App.changeResizable();
		App.centralizar();
	}

}
