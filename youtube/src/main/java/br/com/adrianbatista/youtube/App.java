package br.com.adrianbatista.youtube;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage){
    	
    	List<String> users = consultAPI();
    	consumeAPI(users);
    	
    	this.stage = stage;
        stage.setScene(FXMLUtil.loadScene("login"));
        this.centralizar();
        this.changeResizable();
        
        stage.show();
    }
    
    //-------------------------------------------------------
    
    private void consumeAPI(List<String>users) {
    	for(int lineIndex = 0; lineIndex < users.size(); lineIndex++ ) {
    		String line = users.get(lineIndex);
    		if(line.contains("username")) {
    			// processamos o nome de Usuario
    			String username = processJSONLine(line);
    			System.out.println(username);
    			
    			// vamos para a proxima linha
    			lineIndex++;
    			line = users.get(lineIndex);
    			
    			// processamos o password
    			String password = processJSONLine(line);
    			System.out.println(password);
    			
    			User user = new User(username, password);
    			
    			EntityManager em = ConnDB.getEntityManager();
    			em.getTransaction().begin();
    			em.persist(user);
    			em.getTransaction().commit();
    			em.close();
    			ConnDB.closeConn();
    		}
    	}
    }

	private String processJSONLine(String line) {
		String[] dividedLine = line.split(":");
		String username = dividedLine[1];
		username = username.replace(",", " ");
		username = username.replace("\"", " ");
		username = username.trim();
		return username;
	}
    
    private List<String> consultAPI(){
    	List<String> result = new ArrayList<>();
    	try {
    		URL url = new URL("https://lucasbueno.com.br/steam.json");
    		URLConnection uc = url.openConnection();
    		InputStreamReader input = new InputStreamReader(uc.getInputStream());
    		BufferedReader in = new BufferedReader(input);
    		String inputLine;
    		
    		while((inputLine = in.readLine()) != null)
    			result.add(inputLine);
    		
    		in.close();
    	}catch(Exception e) {
    		Alert alert = ExceptionUtil.error("Erro", "Erro ao consumir a API!", "Erro ao consumir a API!", e);
    		alert.showAndWait();
    		
    	}
    	return result;
    }

    public static void setRoot(String fxml) {
        stage.setScene(FXMLUtil.loadScene(fxml));
    }
    
    public static void changeResizable() {
    	if(stage.isResizable()) {
    		stage.setResizable(true);
    	}else {
    		stage.setResizable(false);
    	}
    		
    }
    
    public static void centralizar() {
    	stage.centerOnScreen();
    }
    
    public static void enlarge() {
    	if(stage.isMaximized()) {
    		stage.setMaximized(false);
    		stage.centerOnScreen();
    	}
    		
    	else {
    		stage.setMaximized(true);
    		stage.setMaximized(false);
    	}
    		
    }
 
}