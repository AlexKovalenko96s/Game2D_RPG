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
	public ArrayList<String> grassPoints = new ArrayList<String>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	public static int level = 1;

	public static final int HEIGHT = 829;
	public static final int WIDTH = 966;
	public static final int SCALE = 32;

	public int tailLength = 2;
	public int direction;
	public int score = 0;

	public boolean over = false;

	public Timer timer = new Timer(20, this);

	public Dimension dim;

	public JFrame frame;

	public Point head;
	public Point apple;

	private Random random;

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
		frame.setVisible(true);

		startGame();
	}

	public void startGame() {

		over = false;

		if (level == 1) {
			tailLength = 2;
			score = 0;
			direction = DOWN;
			head = new Point(1, 1);
		}

		snakeParts.clear();
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		renderPanel.repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W) {
			direction = UP;
		}
		if (i == KeyEvent.VK_S) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_A) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_D) {
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

	public static void main(String[] args) {
		main = new Main();
	}
}
