package ua.kas.maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Burns {

	private Image burns;

	private int tileX, tileY;

	public Burns() {

		ImageIcon img = new ImageIcon("res/burns.png");
		burns = img.getImage();

		tileX = 9;
		tileY = 7;

	}

	public Image getBurns() {
		return burns;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void move(int dx, int dy) {

		tileX += dx;
		tileY += dy;

	}

}
