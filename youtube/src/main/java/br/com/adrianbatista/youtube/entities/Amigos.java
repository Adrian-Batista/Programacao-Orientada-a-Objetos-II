package br.com.adrianbatista.youtube.entities;

public class Amigos {

	private User amigo;

	public Amigos() {

	}

	public Amigos(User amigo) {
		super();
		this.amigo = amigo;
	}

	public User getAmigo() {
		return amigo;
	}

	public void setAmigo(User amigo) {
		this.amigo = amigo;
	}

}
