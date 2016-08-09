package ua.kas.main.object;

import java.awt.Graphics;
import java.util.Random;

import ua.kas.main.Game;
import ua.kas.main.SpriteSheet;

public class Enemy {
	private double x;
	private double y;

	private Random random = new Random();

	private SpriteSheet spriteSheet;

	public Enemy(double x, double y, SpriteSheet spriteSheet) {
		this.x = x;
		this.y = y;
		this.spriteSheet = spriteSheet;
	}

	public void tick() {
		y += 1;
		if (y > Game.HEIGHT * Game.SCALE) {
			y = 0;
			x = random.nextInt(Game.WIDTH * Game.SCALE - 32);
		}
	}

	public void render(Graphics g) {
		g.drawImage(spriteSheet.getEnemy(), (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
