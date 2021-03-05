package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.App;
import br.com.adrianbatista.youtube.ExceptionUtil;
import br.com.adrianbatista.youtube.FXMLUtil;
import br.com.adrianbatista.youtube.db.UserDAO;
import br.com.adrianbatista.youtube.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button idExit;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private void login() {
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		
		if(email.isBlank()) {
			Alert alert = ExceptionUtil.error("Erro!", "ERRO Digite o e-mail!", "", null);	
			alert.showAndWait();
			return;
		}
		
		if(password.isBlank()) {
			Alert alert = ExceptionUtil.error("Erro!", "ERRO Digite a senha!", "", null);	
			alert.showAndWait();
			return;
		}
		
		User u = new UserDAO().get(email);
		if(u == null) {
			Alert alert = ExceptionUtil.error("Erro!", "ERRO Usuário ou senha invalido(s)!", "", null);	
			alert.showAndWait();
			return;
		}
		
		if(!u.getPassword().contentEquals(password)) {
			Alert alert = ExceptionUtil.error("Erro!", "ERRO Usuário ou senha invalido(s)!", "", null);	
			alert.showAndWait();
			return;
		}
		
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
