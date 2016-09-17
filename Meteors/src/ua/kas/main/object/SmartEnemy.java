package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ObjectId objectId, Handler handler) {
		super(x, y, objectId);
		this.handler = handler;
		width = 16;
		height = 16;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getObjectId() == ObjectId.Player) {
				player = handler.object.get(i);
			}
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		float diffx = x - player.getX() - 8;
		float diffy = y - player.getY() - 8;
		float distance = (float) Math
				.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

		velX = ((-1 / distance) * diffx);
		velY = ((-1 / distance) * diffy);

		// if (y <= 0 || y >= Game.HEIGHT - 42) {
		// velY *= -1;
		// } else if (x <= 0 || x >= Game.WIDTH - 16) {
		// velX *= -1;
		// }

		handler.addObject(new Trail(x, y, ObjectId.Trail, Color.YELLOW, width, height, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
}