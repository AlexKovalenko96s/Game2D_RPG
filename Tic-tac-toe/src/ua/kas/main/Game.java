package ua.kas.main;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Game implements Initializable {

	@FXML
	GridPane grid = new GridPane();

	@FXML
	Button[][] buttons = new Button[3][3];

	@FXML
	Label vsLabel = new Label("");

	private int score1 = 0;
	private int score2 = 0;
	private int count = 0;

	private String playerChar1 = "✖";
	private String playerChar2 = "✔";
	private String lastChar = "char";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new Button();
				grid.add(buttons[i][j], i, j);
			}
		}
		vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);
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
							JOptionPane.showMessageDialog(null, "Win " + lastChar);
							vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);
							for (int k = 0; k < buttons.length; k++) {
								for (int l = 0; l < buttons[k].length; l++) {
									buttons[k][l].setDisable(true);
								}
							}
						}
					}
				});
			}
		}
	}

	public void restart() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].setText("");
				buttons[i][j].setDisable(false);
			}
		}
		count = 0;
		click();
	}

	private boolean check(String lastChar) {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText() == lastChar && buttons[i][1].getText() == lastChar
					&& buttons[i][2].getText() == lastChar) {
				if (lastChar.equals(playerChar1)) {
					score1++;
				} else {
					score2++;
				}
				return true;
			}
		}

		for (int j = 0; j < 3; j++) {
			if (buttons[0][j].getText() == lastChar && buttons[1][j].getText() == lastChar
					&& buttons[2][j].getText() == lastChar) {
				if (lastChar.equals(playerChar1)) {
					score1++;
				} else {
					score2++;
				}
				return true;
			}
		}

		if (buttons[0][0].getText() == lastChar && buttons[1][1].getText() == lastChar
				&& buttons[2][2].getText() == lastChar) {
			if (lastChar.equals(playerChar1)) {
				score1++;
			} else {
				score2++;
			}
			return true;
		}

		if (buttons[0][2].getText() == lastChar && buttons[1][1].getText() == lastChar
				&& buttons[2][0].getText() == lastChar) {
			if (lastChar.equals(playerChar1)) {
				score1++;
			} else {
				score2++;
			}
			return true;
		}
		return false;
	}
}
