package br.com.adrianbatista.youtube.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.adrianbatista.youtube.App;
import br.com.adrianbatista.youtube.FXMLUtil;
import br.com.adrianbatista.youtube.db.UserDAO;
import br.com.adrianbatista.youtube.db.VideoDAO;
import br.com.adrianbatista.youtube.entities.User;
import br.com.adrianbatista.youtube.entities.Video;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MainController {

	private User user;

	@FXML
	private TilePane tileGames;

	@FXML
	private ListView<String> userGameList;

	@FXML
	private Button btnPlay;

	@FXML
	private Button btnRemove;

	@FXML
	private Label lblGameDescription;

	@FXML
	private ImageView imgUser;

	@FXML
	private Label lblUserInfo;

	public void updateUserInfo(User u) {
		this.user = u;
		updateLibrary();
		if (!user.getUserImage().isBlank()) {
			Image image = new Image(user.getUserImage());
			imgUser.setImage(image);
		}
		lblUserInfo.setText("Olá " + user.getUsername());
	}

	@FXML
	private void updateDescription() {
		String videoName = userGameList.getSelectionModel().getSelectedItem();
		Video video = new VideoDAO().get(videoName);
		lblGameDescription.setText(video.getDescription());
	}

	@FXML
	private void perfil() {
		App.setRoot("perfil");
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
	private void logout() {
		user = null;
		FXMLUtil.changeFlatbee(false);
		App.changeResizable();
		App.setRoot("login");
	}

	@FXML
	private void exit() {
		Platform.exit();
	}

	@FXML
	private void updateGamesStore() {
		tileGames.getChildren().clear();
		for (Video g : new VideoDAO().getAll())
			if (!user.getVideos().contains(g)) {
				Button btn = new Button(g.getName());
				btn.setOnAction(comprarJogo());
				tileGames.getChildren().add(btn);
			}
	}

	@FXML
	private void updateLibrary() {
		if (user == null)
			return;
		List<String> userGames = new ArrayList<>();
		for (Video g : user.getVideos())
			userGames.add(g.getName());
		userGameList.setItems(FXCollections.observableArrayList(userGames));

		if (user.getVideos().isEmpty()) {
			lblGameDescription.setText("Você ainda não possui nenhum jogo, compre hoje mesmo um jogo na nossa loja!");
			btnRemove.setDisable(true);
			btnPlay.setDisable(true);
		} else {
			userGameList.getSelectionModel().select(0);
			btnRemove.setDisable(false);
			btnPlay.setDisable(false);
			updateDescription();
		}
	}

	@FXML
	private void remove() {
		String gameName = userGameList.getSelectionModel().getSelectedItem();
		Video game = new VideoDAO().get(gameName);
		user.getVideos().remove(game);
		new UserDAO().persist(user);
		updateLibrary();
	}

	private EventHandler<ActionEvent> comprarJogo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button btn = (Button) event.getSource();
				String gameName = btn.getText();
				user.getVideos().add(new VideoDAO().get(gameName));
				new UserDAO().persist(user);
				updateGamesStore();
			}
		};
	}

}
