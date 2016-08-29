package ua.kas.main;

import java.awt.image.BufferedImage;

public class Texture {

	private BufferedImage blockSheet = null;

	public BufferedImage[] block = new BufferedImage[2];

	private SpriteSheet blockSpriteSheet;

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();

		blockSheet = loader.loadImage("/blockSheet.png");

		blockSpriteSheet = new SpriteSheet(blockSheet);

		getTextures();
	}

	private void getTextures() {
		// block
		block[0] = blockSpriteSheet.grabImage(1, 1, 32, 32);
		block[1] = blockSpriteSheet.grabImage(2, 1, 32, 32);
	}
}
