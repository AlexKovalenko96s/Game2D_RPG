package ua.kas.main;

import java.awt.image.BufferedImage;

public class Texture {

	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	private BufferedImage playerHitSheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[12];
	public BufferedImage[] playerHit = new BufferedImage[2];
	public BufferedImage[] lava = new BufferedImage[6];

	private SpriteSheet blockSpriteSheet, playerSpriteSheet, playerHitSpriteSheet;

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();

		blockSheet = loader.loadImage("/blockSheet.png");
		playerSheet = loader.loadImage("/spriteSheet.png");
		playerHitSheet = loader.loadImage("/hit.png");

		blockSpriteSheet = new SpriteSheet(blockSheet);
		playerSpriteSheet = new SpriteSheet(playerSheet);
		playerHitSpriteSheet = new SpriteSheet(playerHitSheet);

		getTextures();
	}

	private void getTextures() {
		// block
		block[0] = blockSpriteSheet.grabImage(1, 1, 32, 32);
		block[1] = blockSpriteSheet.grabImage(2, 1, 32, 32);

		// player go right
		player[0] = playerSpriteSheet.grabImage(1, 4, 32, 64);
		player[1] = playerSpriteSheet.grabImage(2, 4, 32, 64);
		player[2] = playerSpriteSheet.grabImage(3, 4, 32, 64);
		player[3] = playerSpriteSheet.grabImage(4, 4, 32, 64);
		player[4] = playerSpriteSheet.grabImage(5, 4, 32, 64);
		player[5] = playerSpriteSheet.grabImage(6, 4, 32, 64);

		// player go left
		player[6] = playerSpriteSheet.grabImage(1, 3, 32, 64);
		player[7] = playerSpriteSheet.grabImage(2, 3, 32, 64);
		player[8] = playerSpriteSheet.grabImage(3, 3, 32, 64);
		player[9] = playerSpriteSheet.grabImage(4, 3, 32, 64);
		player[10] = playerSpriteSheet.grabImage(5, 3, 32, 64);
		player[11] = playerSpriteSheet.grabImage(6, 3, 32, 64);

		// player hit right
		playerHit[0] = playerHitSpriteSheet.grabImage(2, 1, 48, 64);

		// player hit left
		playerHit[1] = playerHitSpriteSheet.grabImage(1, 1, 48, 64);

		// lava move
		lava[0] = blockSpriteSheet.grabImage(3, 1, 32, 32);
		lava[1] = blockSpriteSheet.grabImage(4, 1, 32, 32);
		lava[2] = blockSpriteSheet.grabImage(5, 1, 32, 32);

		// lava don`t move
		lava[3] = blockSpriteSheet.grabImage(6, 1, 32, 32);
		lava[4] = blockSpriteSheet.grabImage(7, 1, 32, 32);
		lava[5] = blockSpriteSheet.grabImage(8, 1, 32, 32);
	}
}
