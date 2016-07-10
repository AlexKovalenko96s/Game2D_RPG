package ua.kas.theGoldenKey;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;

	public double moveSpeed = 1.0;

	public static int[] pImg = { 0, 0 };

	public static boolean isMoving = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;

	public int aniFrame = 1;
	public int aniDelta = 0;

	public Player(String name) {
		width = 32;
		height = 32;
		setBounds((Core.pixel.width / 2) - (width / 2), (Core.pixel.height / 2) - (height / 2), width, height);
	}

	public void tick() {

		if (up) {
			Core.oY -= moveSpeed;
		}
		if (down) {
			Core.oY += moveSpeed;
		}
		if (left) {
			Core.oX -= moveSpeed;
		}
		if (right) {
			Core.oX += moveSpeed;
		}
	}

	public void render(Graphics g) {

		if (aniFrame == 1) {
			g.drawImage(Tile.characters, this.x, this.y, x + width, y + height, pImg[0] * Tile.size,
					pImg[1] * Tile.size, pImg[0] * Tile.size + Tile.size, pImg[1] * Tile.size + Tile.size, null);
		}
	}
}
