package ua.kas.snake;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private Map m;

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		m = new Map();

		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 30; x++) {
				if (m.getMap(x, y).equals("g")) {
					g.drawImage(m.getGrass(), x * 32, y * 32, null);
				}
				if (m.getMap(x, y).equals("w")) {
					g.drawImage(m.getWall(), x * 32, y * 32, null);
				}
			}
		}
	}
}