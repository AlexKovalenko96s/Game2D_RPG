package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import ua.kas.main.Controller;
import ua.kas.main.Game;
import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;
import ua.kas.main.libs.Animation;

public class Enemy extends GameObject implements EntityB {

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	public BufferedImage[] img;

	private Game game;
	private Controller controller;
	public Animation animation;

	public Enemy(double x, double y, BufferedImage[] img, Game game, Controller controller) {
		super(x, y);
		this.img = img;
		this.game = game;
		this.controller = controller;

		animation = new Animation(5, img[0], img[1]);
	}

	@Override
	public void tick() {
		y += speed;
		if (y >= Game.HEIGHT * Game.SCALE) {
			speed = random.nextInt(3) + 1;
			y = -10;
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
		animation.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}