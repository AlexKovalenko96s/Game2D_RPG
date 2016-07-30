package ua.kas.snake;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tile {

	private BufferedImage sheet;

	private Image img_grass, img_wall;
	private Image headUP, headDOWN, headLEFT, headRIGHT, tail, apple;
	private Image pause;

	private ImageIcon img;

	public Tile() {

		try {
			sheet = ImageIO.read(new File("res/snake.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			img = new ImageIcon(ImageIO.read(new File("res/pause.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pause = img.getImage();

		img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		img_grass = img.getImage();

		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 1 * 32 - 32, 32, 32));
		img_wall = img.getImage();

		img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 4 * 32 - 32, 32, 32));
		headLEFT = img.getImage();

		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 4 * 32 - 32, 32, 32));
		headRIGHT = img.getImage();

		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 3 * 32 - 32, 32, 32));
		headUP = img.getImage();

		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 5 * 32 - 32, 32, 32));
		headDOWN = img.getImage();

		img = new ImageIcon(sheet.getSubimage(2 * 32 - 32, 4 * 32 - 32, 32, 32));
		tail = img.getImage();

		img = new ImageIcon(sheet.getSubimage(4 * 32 - 32, 1 * 32 - 32, 32, 32));
		apple = img.getImage();
	}

	public Image getImg_grass() {
		return img_grass;
	}

	public Image getImg_wall() {
		return img_wall;
	}

	public Image getHeadUP() {
		return headUP;
	}

	public Image getHeadDOWN() {
		return headDOWN;
	}

	public Image getHeadLEFT() {
		return headLEFT;
	}

	public Image getHeadRIGHT() {
		return headRIGHT;
	}

	public Image getTail() {
		return tail;
	}

	public Image getApple() {
		return apple;
	}

	public Image getPause() {
		return pause;
	}
}
