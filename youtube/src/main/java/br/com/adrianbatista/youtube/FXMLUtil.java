package br.com.adrianbatista.youtube;

import java.io.IOException;

import br.com.adrianbatista.youtube.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class FXMLUtil {
	private static MainController mainController = null;
	private static boolean useFlatbee = false;

	public static Scene loadScene(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			if(useFlatbee)
				scene.getStylesheets().add("br/com/adrianbatista/youtube/flatbee.css");
			if (fxmlLoader.getController() instanceof MainController)
				mainController = fxmlLoader.getController();
			else
				mainController = null;
			return scene;
		} catch (IOException eIO) {
			Alert alert = AlertUtil.error("Erro", "Erro ao carregar um componente",
					"Erro ao tentar carregar a janela " + fxml, eIO);
			alert.showAndWait();
			return null;
		} catch (IllegalStateException eIllegalState) {
			Alert alert = AlertUtil.error("Erro", "Erro - Arquivo inexistente ",
					"Erro ao tentar carregar a janela " + fxml, eIllegalState);
			alert.showAndWait();
			return null;
		}
	}

	public static MainController getMainController() {
		return mainController;
	}
	
	public static void changeFlatbee(boolean use) {
		useFlatbee = use;
	}

}