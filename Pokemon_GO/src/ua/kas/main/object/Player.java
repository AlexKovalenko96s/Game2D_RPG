package ua.kas.main.object;

import java.awt.Graphics;

import ua.kas.main.SpriteSheet;
import ua.kas.main.entity.EntityA;

public class Player extends GameObject implements EntityA {

	private double velX = 0;

	private SpriteSheet spriteSheet;

	public Player(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
	}

	@Override
	public void tick() {
		x += velX;

		if (x <= 0) {
			x = 0;
		}

		if (x >= 640 - 22) {
			x = 640 - 22;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(spriteSheet.getPlayer(), (int) x, (int) y, null);
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}
}