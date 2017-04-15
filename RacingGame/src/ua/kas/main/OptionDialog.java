package ua.kas.main;

// enter the game

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionDialog extends Application {

	static Stage window;

	@Override
	public void start(Stage window) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DialogDesign.fxml"));
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.show();
		OptionDialog.window = window;

	}

	public static void main(String[] args) {
		launch(args);
	}
}