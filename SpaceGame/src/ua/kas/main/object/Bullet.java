package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.SpriteSheet;
import ua.kas.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {

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