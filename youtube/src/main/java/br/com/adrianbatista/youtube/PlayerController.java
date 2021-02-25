package br.com.adrianbatista.youtube;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.;

public class PlayerController {
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnPause;
	
	
	String VUrl = "file:\C:\Users\adria\OneDrive\Documentos\GitHub\Programacao-Orientada-a-Objetos-II\youtube\videos\Pexels Videos 1394254.mp4"
	Media media = new Media(VUrl);
	MediaPlayer mediaplayer = new MediaPlayer(media);
	
	
	private void Play() {
		
	}
	
	private void Pause() {
		
	}

}
