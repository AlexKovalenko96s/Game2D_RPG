package ua.kas.snake;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Map {

	private Scanner scn;

	private BufferedImage sheet;
	private Image img_grass, img_wall;

	private String Map[] = new String[25];

	public Map() {

		try {
			sheet = ImageIO.read(new File("res/snake.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon img = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		img_grass = img.getImage();

		img = new ImageIcon(sheet.getSubimage(3 * 32 - 32, 1 * 32 - 32, 32, 32));
		img_wall = img.getImage();

		openFile();
		readFile();
		closeFile();
	}

	public Image getGrass() {
		return img_grass;
	}

	public Image getWall() {
		return img_wall;
	}

	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}

	private void openFile() {

		try {
			scn = new Scanner(new File("res/level1.txt"));
		} catch (Exception e) {
			System.err.println("Error Loading Map!");
		}
	}

	private void readFile() {
		while (scn.hasNext()) {
			for (int i = 0; i < 25; i++) {
				Map[i] = scn.next();
			}
		}
	}

	private void closeFile() {
		scn.close();
	}
}