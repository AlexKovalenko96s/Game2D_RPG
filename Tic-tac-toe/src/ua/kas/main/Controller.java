package ua.kas.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller implements Initializable {

	@FXML
	GridPane grid = new GridPane();
	@FXML
	Button refresh = new Button();
	@FXML
	Button[][] buttons = new Button[3][3];

	@FXML
	Label label21 = new Label();

	@FXML
	TextField player, player1, player2;

	private int score1 = 0;
	private int score2 = 0;
	private int scoreComp = 0;
	private int count = 0;
	private int gameType = 0;
	// 0 - PvP
	// 1 - PvE

	private String playerChar1 = "✖";
	private String playerChar2 = "✔";
	private String playerName = "";
	private String playerName1 = "";
	private String playerName2 = "";
	private String lastChar = "char";
	private String vs = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new Button();
				grid.add(buttons[i][j], i, j);
			}
		}
		click();
	}

	public void click() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				final Button myButton = buttons[i][j];
				myButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						count++;
						count = count % 2;
						if (count == 0 && "".equals(myButton.getText())) {
							myButton.setText(playerChar2);
							lastChar = playerChar2;
						} else if (count == 1 && "".equals(myButton.getText())) {
							myButton.setText(playerChar1);
							lastChar = playerChar1;
						}
						if (check(lastChar)) {
							System.out.println("Win " + lastChar);
						}
					}
				});
			}
		}
	}

	public boolean check(String lastChar) {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText() == lastChar && buttons[i][1].getText() == lastChar
					&& buttons[i][2].getText() == lastChar)
				return true;
		}

		for (int j = 0; j < 3; j++) {
			if (buttons[0][j].getText() == lastChar && buttons[1][j].getText() == lastChar
					&& buttons[2][j].getText() == lastChar)
				return true;
		}

		if (buttons[0][0].getText() == lastChar && buttons[1][1].getText() == lastChar
				&& buttons[2][2].getText() == lastChar) {
			return true;
		}

		if (buttons[0][2].getText() == lastChar && buttons[1][1].getText() == lastChar
				&& buttons[2][0].getText() == lastChar) {
			return true;
		}
		return false;
	}

	public void PvP(ActionEvent e) throws IOException {
		Scene pvp = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
		pvp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage pvp_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		pvp_stage.setScene(pvp);
		pvp_stage.show();

		playerName1 = player1.getText();
		playerName2 = player2.getText();
		gameType = 0;
		vs = playerName1 + " vs " + playerName2 + " " + score1 + ":" + score2;
		label21.setText(vs);
		System.out.println(vs);
	}

	public void PvE(ActionEvent e) throws IOException {
		Scene pve = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
		pve.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage pve_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		pve_stage.setScene(pve);
		pve_stage.show();

		playerName = player.getText();
		gameType = 1;
		vs = playerName + " vs Computer " + score1 + ":" + scoreComp;
		label21.setText(vs);
	}
}
