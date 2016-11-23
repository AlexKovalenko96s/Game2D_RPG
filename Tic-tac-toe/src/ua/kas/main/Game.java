package ua.kas.main;

import java.net.URL;
import java.util.Random;
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

	private int x = 0;
	private int y = 0;
	private int score1 = 0;
	private int score2 = 0;
	private int count = 0;
	private int countComputer = 0;
	private int countUser = 0;

	private String playerChar1 = "✖";
	private String playerChar2 = "✔";
	private String lastChar = "char";

	Random random = new Random();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new Button();
				grid.add(buttons[i][j], i, j);
			}
		}
		vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);

		if (Controller.gameType == 0) {
			click();
		} else {
			clickVsComputer();
		}
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
							if (lastChar.equals(playerChar1)) {
								score1++;
							} else {
								score2++;
							}
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
	}

	private boolean check(String lastChar) {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText() == lastChar && buttons[i][1].getText() == lastChar
					&& buttons[i][2].getText() == lastChar) {
				return true;
			}
		}

		for (int j = 0; j < 3; j++) {
			if (buttons[0][j].getText() == lastChar && buttons[1][j].getText() == lastChar
					&& buttons[2][j].getText() == lastChar) {
				return true;
			}
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

	public void clickVsComputer() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				final Button myButton = buttons[i][j];
				myButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if ("".equals(myButton.getText())) {
							myButton.setText(playerChar1);
							count++;
							if (check(playerChar1)) {
								JOptionPane.showMessageDialog(null, "Win " + playerChar1);
								score1++;
								vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);
								for (int k = 0; k < buttons.length; k++) {
									for (int l = 0; l < buttons[k].length; l++) {
										buttons[k][l].setDisable(true);
									}
								}
							}
							if (!ai() && count != 9) {
								x = random.nextInt(3);
								y = random.nextInt(3);
								while (!buttons[x][y].getText().equals("")) {
									x = random.nextInt(3);
									y = random.nextInt(3);
								}
								count++;
								buttons[x][y].setText(playerChar2);
							}
							if (check(playerChar2)) {
								JOptionPane.showMessageDialog(null, "Win " + playerChar2);
								score2++;
								vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);
								for (int k = 0; k < buttons.length; k++) {
									for (int l = 0; l < buttons[k].length; l++) {
										buttons[k][l].setDisable(true);
									}
								}
							}
							if (count == 9) {
								JOptionPane.showMessageDialog(null, "Draw!");
							}
						}
					}
				});
			}
		}
	}

	private boolean ai() {
		for (int i = 0; i < 3; i++) {
			countComputer = 0;
			countUser = 0;
			if (buttons[i][0].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[i][0].getText().equals("")) {
				countUser++;
			}

			if (buttons[i][1].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[i][1].getText().equals("")) {
				countUser++;
			}

			if (buttons[i][2].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[i][2].getText().equals("")) {
				countUser++;
			}

			if (countComputer == 2 && countUser == 1) {
				computerTurn(i, "col");
				return true;
			}
		}

		for (int j = 0; j < 3; j++) {
			countComputer = 0;
			countUser = 0;
			if (buttons[0][j].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[0][j].getText().equals("")) {
				countUser++;
			}

			if (buttons[1][j].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[1][j].getText().equals("")) {
				countUser++;
			}

			if (buttons[2][j].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[2][j].getText().equals("")) {
				countUser++;
			}

			if (countComputer == 2 && countUser == 1) {
				computerTurn(j, "row");
				return true;
			}
		}

		for (int j = 0; j < 1; j++) {
			countComputer = 0;
			countUser = 0;
			if (buttons[0][2].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[0][2].getText().equals("")) {
				countUser++;
			}

			if (buttons[1][1].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[1][1].getText().equals("")) {
				countUser++;
			}

			if (buttons[2][0].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[2][0].getText().equals("")) {
				countUser++;
			}

			if (countComputer == 2 && countUser == 1) {
				computerTurn("right");
				return true;
			}
		}

		countComputer = 0;
		countUser = 0;

		for (int j = 0; j < 3; j++) {
			if (buttons[j][j].getText().equals(playerChar1)) {
				countComputer++;
			} else if (buttons[j][j].getText().equals("")) {
				countUser++;
			}
		}
		if (countComputer == 2 && countUser == 1) {
			computerTurn("left");
			return true;
		}
		return false;
	}

	private void computerTurn(int n, String str) {
		count++;
		if (str.equals("col")) {
			for (int i = 0; i < 3; i++) {
				if (buttons[n][i].getText().equals("")) {
					buttons[n][i].setText(playerChar2);
					return;
				}
			}
		} else if (str.equals("row")) {
			for (int i = 0; i < 3; i++) {
				if (buttons[i][n].getText().equals("")) {
					buttons[i][n].setText(playerChar2);
					return;
				}
			}
		}
	}

	private void computerTurn(String str) {
		count++;
		if (str.equals("left")) {
			for (int i = 0; i < 3; i++) {
				if (buttons[i][i].getText().equals("")) {
					buttons[i][i].setText(playerChar2);
					return;
				}
			}
		} else if (str.equals("right")) {
			if (buttons[0][2].getText().equals("")) {
				buttons[0][2].setText(playerChar2);
				lastChar = playerChar2;
				return;
			} else if (buttons[1][1].getText().equals("")) {
				buttons[1][1].setText(playerChar2);
				lastChar = playerChar2;
				return;
			} else if (buttons[2][0].getText().equals("")) {
				buttons[2][0].setText(playerChar2);
				return;
			}
		}
	}
}
