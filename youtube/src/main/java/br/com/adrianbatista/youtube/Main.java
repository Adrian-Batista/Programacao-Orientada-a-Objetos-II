package br.com.adrianbatista.youtube;

import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		Application.launch(App.class);
		ConnDB.closeConn();
	}

}
