package ua.kas.main;

import java.awt.image.BufferedImage;

public class Texture {

	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[1];

	private SpriteSheet spriteSheet_player, spriteSheet_block;

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();

		block_sheet = loader.loadImage("/block_sheet.png");
		player_sheet = loader.loadImage("/player_sheet.png");

		spriteSheet_player = new SpriteSheet(player_sheet);
		spriteSheet_block = new SpriteSheet(block_sheet);

		getTextures();
	}

	private void getTextures() {
		block[0] = spriteSheet_block.grabImage(1, 1, 32, 32);
		block[1] = spriteSheet_block.grabImage(2, 1, 32, 32);

		player[0] = spriteSheet_player.grabImage(1, 1, 32, 64);
	}
}
