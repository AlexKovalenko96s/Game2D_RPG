package ua.kas.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener, KeyListener {

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public static final int SCALE = 10;

	public int ticks = 0;
	public int direction = DOWN;
	public int score = 0;
	public int tailLength = 2;

	public boolean over = false;
	public boolean paused = false;

	public Dimension dim;

	public Point head;
	public Point apple;

	public JFrame frame;

	public Timer timer = new Timer(20, this);

	public static Main main;

	public RenderPanel renderPanel;

	public Random random;

	public Main() {

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(805, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(renderPanel = new RenderPanel());
		frame.addKeyListener(this);

		frame.setVisible(true);

		startGame();
	}

	public void startGame() {

		over = false;
		paused = false;

		tailLength = 2;
		score = 0;
		direction = DOWN;

		head = new Point(0, 0);
		random = new Random();
		apple = new Point(random.nextInt(79), random.nextInt(66));

		snakeParts.clear();

		// for (int i = 0; i < tailLength; i++) {
		// snakeParts.add(new Point(head.x, head.y));
		// }

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		renderPanel.repaint();

		if (!over) {
			ticks++;
		}

		if (ticks % 2 == 0 && head != null && over != true && paused != true) {

			snakeParts.add(new Point(head.x, head.y));

			if (snakeParts.size() > tailLength) {
				snakeParts.remove(0);
			}

			if (direction == UP) {
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					over = true;
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 <= 66 && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					over = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					over = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 <= 79 && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					over = true;
				}
			}

			if (apple != null) {
				if (head.equals(apple)) {
					score += 10;
					tailLength++;
					apple.setLocation(random.nextInt(79), random.nextInt(66));
				}
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

	public static void main(String[] args) {

		main = new Main();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W && direction != DOWN) {
			direction = UP;
		}
		if (i == KeyEvent.VK_S && direction != UP) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_A && direction != RIGHT) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_D && direction != LEFT) {
			direction = RIGHT;
		}
		if (i == KeyEvent.VK_SPACE) {
			if (over) {
				startGame();
			} else {
				paused = !paused;

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
}