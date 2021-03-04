package br.com.adrianbatista.youtube.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML 
	private Button btnReturn;
	
	@FXML
	private void voltar(){
	    Stage stage = (Stage) btnReturn.getScene().getWindow();
	    stage.close();
	}

	@FXML
	private void fechar(){
	    Stage stage = (Stage) btnReturn.getScene().getWindow();
	    stage.close();
	}

}
