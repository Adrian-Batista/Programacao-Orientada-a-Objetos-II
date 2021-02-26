package br.com.adrianbatista.youtube;


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
		App.changeResizable();
	}

}
