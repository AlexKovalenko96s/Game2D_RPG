package ua.kas.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheet {

	private BufferedImage sheet;

	private Image ship, bullet, enemy;

	private ImageIcon img;

	public SpriteSheet(String path) {
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		ship = img.getImage();
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 1 * 32 - 32, 32, 32));
		bullet = img.getImage();
		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy = img.getImage();
	}

	public Image getShip() {
		return ship;
	}

	public Image getBullet() {
		return bullet;
	}

	public Image getEnemy() {
		return enemy;
	}
}
