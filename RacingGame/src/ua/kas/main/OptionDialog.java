package ua.kas.main;

// enter the game

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class OptionDialog extends Application {

	static Stage window;

	@Override
	public void start(Stage window) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("DialogDesign.fxml"));
		Scene scene = new Scene(root, 343 - 10, 206 - 5);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setTitle("Racing Game");
		window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		window.setScene(scene);
		window.show();
		OptionDialog.window = window;

	}

	public static void main(String[] args) {
		launch(args);
	}
}