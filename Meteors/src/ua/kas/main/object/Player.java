package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.Game;
import ua.kas.main.HUD;
import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Player extends GameObject {

	private Handler handler;

	public Player(float x, float y, ObjectId objectId, Handler handler) {
		super(x, y, objectId);
		this.handler = handler;
		width = 32;
		height = 32;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp((int) x, 0, Game.WIDTH - width - 6);
		y = Game.clamp((int) y, 0, Game.HEIGHT - height - 28);

		collision();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, width, height);
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getObjectId() == ObjectId.BasicEnemy || tempObject.getObjectId() == ObjectId.FastEnemy
					|| tempObject.getObjectId() == ObjectId.SmartEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
}