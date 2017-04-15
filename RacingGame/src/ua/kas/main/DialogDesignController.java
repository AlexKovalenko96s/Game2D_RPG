package ua.kas.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class DialogDesignController implements Initializable {

	@FXML
	Button continuebtn;
	@FXML
	ToggleButton playbtn;
	@FXML
	ToggleButton hostbtn;
	@FXML
	TextField address;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// host
		playbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				playbtn.setSelected(true);
				hostbtn.setSelected(false);
				address.setDisable(false);
				continuebtn.setDisable(false);
			}
		});
		// player
		hostbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				hostbtn.setSelected(true);
				playbtn.setSelected(false);
				address.setDisable(true);
				continuebtn.setDisable(false);
			}
		});

		// start
		continuebtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				RacingGame.address = address.getText();
				RacingGame.server = hostbtn.isSelected();
				RacingGame game = new RacingGame();

				try {
					game.start(OptionDialog.window);
					OptionDialog.window.centerOnScreen();
				} catch (Exception ex) {
				}
			}
		});
	}
}