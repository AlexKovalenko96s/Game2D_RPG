package ua.kas.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Tile tile;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		tile = new Tile();

		/*
		 * Map
		 */

		for (Point point : Main.grassPoints) {
			g.drawImage(tile.getImg_grass(), point.x * Main.SCALE, point.y * Main.SCALE, null);
		}
		for (Point point : Main.wallPoints) {
			g.drawImage(tile.getImg_wall(), point.x * Main.SCALE, point.y * Main.SCALE, null);
		}

		/*
		 * Things
		 */

		g.drawImage(tile.getApple(), Main.apple.x * Main.SCALE, Main.apple.y * Main.SCALE, null);

		/*
		 * Snake
		 */

		for (Point point : Main.snakePoints) {
			g.drawImage(tile.getTail(), point.x * Main.SCALE, point.y * Main.SCALE, null);
		}
		if (Main.direction == 0) {
			g.drawImage(tile.getHeadUP(), Main.head.x * Main.SCALE, Main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 1) {
			g.drawImage(tile.getHeadDOWN(), Main.head.x * Main.SCALE, Main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 2) {
			g.drawImage(tile.getHeadLEFT(), Main.head.x * Main.SCALE, Main.head.y * Main.SCALE, null);
		}
		if (Main.direction == 3) {
			g.drawImage(tile.getHeadRIGHT(), Main.head.x * Main.SCALE, Main.head.y * Main.SCALE, null);
		}

		/*
		 * Menu
		 */

		if (Main.fail) {
			g.drawImage(tile.getPause(), Main.WIDTH / 2 - 200, Main.HEIGHT / 2 - 50, null);
			g.setColor(Color.BLACK);
			g.drawString("Fail!", Main.WIDTH / 2 - 10, Main.HEIGHT / 2);
		}

		if (Main.pause) {
			g.drawImage(tile.getPause(), Main.WIDTH / 2 - 200, Main.HEIGHT / 2 - 50, null);
			g.setColor(Color.BLACK);
			g.drawString("Level: " + Main.level, Main.WIDTH / 2 - 20, Main.HEIGHT / 2 - 10);
			g.drawString("PAUSE, please press SPACE for start!", Main.WIDTH / 2 - 100, Main.HEIGHT / 2 + 10);
		}

		g.setColor(Color.WHITE);
		g.drawString("Level: " + Main.level + "   Score: " + Main.score, Main.WIDTH - 150, Main.HEIGHT - 40);
	}

	public void createLevel(BufferedImage levelImage) {

		int height = levelImage.getHeight();
		int width = levelImage.getWidth();

		Main.wallPoints.clear();
		Main.grassPoints.clear();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				int pixel = levelImage.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 255 && blue == 0) {
					Main.grassPoints.add(new Point(x, y));
				}

				if (red == 0 && green == 0 && blue == 0) {
					Main.wallPoints.add(new Point(x, y));
				}
			}
		}
	}
}
