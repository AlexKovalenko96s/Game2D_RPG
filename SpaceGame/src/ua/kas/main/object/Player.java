package ua.kas.main.object;

import java.awt.Graphics;

import ua.kas.main.SpriteSheet;

public class Player extends GameObject {

	private double velX = 0;
	private double velY = 0;

	private SpriteSheet spriteSheet;

	public Player(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
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
		g.drawImage(spriteSheet.getShip(), (int) x, (int) y, null);
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
