package br.com.adrianbatista.youtube.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	private String username;

	private String password;

	private String userImage;

	private boolean saveLogin;

	@ManyToMany
	private List<Video> videos;

	public User() {
	}

	public User(String username, String password, String userImage) {
		super();
		this.username = username;
		this.password = password;
		this.userImage = userImage;
		this.saveLogin = true;
		this.videos = new ArrayList<>();
	}

	public boolean isSaveLogin() {
		return saveLogin;
	}

	public void setSaveLogin(boolean saveLogin) {
		this.saveLogin = saveLogin;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}