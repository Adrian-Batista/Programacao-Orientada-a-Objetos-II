package br.com.adrianbatista.youtube;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Stage stage;
	private static Thread connection;
	private static Thread FileToDB;


	@Override
	public void start(Stage stge) {
		stage = stge;
		stage.setScene(FXMLUtil.loadScene("login"));
		changeResizable();
		stage.setTitle("YouTube");
		stage.show();
		
		FileToDB.start();
		connection.start();
	}
	
	public static void setFileToDB(Thread FileToDB) {
		App.FileToDB = FileToDB;
	}

	public static void setConnection(Thread connection) {
		App.connection = connection;
	}

	public static void setRoot(String fxml) {
		stage.setScene(FXMLUtil.loadScene(fxml));
	}

	public static void changeResizable() {
		if (stage.isResizable())
			stage.setResizable(false);
		else
			stage.setResizable(true);
	}
	
	public static void centralized() {
		stage.centerOnScreen();
	}
	
	public static void fullScreen(Boolean opc) {
		stage.setMaximized(opc);
	}

}