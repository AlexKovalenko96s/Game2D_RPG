package ua.kas.theGoldenKey;

import java.awt.Graphics;

public class EntityPlayer extends Entity {

	public double moveSpeed = 1.8;

	private static int Rx, Ry;

	public static int[][] pImgDown = { { 0, 0 }, { 1, 0 }, { 2, 0 } };
	public static int[][] pImgLeft = { { 0, 1 }, { 1, 1 }, { 2, 1 } };
	public static int[][] pImgRight = { { 0, 2 }, { 1, 2 }, { 2, 2 } };
	public static int[][] pImgUp = { { 0, 3 }, { 1, 3 }, { 2, 3 } };

	public static boolean isMoving = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;

	private Core GK;

	public int aniFrame = 0;
	public int aniTime = 10;
	public int aniDelta = 0;

	public EntityPlayer(Core GK, double x, double y, int width, int height) {

		super(new int[] { 0, 0 }, x, y, width, height);
		moveSpeed = 1.8;
		health = 110;
		// setBounds((Core.pixel.width / 2) - (width / 2), (Core.pixel.height /
		// 2) - (height / 2), width, height);
	}

	public void tick(double delta) {

		aniDelta++;

		if (aniDelta >= aniTime) {
			aniFrame++;
			aniDelta = 0;

			if (aniFrame > 2) {
				aniFrame = 0;
			}
		}

		if (up) {
			Core.oY -= moveSpeed * delta;
		}
		if (down) {
			Core.oY += moveSpeed * delta;
		}
		if (left) {

			Core.oX -= moveSpeed * delta;
		}
		if (right) {
			Core.oX += moveSpeed * delta;
		}

	}

	public void render(Graphics g) {

		if (down) {
			g.drawImage(Tile.characters, (int) this.x, (int) this.y, (int) x + width, (int) y + height,
					pImgDown[aniFrame][0] * Tile.size, pImgDown[aniFrame][1] * Tile.size,
					pImgDown[aniFrame][0] * Tile.size + Tile.size, pImgDown[aniFrame][1] * Tile.size + Tile.size, null);
		} else if (up) {
			g.drawImage(Tile.characters, (int) this.x, (int) this.y, (int) x + width, (int) y + height,
					pImgUp[aniFrame][0] * Tile.size, pImgUp[aniFrame][1] * Tile.size,
					pImgUp[aniFrame][0] * Tile.size + Tile.size, pImgUp[aniFrame][1] * Tile.size + Tile.size, null);
		} else if (left) {
			g.drawImage(Tile.characters, (int) this.x, (int) this.y, (int) x + width, (int) y + height,
					pImgLeft[aniFrame][0] * Tile.size, pImgLeft[aniFrame][1] * Tile.size,
					pImgLeft[aniFrame][0] * Tile.size + Tile.size, pImgLeft[aniFrame][1] * Tile.size + Tile.size, null);
		} else if (right) {
			g.drawImage(Tile.characters, (int) this.x, (int) this.y, (int) x + width, (int) y + height,
					pImgRight[aniFrame][0] * Tile.size, pImgRight[aniFrame][1] * Tile.size,
					pImgRight[aniFrame][0] * Tile.size + Tile.size, pImgRight[aniFrame][1] * Tile.size + Tile.size,
					null);
		} else {
			g.drawImage(Tile.characters, (int) this.x, (int) this.y, (int) x + width, (int) y + height,
					pImgDown[1][0] * Tile.size, pImgDown[1][1] * Tile.size, pImgDown[1][0] * Tile.size + Tile.size,
					pImgDown[1][1] * Tile.size + Tile.size, null);
		}

	}

	@Override
	public void collidedWith(Entity entity) {
		// TODO Auto-generated method stub

	}
}
