package br.com.adrianbatista.youtube.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.adrianbatista.youtube.App;
import br.com.adrianbatista.youtube.FXMLUtil;
import br.com.adrianbatista.youtube.db.VideoDAO;
import br.com.adrianbatista.youtube.db.UserDAO;
import br.com.adrianbatista.youtube.entities.Video;
import br.com.adrianbatista.youtube.entities.User;
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
	private TilePane tileVideos;

	@FXML
	private ListView<String> userVideoList;

	@FXML
	private Button btnPlay;

	@FXML
	private Button btnRemove;

	@FXML
	private Label lblVideosDescription;

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
		String videoName = userVideoList.getSelectionModel().getSelectedItem();
		Video video = new VideoDAO().get(videoName);
		lblVideosDescription.setText(video.getDescription());
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
	private void updateVideosStore() {
		tileVideos.getChildren().clear();
		for (Video g : new VideoDAO().getAll())
			if (!user.getVideos().contains(g)) {
				Button btn = new Button(g.getName());
				btn.setOnAction(comprarVideo());
				tileVideos.getChildren().add(btn);
			}
	}

	@FXML
	private void updateLibrary() {
		if (user == null)
			return;
		List<String> userVideos = new ArrayList<>();
		for (Video g : user.getVideos())
			userVideos.add(g.getName());
		userVideoList.setItems(FXCollections.observableArrayList(userVideos));

		if (user.getVideos().isEmpty()) {
			lblVideosDescription.setText("Você ainda não possui nenhum jogo, compre hoje mesmo um jogo na nossa loja!");
			btnRemove.setDisable(true);
			btnPlay.setDisable(true);
		} else {
			userVideoList.getSelectionModel().select(0);
			btnRemove.setDisable(false);
			btnPlay.setDisable(false);
			updateDescription();
		}
	}

	@FXML
	private void remove() {
		String gameName = userVideoList.getSelectionModel().getSelectedItem();
		Video video = new VideoDAO().get(gameName);
		user.getVideos().remove(video);
		new UserDAO().persist(user);
		updateLibrary();
	}

	private EventHandler<ActionEvent> comprarVideo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button btn = (Button) event.getSource();
				String videoName = btn.getText();
				user.getVideos().add(new VideoDAO().get(videoName));
				new UserDAO().persist(user);
				updateVideosStore();
			}
		};
	}

}
