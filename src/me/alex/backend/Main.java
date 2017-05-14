package me.alex.backend;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.alex.control.AnimationViewer;

public class Main extends Application {
	
	private Stage stage;
	private static Main instance;

	public Main() {
		if (instance != null) {
			throw new UnsupportedOperationException("I just wanna be a singleton :c");
		}
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage) {
		this.stage = primaryStage; 
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/me/alex/visual/CreateAnimation.fxml"));
			Scene scene = new Scene(root, 600, 730);
			this.stage.setScene(scene);
			this.stage.show();
			this.stage.setTitle("Animation!");
		} catch (IOException e) {
			System.out.println("io ex");
		}
	}
	
	public void switchScene(String location, int width, int height) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(location));
			this.stage.setScene(new Scene(root, width, height));
		} catch (IOException e) {
			System.out.println("IO ex when changing location");
		}
	}
	
	@Override
	public void stop() {
		AnimationViewer anim = AnimationViewer.getPrimaryInstance();
		if (anim != null) {
			anim.setRunning(false);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
