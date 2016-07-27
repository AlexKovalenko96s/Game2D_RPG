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
		g.setColor(Color.RED);
		for (Point point : main.snakeParts) {
			g.fillRect(point.x * Main.SCALE, point.y * Main.SCALE, Main.SCALE, Main.SCALE);
		}
		g.fillRect(main.head.x * Main.SCALE, main.head.y * Main.SCALE, Main.SCALE, Main.SCALE);
	}
}
