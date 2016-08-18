package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.SpriteSheet;
import ua.kas.main.entity.EntityA;

public class Shot extends GameObject implements EntityA {

	private SpriteSheet spriteSheet;

	public Shot(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
	}

	@Override
	public void tick() {
		y -= 10;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(spriteSheet.getShot(), (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}