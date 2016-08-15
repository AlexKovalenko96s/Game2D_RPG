package ua.kas.sapper;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tile {

	private ImageIcon mine, zero, one, two, three, four, flag;

	public Tile() {
		try {
			mine = new ImageIcon(ImageIO.read(new File("res/mine.png")));
			zero = new ImageIcon(ImageIO.read(new File("res/0.png")));
			one = new ImageIcon(ImageIO.read(new File("res/1.png")));
			two = new ImageIcon(ImageIO.read(new File("res/2.png")));
			three = new ImageIcon(ImageIO.read(new File("res/3.png")));
			four = new ImageIcon(ImageIO.read(new File("res/4.png")));
			flag = new ImageIcon(ImageIO.read(new File("res/flag.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon getMine() {
		return mine;
	}

	public ImageIcon getZero() {
		return zero;
	}

	public ImageIcon getOne() {
		return one;
	}

	public ImageIcon getTwo() {
		return two;
	}

	public ImageIcon getThree() {
		return three;
	}

	public ImageIcon getFour() {
		return four;
	}

	public ImageIcon getFlag() {
		return flag;
	}
}
