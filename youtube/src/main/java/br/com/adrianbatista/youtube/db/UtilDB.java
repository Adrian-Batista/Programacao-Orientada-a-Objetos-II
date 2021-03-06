package br.com.adrianbatista.youtube.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.adrianbatista.youtube.entities.User;
import br.com.adrianbatista.youtube.entities.Video;
import javafx.scene.control.Alert;

public class UtilDB {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if(entityManager == null)
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}
	
	private static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("teste");
		return entityManagerFactory;
	}
	
	public static void closeConn() {
		if(entityManager != null)
			entityManager.close();
		if(entityManagerFactory != null)
			entityManagerFactory.close();
	}
	
	public static void initDB() {

		for (User u : consumeAPI(consultAPI()))
			new UserDAO().persist(u);

		User u = new User("admin", "teste");
		new UserDAO().persist(u);
		
		new VideoDAO().persist(new Video("Primeiro Video", "Este é o primeiro video", 10));
		new VideoDAO().persist(new Video("Segundo Video", "Este é o segundo video", 15));
		
	}

	public static List<User> consumeAPI(List<String> users) {
		List<User> result = new ArrayList<User>();
		for (int lineIndex = 0; lineIndex < users.size(); lineIndex++) {
			String line = users.get(lineIndex);
			if (line.contains("username")) {
				String username = processJSONLine(line);
				lineIndex++;
				line = users.get(lineIndex);
				String password = processJSONLine(line);
				User user = new User(username, password);
				result.add(user);
			}
		}
		return result;
	}

	private static String processJSONLine(String line) {
		String[] dividedLine = line.split(":");
		String username = dividedLine[1];
		username = username.replace(",", " ");
		username = username.replace("\"", " ");
		username = username.trim();
		return username;
	}

	private static List<String> consultAPI() {
		List<String> result = new ArrayList<>();
		try {
			URL url = new URL("http://www.lucasbueno.com.br/steam.json");
			URLConnection uc = url.openConnection();
			InputStreamReader input = new InputStreamReader(uc.getInputStream());
			BufferedReader in = new BufferedReader(input);
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				result.add(inputLine);

			in.close();
		} catch (Exception e) {
			//Alert alert = AlertUtil.error("Erro", "Erro ao consumir a API!", "Erro ao consumir a API!", e);
			//alert.showAndWait();
		}
		return result;
	}

}
