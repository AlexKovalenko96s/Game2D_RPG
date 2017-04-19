package ua.kas.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class PongGame extends Application {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private static final int BALL_RADIUS = 10;
	private static final int BAT_WIDTH = 100;
	private static final int BAT_HEIGHT = 20;

	private int countUp = 0;
	private int countDown = 0;
	private int countUpSocker = 0, countDownSocker = 0;

	private double ballX = 0, ballY = 0, x = 0, y = 0;

	private Circle ball = new Circle(BALL_RADIUS);

	private Rectangle bat1 = new Rectangle(BAT_WIDTH, BAT_HEIGHT);
	private Rectangle bat2 = new Rectangle(BAT_WIDTH, BAT_HEIGHT);

	private KeyCode keyPressedCode = null;

	private Timeline gameLoop;

	private boolean ballUp = true;
	private boolean ballLeft = false;
	private boolean keyPressed = false;
	private boolean ballUpSocket, ballLeftSocket;

	static boolean server;

	static String address;

	private DataOutputStream out;
	private DataInputStream in;

	private Text scoreUp = new Text("0");
	private Text scoreDown = new Text("0");

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Pong");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		countUp = 0;
		countDown = 0;
		startGame();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	private Pane createContent() {
		Pane container = new Pane();
		container.setPrefSize(WIDTH - 10, HEIGHT - 10);

		if (server) {
			bat1.setTranslateX(WIDTH / 2);
			bat1.setTranslateY(HEIGHT - BAT_HEIGHT);
			bat1.setFill(Color.RED);

			bat2.setTranslateX(WIDTH / 2);
			bat2.setTranslateY(0);
			bat2.setFill(Color.BLUE);
		} else {
			bat2.setTranslateX(WIDTH / 2);
			bat2.setTranslateY(HEIGHT - BAT_HEIGHT);
			bat2.setFill(Color.RED);

			bat1.setTranslateX(WIDTH / 2);
			bat1.setTranslateY(0);
			bat1.setFill(Color.BLUE);
		}

		ball.setFill(Color.CORAL);

		scoreUp.setX(WIDTH / 2 - 10);
		scoreUp.setY(HEIGHT / 2 - 50);
		scoreUp.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 90));
		scoreUp.setFill(Color.LIGHTGRAY);

		scoreDown.setX(WIDTH / 2 - 10);
		scoreDown.setY(HEIGHT / 2 + 50);
		scoreDown.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 90));
		scoreDown.setFill(Color.LIGHTGRAY);

		Rectangle background = new Rectangle(0, 0, WIDTH, HEIGHT);
		background.setFill(Color.WHITE);

		container.getChildren().addAll(background, bat1, bat2, scoreUp, scoreDown, ball);

		container.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent e) {
				keyPressed = true;
				keyPressedCode = e.getCode();
			}
		});

		container.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent e) {
				keyPressed = false;
			}
		});

		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000 / 60), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				if (keyPressed) {
					if (keyPressedCode == KeyCode.LEFT) {
						if (bat1.getTranslateX() - 5 >= 0)
							bat1.setTranslateX(bat1.getTranslateX() - 5);
					} else if (keyPressedCode == KeyCode.RIGHT) {
						if (bat1.getTranslateX() + 5 <= WIDTH - BAT_WIDTH)
							bat1.setTranslateX(bat1.getTranslateX() + 5);
					}
				}

				ball.setTranslateX(ball.getTranslateX() + (ballLeft ? -5 : 5));
				ball.setTranslateY(ball.getTranslateY() + (ballUp ? -5 : 5));

				scoreUp.setText(Integer.toString(countUp));
				scoreDown.setText(Integer.toString(countDown));

				try {
					if (out != null) {
						out.writeDouble(ball.getTranslateX());
						out.writeDouble(ball.getTranslateY());
						out.writeDouble(bat1.getTranslateX());
						out.writeDouble(bat1.getTranslateY());
						out.writeBoolean(ballUp);
						out.writeBoolean(ballLeft);
						out.writeInt(countUp);
						out.writeInt(countDown);
					}
				} catch (Exception ex) {
				}

				try {
					if (in != null) {
						ball.setTranslateX(ballX);
						ball.setTranslateY(ballY);
						bat2.setTranslateX(x);
						bat2.setTranslateY(y);
						ballUp = ballUpSocket;
						ballLeft = ballLeftSocket;
						countUp = countUpSocker;
						countDown = countDownSocker;
					}
				} catch (Exception ex) {
				}

				checkForCollisions();
			}
		}));

		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.play();

		if (server) {
			Rectangle r = new Rectangle(WIDTH, HEIGHT);
			r.setFill(Color.WHITE);
			r.setOpacity(0.6);
			container.getChildren().addAll(r);
			Text t = new Text("Ожидаем второго игрока...");
			t.setX(WIDTH / 2 - 100);
			t.setY(HEIGHT / 2 - 25);
			t.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 17));
			t.setFill(Color.LIGHTGRAY);
			container.getChildren().addAll(t);
		}

		new Thread(new Runnable() {

			@SuppressWarnings("resource")
			public void run() {
				try {
					Socket socket;
					if (server) {
						ServerSocket serverSocket = new ServerSocket(8888);
						socket = serverSocket.accept();

						Platform.runLater(new Runnable() {
							public void run() {
								container.getChildren().remove(container.getChildren().size() - 2,
										container.getChildren().size());
								container.requestFocus();
							}
						});
					} else {
						socket = new Socket(address, 8888);
						Platform.runLater(new Runnable() {

							public void run() {
								container.requestFocus();
							}
						});
					}

					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());

					while (true) {
						ballX = in.readDouble();
						ballY = in.readDouble();
						x = in.readDouble();
						y = in.readDouble();
						ballUpSocket = in.readBoolean();
						ballLeftSocket = in.readBoolean();
						countUpSocker = in.readInt();
						countDownSocker = in.readInt();
					}
				} catch (Exception e) {
					System.out.println("Server error" + e.toString());
				}
			}
		}).start();
		return container;
	}

	private void checkForCollisions() {
		if (ball.getTranslateX() - BALL_RADIUS == 0)
			ballLeft = false;
		else if (ball.getTranslateX() + BALL_RADIUS == WIDTH)
			ballLeft = true;
		if (ball.getTranslateY() + BALL_RADIUS <= BAT_HEIGHT + 20
				&& ball.getTranslateX() + BALL_RADIUS >= bat1.getTranslateX()
				&& ball.getTranslateX() - BALL_RADIUS <= bat1.getTranslateX() + BAT_WIDTH && !server) {
			ballUp = false;
		} else if (ball.getTranslateY() + BALL_RADIUS >= HEIGHT - BAT_HEIGHT
				&& ball.getTranslateX() + BALL_RADIUS >= bat1.getTranslateX()
				&& ball.getTranslateX() - BALL_RADIUS <= bat1.getTranslateX() + BAT_WIDTH && server) {
			ballUp = true;
		}
		if (ball.getTranslateY() + BALL_RADIUS >= HEIGHT - BAT_HEIGHT / 2) {
			countUp++;
			ballUp = false;
			startGameAfterScore();
		} else if (ball.getTranslateY() + BALL_RADIUS <= BAT_HEIGHT / 2) {
			countDown++;
			ballUp = true;
			startGameAfterScore();
		}
	}

	private void stopGame() {
		gameLoop.stop();
	}

	private void startGame() {
		stopGame();

		ballUp = true;
		ball.setTranslateX(WIDTH / 2);
		ball.setTranslateY(HEIGHT / 2);

		gameLoop.play();
	}

	private void startGameAfterScore() {
		stopGame();

		ball.setTranslateX(WIDTH / 2);
		ball.setTranslateY(HEIGHT / 2);

		gameLoop.play();
	}
}
