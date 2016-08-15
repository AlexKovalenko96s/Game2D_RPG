package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.Game;
import ua.kas.main.SpriteSheet;
import ua.kas.main.classes.EntityA;

public class Player extends GameObject implements EntityA {

	private double velX = 0;
	private double velY = 0;

	private SpriteSheet spriteSheet;

	private Game game;

	public Player(double x, double y, SpriteSheet spriteSheet, Game game) {
		super(x, y);
		this.spriteSheet = spriteSheet;
		this.game = game;
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

		if (Phisics.Collision(this, game.eb)) {
			System.err.println("CRASH");
		}
	}

	public void render(Graphics g) {
		g.drawImage(spriteSheet.getShip(), (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
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
