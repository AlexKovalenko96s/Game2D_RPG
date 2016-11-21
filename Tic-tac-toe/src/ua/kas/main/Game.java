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
	private int countComputer = 0;
	private int countUser = 0;

	private String playerChar1 = "✖";
	private String playerChar2 = "✔";
	private String lastChar = "char";

	private boolean turn = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new Button();
				grid.add(buttons[i][j], i, j);
			}
		}
		vsLabel.setText(Controller.vs + " " + score1 + " : " + score2);
		// click();
		clickVsComputer();
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

	public void clickVsComputer() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				final Button myButton = buttons[i][j];
				myButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if (count == 0 && "".equals(myButton.getText())) {
							myButton.setText(playerChar1);
							lastChar = playerChar1;
							turn = false;
						}
					}
				});
			}
		}
		for (int i = 0; i < 9; i++) {
			count = count % 2;
			if (count == 0 && turn) {
				count++;
			} else if (count == 1 && !turn) {
				if (!ai()) {
					buttons[2][2].setText(playerChar2);

				}
				count++;
			}
		}
	}

	private boolean ai() {
		for (int i = 0; i < 3; i++) {
			if (buttons[i][0].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[i][0].getText() == playerChar2) {
				countUser++;
			}

			if (buttons[i][1].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[i][1].getText() == playerChar2) {
				countUser++;
			}

			if (buttons[i][2].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[i][2].getText() == playerChar2) {
				countUser++;
			}

			if (countComputer == 2 && countUser == 1) {
				computerTurn(i, "row");
				return true;
			}
			countComputer = 0;
			countUser = 0;
		}

		for (int j = 0; j < 3; j++) {
			if (buttons[0][j].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[0][j].getText() == playerChar2) {
				countUser++;
			}

			if (buttons[0][j].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[0][j].getText() == playerChar2) {
				countUser++;
			}

			if (buttons[0][j].getText() == playerChar1) {
				countComputer++;
			} else if (buttons[0][j].getText() == playerChar2) {
				countUser++;
			}

			if (countComputer == 2 && countUser == 1) {
				computerTurn(j, "col");
				return true;
			}
			countComputer = 0;
			countUser = 0;
		}

		// if (buttons[0][0].getText() == lastChar && buttons[1][1].getText() ==
		// lastChar
		// && buttons[2][2].getText() == lastChar) {
		// if (lastChar.equals(playerChar1)) {
		// score1++;
		// } else {
		// score2++;
		// }
		// return true;
		// }
		//
		// if (buttons[0][2].getText() == lastChar && buttons[1][1].getText() ==
		// lastChar
		// && buttons[2][0].getText() == lastChar) {
		// if (lastChar.equals(playerChar1)) {
		// score1++;
		// } else {
		// score2++;
		// }
		// return true;
		// }
		return false;
	}

	private void computerTurn(int n, String str) {
		if (str.equals("row")) {
			for (int i = 0; i < 3; i++) {
				if (buttons[n][i].getText() == "") {
					buttons[n][i].setText(playerChar2);
					return;
				}
			}
		} else if (str.equals("col")) {
			for (int i = 0; i < 3; i++) {
				if (buttons[i][n].getText() == "") {
					buttons[i][n].setText(playerChar2);
					return;
				}
			}
		}
	}

}
