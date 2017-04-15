package ua.kas.main;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

// status text

public class StatusUpdater extends Pane {

	Text status = new Text();

	public StatusUpdater(double x, double y) {
		setTranslateX(x);
		setTranslateY(y);
		setStyle("-fx-background-color: #CCC;" + "-fx-background-radius:20px");
		setPrefHeight(70);
		setPrefWidth(300);
		setOpacity(0);
		status.setTranslateY(40);
		status.setTranslateX(50);

		getChildren().addAll(status);
		status.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 17));
		status.setFill(new Color(107 / 255.0, 162 / 255.0, 252 / 255.0, 1.0));
	}

	public void setTextAndAnimate(String message) {
		status.setText(message);
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(0.5);
		fadeTransition.setAutoReverse(true);
		fadeTransition.setCycleCount(2);
		fadeTransition.play();

	}

	public void setText(String message) {
		status.setText(message);
		this.setOpacity(1);
	}
}
