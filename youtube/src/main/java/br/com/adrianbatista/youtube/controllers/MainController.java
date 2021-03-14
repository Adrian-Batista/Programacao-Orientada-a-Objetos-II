package br.com.adrianbatista.youtube.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.adrianbatista.youtube.AlertUtil;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

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
	private Label lblVideoDescription;

	@FXML
	private ImageView imgUser;

	@FXML
	private Label lblUserInfo;

	@FXML
	private Button btnSaveVideo;

	@FXML
	private TextField txtNomeVideo;

	@FXML
	private TextField txtDescVideo;

	@FXML
	private TextField txtCatVideo;

	@FXML
	private TextField txtEmailUser;

	@FXML
	private TextField txtSenhaUser;

	@FXML
	private Button btnRemoveUser;

	@FXML
	private Label nomeUser;

	@FXML
	private Label lblQntVideos;
	
	@FXML
	private ListView<String> userFriendAddList;
	
	@FXML
	private ListView<String> userFriendRemoveList;
	
	@FXML
	private Button btnRemoveFriend;
	
	@FXML
	private Button btnAddFriend;


	// ------------------------------ TÍTULO -----------------------------------

	public void updateUserInfo(User u) {
		this.user = u;
		updateLibrary();
		if (!user.getUserImage().isBlank()) {
			Image image = new Image(user.getUserImage());
			imgUser.setImage(image);
		}
		lblUserInfo.setText("Seja Bem Vindo,  " + user.getUsername());
	}






	// ------------------------------------- Meus Vídeos --------------------------------------------------- 

	@FXML
	private List<String> updateLibrary() {
		if (user == null)
			return null;
		List<String> userVideos = new ArrayList<>();
		for (Video g : user.getVideos())
			userVideos.add(g.getName());
		userVideoList.setItems(FXCollections.observableArrayList(userVideos));

		if (user.getVideos().isEmpty()) {
			lblVideoDescription.setText("Você ainda não possui nenhum vídeo, acesse a loja e adicione a sua lista!");
			btnRemove.setDisable(true);
			btnPlay.setDisable(true);
		} else {
			userVideoList.getSelectionModel().select(0);
			btnRemove.setDisable(false);
			btnPlay.setDisable(false);
			updateDescription();
		}

		return userVideos;
	}

	@FXML
	private void updateDescription() {
		String videoName = userVideoList.getSelectionModel().getSelectedItem();
		Video video = new VideoDAO().get(videoName);
		lblVideoDescription.setText("Categoria: " + video.getDescription());
	}

	@FXML
	private void watch() {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("play"));
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	@FXML
	private void remove() {
		String videoName = userVideoList.getSelectionModel().getSelectedItem();
		Video video = new VideoDAO().get(videoName);
		user.getVideos().remove(video);
		new UserDAO().persist(user);
		updateLibrary();
	}

	private EventHandler<ActionEvent> salvarVideo() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button btn = (Button) event.getSource();
				String gameName = btn.getText();
				user.getVideos().add(new VideoDAO().get(gameName));
				new UserDAO().persist(user);
				updateVideosStore();
			}
		};
	}

	// ----------------------------------- Store ------------------------------

	@FXML
	private void updateVideosStore() {
		tileVideos.getChildren().clear();
		for (Video g : new VideoDAO().getAll())
			if (!user.getVideos().contains(g)) {
				Button btn = new Button(g.getName());
				btn.setOnAction(salvarVideo());
				tileVideos.getChildren().add(btn);
			}
	}

	// ------------------------------------------ Amigos ---------------------------------------
	
//	@FXML
//	private List<String> updateListFriend() {
//		if (user == null)
//			return null;
//		List<String> userFriends = new ArrayList<>();
//		for (Amigos g : user.getAmigos()) {
//			// montar um 
//			userFriends.add(g.getNome());
//		}
//		userFriendAddList.setItems(FXCollections.observableArrayList(userFriends));
//
//		if (!user.getAmigos().isEmpty()) {
//			userFriendAddList.getSelectionModel().select(0);
//			btnAddFriend.setDisable(false);
//			btnRemoveFriend.setDisable(false);
//			updateDescription();
//		}
//
//		return userFriends;
//	}
	
	@FXML
	private void addFriend() {

	}

	@FXML
	private void removeFriend() {


	}

	// --------------------------------------------- Perfil ---------------------------------------

	@FXML
	private void updateUser() {
		nomeUser.setText(user.getUsername());
		String qnt = String.valueOf(updateLibrary().size());
		lblQntVideos.setText(qnt);
	}

	@FXML
	private void removeUser() {
		UserDAO u = new UserDAO();
		u.remove(user);

		AlertUtil.info("Sucesso", "Sucesso", "Cadastro removido com sucesso").show();

		logout();
	}

	// -------------------------------------------- ADD Video ----------------------------------------
	@FXML
	private void addVideo() {

		String nome = txtNomeVideo.getText();
		String descricao = txtDescVideo.getText();
		String categoria = txtCatVideo.getText();

		if (nome.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o Título!", null);
			alert.showAndWait();
			return;
		}
		if (categoria.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a Categoria!", null);
			alert.showAndWait();
			return;
		}

		Video video = new Video(nome, descricao, categoria);
		new VideoDAO().persist(video);

		updateVideosStore();

		Alert alert = AlertUtil.info("Sucesso", "Novo Vídeo!", "Cadastro realizado com sucesso");
		alert.showAndWait();
		limpaTxtVideo();
	}

	@FXML
	private void limpaTxtVideo() {
		txtNomeVideo.setText("");
		txtDescVideo.setText("");
		txtCatVideo.setText("");
	}

	// --------------------------------------------- Exit -----------------------------------

	@FXML
	private void logout() {
		user = null;
		FXMLUtil.changeFlatbee(false);
		App.changeResizable();
		App.setRoot("login");
		App.centralized();
	}

	@FXML
	private void exit() {
		Platform.exit();
	}
}
