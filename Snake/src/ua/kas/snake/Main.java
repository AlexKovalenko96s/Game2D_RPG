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
	public int tailLength = 10;

	public boolean over = false;

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
		frame.setSize(800, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(renderPanel = new RenderPanel());
		frame.addKeyListener(this);
		head = new Point(0, 0);
		random = new Random();
		apple = new Point(dim.width / SCALE, dim.height / SCALE);
		frame.setVisible(true);

		for (int i = 0; i < tailLength; i++) {
			snakeParts.add(new Point(head.x, head.y));
		}

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		renderPanel.repaint();
		ticks++;

		if (ticks % 10 == 0 && head != null && over != true) {

			snakeParts.add(new Point(head.x, head.y));

			if (direction == UP) {
				if (head.y - 1 > 0) {
					head = new Point(head.x, head.y - 1);
				} else {
					over = true;
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 < dim.height / SCALE) {
					head = new Point(head.x, head.y + 1);
				} else {
					over = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 > 0) {
					head = new Point(head.x - 1, head.y);
				} else {
					over = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 < dim.width / SCALE) {
					head = new Point(head.x + 1, head.y);
				} else {
					over = true;
				}
			}

			// for (int i = 0; i < tailLength; i++) {
			// snakeParts.remove(i);
			// }

			if (apple != null) {
				if (head.equals(apple)) {
					score += 10;
					tailLength++;
					apple.setLocation(dim.width / SCALE, dim.height / SCALE);
				}
			}
		}
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