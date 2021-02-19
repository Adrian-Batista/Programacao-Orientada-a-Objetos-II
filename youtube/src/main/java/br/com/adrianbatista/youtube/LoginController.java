package br.com.adrianbatista.youtube;

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
		App.changeResizable();
		App.setRoot("main");
		App.enlarge();
	}
	
	@FXML
	private void register() {
		Stage stage = new Stage();
	    stage.setScene(FXMLUtil.loadScene("register"));
	    stage.setResizable(false);
	    stage.show();
	}
	
	@FXML
	public void fechar(){
	    Stage stage = (Stage) idExit.getScene().getWindow();
	    stage.close();
	}
	
	

}
