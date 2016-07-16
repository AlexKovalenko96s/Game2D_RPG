package ua.kas.maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Trap {

	private int tileX, tileY;

	private boolean end = false, step = false;

	private Image trap;

	public Trap() {

		ImageIcon img = new ImageIcon("res/trap.png");
		trap = img.getImage();

		tileX = 1;
		tileY = 5;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Image getTrap() {
		return trap;
	}

	public boolean getStep() {
		return step;
	}

	public void move() {

		if (!end) {
			tileX++;
			if (tileX == 8) {
				end = true;
			}
		}

		if (end) {
			if (tileX == 1) {
				end = false;
			} else {
				tileX--;
			}
		}
	}
}