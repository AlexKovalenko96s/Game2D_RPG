package ua.kas.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheet {

	private BufferedImage sheet;

	private Image player;
	public BufferedImage[] enemy1 = new BufferedImage[2];
	public BufferedImage[] enemy2 = new BufferedImage[2];
	public BufferedImage[] enemy3 = new BufferedImage[2];
	public BufferedImage[] enemy4 = new BufferedImage[2];
	public BufferedImage[] shot = new BufferedImage[4];

	private ImageIcon img;

	public SpriteSheet(String path) {
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// player
		img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		player = img.getImage();

		// shot
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 1 * 32 - 32, 32, 32));
		shot[0] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 2 * 32 - 32, 32, 32));
		shot[1] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 3 * 32 - 32, 32, 32));
		shot[2] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 4 * 32 - 32, 32, 32));
		shot[3] = (BufferedImage) img.getImage();

		// enemy1
		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy1[0] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 2 * 32 - 32, 32, 32));
		enemy1[1] = (BufferedImage) img.getImage();

		// enemy2
		img = new ImageIcon(sheet.getSubimage(4 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy2[0] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(4 * 32 - 32, 2 * 32 - 32, 32, 32));
		enemy2[1] = (BufferedImage) img.getImage();

		// enemy3
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 1 * 32 - 32, 32, 32));
		enemy3[0] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 2 * 32 - 32, 32, 32));
		enemy3[1] = (BufferedImage) img.getImage();

		// enemy4
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 3 * 32 - 32, 32, 32));
		enemy4[0] = (BufferedImage) img.getImage();
		img = new ImageIcon(sheet.getSubimage(5 * 32 - 32, 4 * 32 - 32, 32, 32));
		enemy4[1] = (BufferedImage) img.getImage();
	}

	public Image getPlayer() {
		return player;
	}
}
