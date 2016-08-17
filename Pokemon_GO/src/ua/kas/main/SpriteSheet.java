package ua.kas.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheet {

	private BufferedImage sheet;

	private Image player, enemy1, enemy2, enemy3, enemy4, shot;

	private ImageIcon img;

	public SpriteSheet(String path) {
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		player = img.getImage();
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 1 * 32 - 32, 32, 32));
		shot = img.getImage();
		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy1 = img.getImage();
		img = new ImageIcon(sheet.getSubimage(4 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy2 = img.getImage();
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy3 = img.getImage();
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 3 * 32 - 32, 32, 32));
		enemy4 = img.getImage();
	}

	public Image getPlayer() {
		return player;
	}

	public Image getEnemy1() {
		return enemy1;
	}

	public Image getEnemy2() {
		return enemy2;
	}

	public Image getEnemy3() {
		return enemy3;
	}

	public Image getEnemy4() {
		return enemy4;
	}

	public Image getShot() {
		return shot;
	}
}
