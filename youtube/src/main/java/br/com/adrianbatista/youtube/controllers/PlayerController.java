package br.com.adrianbatista.youtube.controllers;


import br.com.adrianbatista.youtube.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayerController {
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnPause;
	
	@FXML
	private Button btnReturn;
	
	@FXML
	private void retornar() {
		App.setRoot("main");
		App.centralizar();
	}

}
