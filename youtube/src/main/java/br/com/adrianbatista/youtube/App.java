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

import br.com.adrianbatista.youtube.db.UtilDB;
import br.com.adrianbatista.youtube.entities.User;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage){
    	this.stage = stage;
        stage.setScene(FXMLUtil.loadScene("login"));
        this.centralizar();
        this.changeResizable();
        stage.show();
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