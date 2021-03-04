package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.App;
import br.com.adrianbatista.youtube.FXMLUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button idExit;
	
	
	@FXML
	private void login() {
		App.setRoot("main");
		App.changeResizable();
		App.centralizar();
	}
	
	@FXML
	private void register() {
		Stage stage = new Stage();
	    stage.setScene(FXMLUtil.loadScene("register"));
	    stage.setResizable(true);
	    stage.show();
	}
	
	@FXML
	public void fechar(){
	    Stage stage = (Stage) idExit.getScene().getWindow();
	    stage.close();
	}
	
	

}
