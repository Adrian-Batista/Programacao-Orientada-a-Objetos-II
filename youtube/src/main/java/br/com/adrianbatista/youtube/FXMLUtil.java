package br.com.adrianbatista.youtube;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class FXMLUtil {

	public static Scene loadScene(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			return scene;
		} catch (IOException eIO) {
			Alert alert = ExceptionUtil.error("Erro", "Erro ao carregar um componente", "Ao tentar carregar a janela" + fxml, eIO);
			alert.showAndWait();
			return null;
		} catch (IllegalStateException eIllegalState) {
			Alert alert = ExceptionUtil.error("Erro", "Erro arquivo inexistente", "Ao tentar carregar a janela" + fxml, eIllegalState);
			alert.showAndWait();
			return null;
		}

	}

}
