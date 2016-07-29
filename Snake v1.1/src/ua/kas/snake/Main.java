package ua.kas.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener, KeyListener {

	public static ArrayList<Point> snakeParts = new ArrayList<Point>();
	public ArrayList<String> grassPoints = new ArrayList<String>();

	private Scanner scn;

	private String Map[] = new String[25];

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	public static int level = 1;

	public static final int HEIGHT = 829;
	public static final int WIDTH = 966;
	public static final int SCALE = 32;

	public static int tailLength = 2;
	public static int direction;
	public static int score = 0;
	public static int ticks = 0;

	public boolean over = false;
	public static boolean needApple = true;

	public Timer timer = new Timer(60, this);

	public Dimension dim;

	public JFrame frame;

	public Point head;
	public static Point apple;

	public static Map m;
	public static RenderPanel renderPanel;
	public static Main main;

	public Main() {

		frame = new JFrame("Snake v1.1");

		dim = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(renderPanel = new RenderPanel());
		frame.addKeyListener(this);
		frame.setVisible(true);

		startGame();
		readFile();
	}

	public void startGame() {
		over = false;

		if (tailLength == 5) {
			level = 2;
			tailLength = 2;
			score = 0;
			direction = DOWN;
			head = new Point(1, 1);
		}

		if (level == 1) {
			tailLength = 2;
			score = 0;
			direction = DOWN;
			head = new Point(1, 1);
		}

		snakeParts.clear();
		readFile();
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		renderPanel.repaint();

		if (!over) {
			ticks++;
		}

		if (ticks % 2 == 0 && !over) {
			snakeParts.add(new Point(head.x, head.y));

			if (snakeParts.size() > tailLength) {
				snakeParts.remove(0);
			}

			if (direction == UP) {
				if (!getMap(head.x, head.y - 1).equals("w") && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					over = true;
				}
			}

			if (direction == DOWN) {
				if (!getMap(head.x, head.y + 1).equals("w") && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					over = true;
				}
			}

			if (direction == LEFT) {
				if (!getMap(head.x - 1, head.y).equals("w") && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					over = true;
				}
			}

			if (direction == RIGHT) {
				if (!getMap(head.x + 1, head.y).equals("w") && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					over = true;
				}
			}

			if (apple != null) {
				if (head.equals(apple)) {
					score += 10;
					tailLength++;
					needApple = true;
				}
			}

			if (tailLength == 5 && level == 1) {
				startGame();
			}
		}
	}

	private boolean noTailAt(int x, int y) {
		for (Point point : snakeParts) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W && direction != DOWN && !over) {
			direction = UP;
		}
		if (i == KeyEvent.VK_S && direction != UP && !over) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_A && direction != RIGHT && !over) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_D && direction != LEFT && !over) {
			direction = RIGHT;
		}
		if (i == KeyEvent.VK_SPACE) {
			if (over) {
				startGame();
			}
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

	public void readFile() {

		try {
			scn = new Scanner(new File("res/level" + level + ".txt"));
		} catch (Exception e) {
			System.err.println("Error Loading Map!");
		}

		while (scn.hasNext()) {
			for (int i = 0; i < 25; i++) {
				Map[i] = scn.next();
			}
		}

		scn.close();
	}

	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}

	public static void main(String[] args) {
		main = new Main();
	}
}
