package ua.kas.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener {

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public static final int SCALE = 10;

	public int ticks = 0;
	public int direction = DOWN;
	public int score = 0;
	public int tailLength = 0;

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
		head = new Point(0, 0);
		random = new Random();
		apple = new Point(dim.width / SCALE, dim.height / SCALE);
		frame.setVisible(true);

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

			snakeParts.remove(0);

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
}