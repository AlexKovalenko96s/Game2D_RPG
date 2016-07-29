package ua.kas.snake;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private Map m;

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Main main = Main.main;
		m = new Map();

		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 30; x++) {
				if (m.getMap(x, y).equals("g")) {
					g.drawImage(m.getGrass(), x * Main.SCALE, y * Main.SCALE, null);
				}
				if (m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x * Main.SCALE, y * Main.SCALE, null);
				}
			}
		}

		// g.drawImage(m.getApple(), main.apple.x * Main.SCALE, main.apple.y *
		// Main.SCALE, null);

		for (Point point : main.snakeParts) {
			g.drawImage(m.getTail(), point.x * Main.SCALE, point.y * Main.SCALE, null);
		}

		if (main.direction == 0) {
			g.drawImage(m.getHeadUP(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (main.direction == 1) {
			g.drawImage(m.getHeadDOWN(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (main.direction == 2) {
			g.drawImage(m.getHeadLEFT(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
		if (main.direction == 3) {
			g.drawImage(m.getHeadRIGHT(), main.head.x * Main.SCALE, main.head.y * Main.SCALE, null);
		}
	}
}