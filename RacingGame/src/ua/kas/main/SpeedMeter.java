package ua.kas.main;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;

// graphics speed-meter

public class SpeedMeter extends Group {

	Arc c = new Arc(0, 0, 100, 100, 0, 180);
	Line meter = new Line(0, 0, 0, -100);
	Text text = new Text("0 km/h");

	public SpeedMeter() {
		super();
		getChildren().addAll(c, text);
		setOpacity(0.3);
		text.setFill(Color.ORANGE);
		text.setX(-20);
		text.setY(-40);
		c.setFill(Color.MEDIUMSLATEBLUE);
		double t;
		for (int i = 0; i <= 22; i++) {
			t = i * Math.PI / 22;
			Line line = new Line(100 * Math.cos(t), -100 * Math.sin(t), 90 * Math.cos(t), -90 * Math.sin(t));
			line.setStroke(Color.ORANGE);
			line.setStrokeWidth(2);
			line.setStrokeLineCap(StrokeLineCap.ROUND);
			getChildren().add(line);
		}
		getChildren().add(meter);
		meter.setStroke(Color.BLUEVIOLET);
		meter.setStrokeWidth(5);
		meter.setStrokeLineCap(StrokeLineCap.ROUND);
		meter.setEffect(new DropShadow(5, Color.BLACK));
	}

	public void setSpeed(double speed) {
		double t = 0;
		if (speed > 0) {
			t = speed * Math.PI / 22;
			text.setText((int) (speed * 30) + " km/h");

		} else {
			t = -speed * Math.PI / 22;
			text.setText((int) (-speed * 30) + " km/h R");
		}
		meter.setEndX(-80 * Math.cos(t));
		meter.setEndY(-80 * Math.sin(t));
	}
}