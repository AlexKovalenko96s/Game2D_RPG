package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.Controller;
import ua.kas.main.Game;
import ua.kas.main.SpriteSheet;
import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;

public class Player extends GameObject implements EntityA {

	private double velX = 0;

	private SpriteSheet spriteSheet;
	private Game game;
	private Controller controller;

	public Player(double x, double y, SpriteSheet spriteSheet, Game game, Controller controller) {
		super(x, y);
		this.spriteSheet = spriteSheet;
		this.game = game;
		this.controller = controller;
	}

	@Override
	public void tick() {
		x += velX;

		if (x <= 0) {
			x = 0;
		}

		if (x >= 640 - 22) {
			x = 640 - 22;
		}

		for (int i = 0; i < game.entityB.size(); i++) {
			EntityB tempEnt = game.entityB.get(i);
			if (Phisics.collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				game.health -= 10;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setScore(game.getScore() + 1);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(spriteSheet.getPlayer(), (int) x, (int) y, null);
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}