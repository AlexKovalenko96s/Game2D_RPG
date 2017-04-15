package ua.kas.main.levels;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import ua.kas.main.RacingGame;

public class LevelEditor extends Application {

	ArrayList<Line> boundList = new ArrayList<>();
	Line boundEdge;
	ImageView imgView;
	ImageView carBounds;
	Pane root;
	Button btn;
	Button btn2;

	@Override
	public void start(Stage window) throws Exception {
		root = new Pane();
		Scene scene = new Scene(root, RacingGame.width, RacingGame.height);
		window.setScene(scene);
		window.show();
		window.setResizable(false);
		System.out.println("trying .. ");
		try {
			Image img;
			img = new Image(LevelEditor.class.getResourceAsStream("level01/level01.png"));
			carBounds = new ImageView(new Image(RacingGame.class.getResourceAsStream("car1.png")));
			imgView = new ImageView(img);
			carBounds.setX(RacingGame.width / 2);
			carBounds.setY(RacingGame.height / 2);

			imgView.setOpacity(0.5);
			root.getChildren().addAll(imgView, carBounds);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		root.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				boundEdge = new Line(e.getX(), e.getY(), e.getX(), e.getY());
				boundEdge.setStrokeWidth(3);
				boundEdge.setStroke(Color.ORANGE);

				root.getChildren().add(boundEdge);
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				boundEdge.setEndX(e.getX());
				boundEdge.setEndY(e.getY());
			}
		});
		root.setOnMouseReleased(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				boundEdge.setEndX(e.getX());
				boundEdge.setEndY(e.getY());
				boundList.add(boundEdge);
			}
		});
		btn = new Button("Get path");
		btn2 = new Button("Car bounds");
		btn.setOpacity(0.3);
		btn2.setOpacity(0.3);

		btn.setLayoutX(200);
		btn.setLayoutY(0);
		btn2.setLayoutX(100);
		;
		btn2.setLayoutY(0);
		;

		btn.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				System.out.print("{");
				Line line;
				for (int i = 0; i < boundList.size(); i++) {
					line = boundList.get(i);
					System.out.print("\n" + line.getStartX() + ", ");
					System.out.print(line.getStartY() + ", ");
					System.out.print(line.getEndX() + ", ");
					System.out.print(line.getEndY());
					if (i != boundList.size() - 1)
						System.out.print(", ");
				}

				System.out.println("\n}");
				boundList.clear();
				root.getChildren().clear();
				root.getChildren().addAll(imgView, carBounds, btn, btn2);
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				System.out.print("{");
				Line line;
				for (int i = 0; i < boundList.size(); i++) {
					line = boundList.get(i);
					System.out.print("\n" + (line.getStartX() - carBounds.getX()) + ", ");
					System.out.print((line.getStartY() - carBounds.getY()) + ", ");
					System.out.print((line.getEndX() - carBounds.getX()) + ", ");
					System.out.print((line.getEndY() - carBounds.getY()));
					if (i != boundList.size() - 1)
						System.out.print(", ");
				}

				System.out.println("\n}");
				boundList.clear();
				root.getChildren().clear();
				root.getChildren().addAll(imgView, carBounds, btn, btn2);
			}
		});
		root.getChildren().addAll(btn, btn2);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
