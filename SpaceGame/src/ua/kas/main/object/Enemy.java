package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ua.kas.main.Game;
import ua.kas.main.SpriteSheet;
import ua.kas.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	private SpriteSheet spriteSheet;

	public Enemy(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
	}

	public void tick() {
		y += speed;
		if (y > Game.HEIGHT * Game.SCALE) {
			speed = random.nextInt(3) + 1;
			y = -10;
			x = random.nextInt(Game.WIDTH * Game.SCALE - 32);
		}
	}

	public void render(Graphics g) {
		g.drawImage(spriteSheet.getEnemy(), (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
