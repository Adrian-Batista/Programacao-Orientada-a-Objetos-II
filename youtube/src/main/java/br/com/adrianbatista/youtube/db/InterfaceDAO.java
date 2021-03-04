package br.com.adrianbatista.youtube.db;

import java.util.List;

public interface InterfaceDAO<T> {

	public void persist(T referencia);

	public void remove(T referencia);

	public List<T> getAll();

}
