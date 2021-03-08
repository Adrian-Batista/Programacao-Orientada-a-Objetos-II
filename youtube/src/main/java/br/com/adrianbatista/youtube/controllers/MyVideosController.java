package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.App;
import javafx.fxml.FXML;

public class MyVideosController {
	
	@FXML
	private void voltar() {
		App.setRoot("main");
		App.changeResizable();
	}
	
	@FXML
	private void player() {
		App.setRoot("play");
		App.changeResizable();
	}

}
