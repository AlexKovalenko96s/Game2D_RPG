package ua.kas.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener, KeyListener {

	public static ArrayList<Point> snakePoints = new ArrayList<Point>();
	public static ArrayList<Point> grassPoints = new ArrayList<Point>();
	public static ArrayList<Point> wallPoints = new ArrayList<Point>();

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public static final int HEIGHT = 829;
	public static final int WIDTH = 966;
	public static final int SCALE = 32;

	public static int direction;
	public static int level = 1;
	public static int score = 0;
	public static int tail = 2;
	public static int tick = 0;

	public static String secret = "";

	public static boolean fail = false;
	public static boolean pause = true;

	private static JFrame frame;

	private static Random random;

	public Timer timer = new Timer(60, this);

	public static Point head;
	public static Point apple;

	public Dimension dim;

	public static Main main;
	public static RenderPanel renderPanel;

	public Main() {

		frame = new JFrame("Snake v1.2");

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(renderPanel = new RenderPanel());
		frame.addKeyListener(this);
		frame.setResizable(false);

		frame.setVisible(true);

		startGame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		renderPanel.repaint();

		if (!fail) {
			tick++;
		}

		if (tick % 2 == 0 && !fail && pause != true) {
			snakePoints.add(new Point(head.x, head.y));

			if (snakePoints.size() > tail) {
				snakePoints.remove(0);
			}

			if (direction == UP) {
				if (!wallPoints.contains(new Point(head.x, head.y - 1)) && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					fail = true;
					score = 0;
					level = 1;
				}
			}
			if (direction == DOWN) {
				if (!wallPoints.contains(new Point(head.x, head.y + 1)) && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					fail = true;
					score = 0;
					level = 1;
				}
			}
			if (direction == LEFT) {
				if (!wallPoints.contains(new Point(head.x - 1, head.y)) && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					fail = true;
					score = 0;
					level = 1;
				}
			}
			if (direction == RIGHT) {
				if (!wallPoints.contains(new Point(head.x + 1, head.y)) && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					fail = true;
					score = 0;
					level = 1;
				}
			}

			if (head.equals(apple)) {
				score += 10;
				tail++;
				random = new Random();
				int i = random.nextInt(grassPoints.size() - 1);
				apple = grassPoints.get(i);
			}

			if (tail == 12) {
				startGame();
			}
		}
	}

	public void startGame() {

		fail = false;
		pause = true;

		if (secret.equals("148")) {

			secret = "";

			try {
				renderPanel.createLevel(ImageIO.read(new File("res/levelS.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			tail = 2;
			level = 1488;
			direction = DOWN;
			head = new Point(1, 1);
		}

		if (level == 1 && tail == 12) {

			try {
				renderPanel.createLevel(ImageIO.read(new File("res/level2.png")));
				System.out.println(wallPoints);
			} catch (IOException e) {
				e.printStackTrace();
			}

			tail = 2;
			level = 2;
			direction = DOWN;
			head = new Point(1, 1);
		} else {
			try {
				renderPanel.createLevel(ImageIO.read(new File("res/level1.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(wallPoints);
			tail = 2;
			level = 1;
			direction = DOWN;
			head = new Point(1, 1);
		}

		random = new Random();
		int i = random.nextInt(grassPoints.size() - 1);
		apple = grassPoints.get(i);

		snakePoints.clear();
		timer.start();
	}

	private boolean noTailAt(int x, int y) {
		for (Point point : snakePoints) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		main = new Main();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W && direction != DOWN && !fail && !pause) {
			direction = UP;
		}
		if (i == KeyEvent.VK_S && direction != UP && !fail && !pause) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_A && direction != RIGHT && !fail && !pause) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_D && direction != LEFT && !fail && !pause) {
			direction = RIGHT;
		}
		if (i == KeyEvent.VK_SPACE) {
			if (fail) {
				startGame();
			} else {
				pause = !pause;
			}
		}
		if (i == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		/*
		 * Secret level
		 */

		if (i == KeyEvent.VK_1) {
			secret = "1";
		}
		if (i == KeyEvent.VK_4 && secret.equals("1")) {
			secret = "14";
		}
		if (i == KeyEvent.VK_8 && secret.equals("14")) {
			secret = "148";
			startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// not used
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used
	}
}
