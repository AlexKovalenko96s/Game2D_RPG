package ua.kas.maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Trap {

	private int tileX, tileY;

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

	public void move() throws InterruptedException {

		System.out.println(tileX);

		if (tileX == 1) {
			for (int i = tileX; i < 7; i++) {
				Thread t1 = null;
				t1.sleep(100);
				tileX += 1;
			}
		}
		if (tileX == 7) {
			for (int i = tileX; i > 1; i--) {
				Thread t2 = null;
				t2.sleep(100);
				tileX -= 1;
			}
		}
	}
}