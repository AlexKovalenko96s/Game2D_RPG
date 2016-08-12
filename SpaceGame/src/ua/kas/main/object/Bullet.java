package ua.kas.main.object;

import java.awt.Graphics;

import ua.kas.main.SpriteSheet;

public class Bullet extends GameObject implements Entity {

	private SpriteSheet spriteSheet;

	public Bullet(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
	}

	public void tick() {
		y -= 10;
	}

	public void render(Graphics g) {
		g.drawImage(spriteSheet.getBullet(), (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}