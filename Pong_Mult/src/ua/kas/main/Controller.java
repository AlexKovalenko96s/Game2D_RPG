package ua.kas.main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	TextField address;

	public void host() {
		address.setDisable(false);
		PongGame.address = address.getText();
		PongGame.server = true;
		PongGame game = new PongGame();

		try {
			game.start(Main.primaryStage);
			Main.primaryStage.centerOnScreen();
		} catch (Exception ex) {
		}
	}

	public void play() {
		address.setDisable(true);
		PongGame.address = address.getText();
		PongGame.server = false;
		PongGame game = new PongGame();

		try {
			game.start(Main.primaryStage);
			Main.primaryStage.centerOnScreen();
		} catch (Exception ex) {
		}
	}
}
