package ua.kas.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	private double x;
	private double y;

	public BufferedImage bullet;

	public Bullet(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		bullet = game.getBullet_img();
	}

	public void tick() {
		y -= 10;
	}

	public void render(Graphics g) {
		g.drawImage(bullet, (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}