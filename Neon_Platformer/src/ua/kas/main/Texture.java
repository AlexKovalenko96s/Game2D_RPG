package ua.kas.main;

import java.awt.image.BufferedImage;

public class Texture {

	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] player_jump = new BufferedImage[6];

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

		// looking right
		player[0] = spriteSheet_player.grabImage(1, 1, 32, 64);
		player[1] = spriteSheet_player.grabImage(2, 1, 32, 64);
		player[2] = spriteSheet_player.grabImage(3, 1, 32, 64);
		player[3] = spriteSheet_player.grabImage(4, 1, 32, 64);
		player[4] = spriteSheet_player.grabImage(5, 1, 32, 64);
		player[5] = spriteSheet_player.grabImage(6, 1, 32, 64);
		player[6] = spriteSheet_player.grabImage(7, 1, 32, 64);

		// looking left
		player[7] = spriteSheet_player.grabImage(20, 1, 32, 64);
		player[8] = spriteSheet_player.grabImage(19, 1, 32, 64);
		player[9] = spriteSheet_player.grabImage(18, 1, 32, 64);
		player[10] = spriteSheet_player.grabImage(17, 1, 32, 64);
		player[11] = spriteSheet_player.grabImage(16, 1, 32, 64);
		player[12] = spriteSheet_player.grabImage(15, 1, 32, 64);
		player[13] = spriteSheet_player.grabImage(14, 1, 32, 64);

		// jump right
		player_jump[0] = spriteSheet_player.grabImage(8, 2, 32, 64);
		player_jump[1] = spriteSheet_player.grabImage(9, 2, 32, 64);
		player_jump[2] = spriteSheet_player.grabImage(10, 2, 32, 64);

		// jump left
		player_jump[3] = spriteSheet_player.grabImage(13, 2, 32, 64);
		player_jump[4] = spriteSheet_player.grabImage(12, 2, 32, 64);
		player_jump[5] = spriteSheet_player.grabImage(11, 2, 32, 64);
	}
}
