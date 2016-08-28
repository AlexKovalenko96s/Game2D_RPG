package ua.kas.main;

import java.awt.image.BufferedImage;

public class Texture {

	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[7];

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
		player[1] = spriteSheet_player.grabImage(2, 1, 32, 64);
		player[2] = spriteSheet_player.grabImage(3, 1, 32, 64);
		player[3] = spriteSheet_player.grabImage(4, 1, 32, 64);
		player[4] = spriteSheet_player.grabImage(5, 1, 32, 64);
		player[5] = spriteSheet_player.grabImage(6, 1, 32, 64);
		player[6] = spriteSheet_player.grabImage(7, 1, 32, 64);
	}
}
