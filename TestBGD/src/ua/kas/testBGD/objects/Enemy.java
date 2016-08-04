package ua.kas.testBGD.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ua.kas.testBGD.Main;

public class Enemy extends GlobalPosition {

	private BufferedImage sheet;

	private int speed = 10;

	public Enemy(int x, int y) {
		super(x, y);
	}

	public void update() {
		x += speed;
		if (x > Main.WIDTH - 32) {
			speed = -10;
		}
		if (x < 0) {
			speed = +10;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(getPlayerImage(), x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Image getPlayerImage() {
		try {
			sheet = ImageIO.read(new File("res/piratesTest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon i = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 1 * 32 - 32, 32, 32));
		return i.getImage();
	}

}
