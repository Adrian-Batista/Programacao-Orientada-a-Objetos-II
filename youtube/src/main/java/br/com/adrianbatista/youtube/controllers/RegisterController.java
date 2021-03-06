package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.AlertUtil;
import br.com.adrianbatista.youtube.db.UserDAO;
import br.com.adrianbatista.youtube.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML 
	private Button btnReturn;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private PasswordField txtPassword;
	
	
	public void register() {
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		
		if(email.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "ERRO Digite o e-mail!", "", null);	
			alert.showAndWait();
			return;
		}
		
		if(password.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "ERRO Digite a senha!", "", null);	
			alert.showAndWait();
			return;
		}
		
		User u = new UserDAO().get(email);
		if(u != null) {
			Alert alert = AlertUtil.error("Erro!", "ERRO Email já em uso!", "", null);	
			alert.showAndWait();
			return;
		}
		new UserDAO().persist(new User(email, password));
		
		AlertUtil.info("Sucesso","Sucesso", "Cadastro Realizado com sucesso").show();
		voltar();
		
	}
	
	@FXML
	private void voltar(){
	    Stage stage = (Stage) btnReturn.getScene().getWindow();
	    stage.close();
	}

	@FXML
	private void fechar(){
	    Platform.exit();
	}

}
