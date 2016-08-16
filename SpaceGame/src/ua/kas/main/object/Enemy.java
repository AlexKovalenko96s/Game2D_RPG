package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ua.kas.main.Controller;
import ua.kas.main.Game;
import ua.kas.main.SpriteSheet;
import ua.kas.main.classes.EntityA;
import ua.kas.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	private SpriteSheet spriteSheet;
	private Game game;
	private Controller controller;

	public Enemy(double x, double y, SpriteSheet spriteSheet, Game game, Controller controller) {
		super(x, y);
		this.spriteSheet = spriteSheet;
		this.game = game;
		this.controller = controller;
	}

	public void tick() {
		y += speed;
		if (y > Game.HEIGHT * Game.SCALE) {
			speed = random.nextInt(3) + 1;
			y = -10;
			x = random.nextInt(Game.WIDTH * Game.SCALE - 32);
		}

		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			if (Phisics.Collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				controller.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(spriteSheet.getEnemy(), (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
