package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import ua.kas.main.Controller;
import ua.kas.main.Game;
import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	private Image img;

	private Game game;
	private Controller controller;

	public Enemy(double x, double y, Image img, Game game, Controller controller) {
		super(x, y);
		this.img = img;
		this.game = game;
		this.controller = controller;
	}

	@Override
	public void tick() {
		y += speed;
		if (y >= Game.HEIGHT * Game.SCALE) {
			speed = random.nextInt(3) + 1;
			y = 0;
			x = random.nextInt(Game.WIDTH * Game.SCALE - 32);
		}

		for (int i = 0; i < game.entityA.size(); i++) {
			EntityA tempEnt = game.entityA.get(i);
			if (Phisics.collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				controller.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setScore(game.getScore() + 1);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}