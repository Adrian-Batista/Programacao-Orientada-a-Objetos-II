package br.com.adrianbatista.youtube.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Video {
	@Id
	private String name;
	
	private String description;
	
	private int like;
	
	public Video() {
		
	}

	public Video(String name, String description, int like) {
		super();
		this.name = name;
		this.description = description;
		this.like = like;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}
	
	
}
