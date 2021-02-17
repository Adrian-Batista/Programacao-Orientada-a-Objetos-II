package br.com.adrianbatista.youtube;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class FXMLUtil {
	
	public static Scene loadScene(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			return scene;
		}catch (IOException e) {
			System.err.println("Failed to load " + fxml + ".fxml");
			return null;
		}
		
	}

}
