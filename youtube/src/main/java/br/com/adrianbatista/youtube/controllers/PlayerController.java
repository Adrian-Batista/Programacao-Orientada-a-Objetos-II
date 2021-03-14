package br.com.adrianbatista.youtube.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayerController {
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnPause;
	
	@FXML
	private Button btnReturn;
	
	@FXML
	private void retornar() {
		Stage stage = (Stage) btnPlay.getScene().getWindow();
		stage.close();
	}

}
