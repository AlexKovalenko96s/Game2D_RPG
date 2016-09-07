package ua.kas.main.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ua.kas.main.Game;
import ua.kas.main.Handler;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class EnemyBoss extends GameObject {

	private int timer = 80;
	private int timer2 = 50;

	private Random random;

	public Handler handler;

	public EnemyBoss(float x, float y, ObjectId objectId, Handler handler) {
		super(x, y, objectId);
		this.handler = handler;
		random = new Random();
		width = 96;
		height = 96;

		velY = 2;
		velX = 0;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (timer <= 0) {
			velY = 0;
		} else {
			timer--;
		}

		if (timer <= 0) {
			timer2--;
		}
		if (timer2 <= 0) {
			if (velX == 0) {
				velX = 2;
			}

			int spawn = random.nextInt(10);

			if (spawn == 0) {
				handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ObjectId.BasicEnemy, handler));
			}
		}

		// if (y <= 0 || y >= Game.HEIGHT - 42) {
		// velY *= -1;
		// } else
		if (x <= 0 || x >= Game.WIDTH - 96) {
			velX *= -1;
		}

		// handler.addObject(new Trail(x, y, ObjectId.Trail, Color.RED, width,
		// height, 0.01f, handler));
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