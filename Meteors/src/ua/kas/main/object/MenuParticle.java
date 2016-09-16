package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ua.kas.main.Game;
import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class MenuParticle extends GameObject {

	private int dir = 0;

	private Handler handler;
	private Color color;
	private Random random;

	public MenuParticle(float x, float y, ObjectId objectId, Handler handler) {
		super(x, y, objectId);
		this.handler = handler;
		random = new Random();
		width = 16;
		height = 16;

		dir = random.nextInt(2);
		if (dir == 0) {
			velY = 2;
			velX = (random.nextInt(5 - (-5)) + (-5));
		} else if (dir == 1) {
			velX = 2;
			velY = (random.nextInt(5 - (-5)) + (-5));
		}

		color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 42) {
			velY *= -1;
		} else if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}

		handler.addObject(new Trail(x, y, ObjectId.Trail, color, width, height, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
}