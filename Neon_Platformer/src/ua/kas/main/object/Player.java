package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Player extends GameObject {

	private float width = 32;
	private float height = 64;
	private float gravity = 0.5f;

	private final float MAX_SPEED = 10;

	private Handler handler;

	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		Collision(object);
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
					falling = true;
				}
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
					velX = 0;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 32;
					velX = 0;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		g.setColor(Color.BLACK);
		g.drawRect((int) x, (int) y, (int) width, (int) height);

		/////////// Collision
		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setColor(Color.BLACK);
		// g2d.draw(getBounds());
		// g2d.draw(getBoundsDown());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsRight());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x + ((int) width / 2) / 2, (int) y, (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsDown() {
		return new Rectangle((int) x + ((int) width / 2) / 2, (int) y + 32, (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, 5, (int) height - 10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + (int) width - 5, (int) y + 5, (int) 5, (int) height - 10);
	}

}
