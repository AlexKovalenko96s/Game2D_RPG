package ua.kas.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private double x;
	private double y;

	private double velX = 0;
	private double velY = 0;

	private BufferedImage player = null;

	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		player = game.getPlayer_img();
	}

	public void tick() {
		x += velX;
		y += velY;

		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (x > 640 - 22) {
			x = 640 - 22;
		}
		if (y > 480 - 32) {
			y = 480 - 32;
		}
	}

	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
}
