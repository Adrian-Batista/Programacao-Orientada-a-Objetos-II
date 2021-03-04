package br.com.adrianbatista.youtube;

import br.com.adrianbatista.youtube.db.UtilDB;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		UtilDB.initDB();
		Application.launch(App.class);
		UtilDB.closeConn();
	}

}
