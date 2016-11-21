package ua.kas.main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

	@FXML
	Button refresh = new Button();

	@FXML
	TextField player, player1, player2;

	private String playerName = "";
	private String playerName1 = "";
	private String playerName2 = "";

	public static String vs = "";

	public int gameType = 0;
	// 0 - PvP
	// 1 - PvE

	public void PvP(ActionEvent e) throws IOException {
		playerName1 = player1.getText();
		playerName2 = player2.getText();
		gameType = 0;
		vs = playerName1 + " vs " + playerName2;
		Scene pvp = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
		pvp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage pvp_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		pvp_stage.setScene(pvp);
		pvp_stage.show();
	}

	public void PvE(ActionEvent e) throws IOException {
		playerName = player.getText();
		gameType = 1;
		vs = playerName + " vs Computer ";
		Scene pve = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
		pve.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage pve_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		pve_stage.setScene(pve);
		pve_stage.show();
	}
}
