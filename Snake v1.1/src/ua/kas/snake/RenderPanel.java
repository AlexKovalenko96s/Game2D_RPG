package ua.kas.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map m;

	private Random random;

	public ArrayList<String> placeForApple = new ArrayList<String>();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Main main = Main.main;
		m = new Map();

		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 30; x++) {
				if (m.getMap(x, y).equals("g")) {
					g.drawImage(m.getGrass(), x * Main.SCALE, y * Main.SCALE, null);
					placeForApple.add(Integer.toString(x) + ";" + Integer.toString(y));
				}
				if (m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x * Main.SCALE, y * Main.SCALE, null);
				}
			}
		}

		if (Main.needApple) {

			random = new Random();
			String s = placeForApple.get(random.nextInt(placeForApple.size()));

			Main.apple = new Point(Integer.parseInt(s.substring(0, s.indexOf(";"))),
					Integer.parseInt(s.substring(s.indexOf(";") + 1)));
			Main.needApple = false;

			placeForApple.clear();
		}

		g.drawImage(m.getApple(), Main.apple.x * Main.SCALE, Main.apple.y * Main.SCALE, null);

		for (Point point : Main.snakeParts) {
			g.drawImage(m.getTail(), point.x * Main.SCALE, point.y * Main.SCALE, null);
		}

		if (Main.direction == 0) {
			g.drawImage(m.getHeadUP(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 1) {
			g.drawImage(m.getHeadDOWN(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 2) {
			g.drawImage(m.getHeadLEFT(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 3) {
			g.drawImage(m.getHeadRIGHT(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}

		if (main.over) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 966, 829);
			g.setColor(Color.WHITE);
			g.drawString("You`re lose!" + " Score: " + Main.score, 450, 400);
			g.drawString(" Time: " + Main.ticks / 2 / 20, 480, 420);
		}

	}
}