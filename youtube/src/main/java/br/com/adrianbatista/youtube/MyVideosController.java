package br.com.adrianbatista.youtube;

import javafx.fxml.FXML;

public class MyVideosController {
	
	@FXML
	private void voltar() {
		App.setRoot("main");
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
