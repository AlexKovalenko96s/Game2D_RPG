package ua.kas.maze;

import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map {

	private Scanner scn;

	private Image img_grass, img_wall, img_donuts;

	private String Map[] = new String[14];

	public Map() {

		ImageIcon img = new ImageIcon("res/grass.png");
		img_grass = img.getImage();

		img = new ImageIcon("res/wall.png");
		img_wall = img.getImage();

		img = new ImageIcon("res/donuts.png");
		img_donuts = img.getImage();

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

	public Image getDonuts() {
		return img_donuts;
	}

	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}

	private void openFile() {

		try {
			scn = new Scanner(new File("res/Map.txt"));
		} catch (Exception e) {
			System.err.println("Error Loading Map!");
		}
	}

	private void readFile() {
		while (scn.hasNext()) {
			for (int i = 0; i < 14; i++) {
				Map[i] = scn.next();
			}
		}
	}

	private void closeFile() {
		scn.close();
	}
}