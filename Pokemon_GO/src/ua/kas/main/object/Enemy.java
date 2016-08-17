package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ua.kas.main.Game;
import ua.kas.main.entity.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	public Enemy(double x, double y) {
		super(x, y);
	}

	@Override
	public void tick() {
		y += speed;
		if (y >= Game.HEIGHT * Game.SCALE) {
			speed = random.nextInt(3) + 1;
			x = random.nextInt(Game.WIDTH * Game.SCALE - 32);
		}
	}

	@Override
	public void render(Graphics g, Image img) {
		g.drawImage(img, (int) x, (int) y, null);
	}
}