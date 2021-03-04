package br.com.adrianbatista.youtube.controllers;

import br.com.adrianbatista.youtube.App;
import javafx.fxml.FXML;

public class MainController {	
	
	
	@FXML
	private void logout() {
		App.setRoot("login");
		App.changeResizable();
		App.centralizar();
	}
	
	@FXML
	private void player() {
		App.setRoot("play");
		App.changeResizable();
		App.centralizar();
	}
	
	@FXML
	private void myVideos() {
		App.setRoot("myVideos");
		App.changeResizable();
		App.centralizar();
	}
	
	@FXML
	private void perfil() {
		App.setRoot("perfil");
		App.changeResizable();
		App.centralizar();
	}

   }

