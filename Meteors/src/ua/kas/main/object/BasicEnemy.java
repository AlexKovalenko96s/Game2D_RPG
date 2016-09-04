package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.Game;
import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class BasicEnemy extends GameObject {

	private Handler handler;

	public BasicEnemy(float x, float y, ObjectId objectId, Handler handler) {
		super(x, y, objectId);
		this.handler = handler;
		width = 16;
		height = 16;

		velY = 5;
		velX = 5;
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

		handler.addObject(new Trail(x, y, ObjectId.Trail, Color.RED, width, height, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
}