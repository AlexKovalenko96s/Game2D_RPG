package ua.kas.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(1666073));
		g.fillRect(0, 0, 800, 700);
		Main main = Main.main;

		/*
		 * head & tail
		 */

		g.setColor(Color.YELLOW);
		for (Point point : main.snakeParts) {
			g.fillRect(point.x * Main.SCALE, point.y * Main.SCALE, Main.SCALE, Main.SCALE);
		}
		g.setColor(Color.ORANGE);
		g.fillRect(main.head.x * Main.SCALE, main.head.y * Main.SCALE, Main.SCALE, Main.SCALE);

		/*
		 * apple
		 */

		g.setColor(Color.RED);
		g.fillRect(main.apple.x * Main.SCALE, main.apple.y * Main.SCALE, Main.SCALE, Main.SCALE);

		/*
		 * 
		 */

		if (main.over) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 700);
			g.setColor(Color.WHITE);
			g.drawString("You`re lose!" + " Score: " + main.score, 350, 300);
			g.drawString(" Time: " + main.ticks / 2 / 20, 380, 320);
		}

		if (main.paused) {
			g.setColor(Color.WHITE);
			g.drawString("Paused" + " Score: " + main.score, 350, 300);
		}
	}
}
