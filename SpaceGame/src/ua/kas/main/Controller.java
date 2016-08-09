package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import ua.kas.main.object.Bullet;
import ua.kas.main.object.Enemy;

public class Controller {

	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();

	private Random random = new Random();

	public Bullet tempBullet;
	public Enemy tempEnemy;
	public SpriteSheet spriteSheet;
	public Game game;

	public Controller(Game game, SpriteSheet spriteSheet) {
		this.game = game;
		this.spriteSheet = spriteSheet;

		addEnemy(new Enemy(random.nextInt(Game.WIDTH * Game.SCALE - 32), 0, spriteSheet));
	}

	public void tick() {
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			if (tempBullet.getY() < 0) {
				removeBullet(tempBullet);
			}
			tempBullet.tick();
		}
		for (int i = 0; i < e.size(); i++) {
			tempEnemy = e.get(i);
			tempEnemy.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < b.size(); i++) {
			tempBullet = b.get(i);
			tempBullet.render(g);
		}
		for (int i = 0; i < e.size(); i++) {
			tempEnemy = e.get(i);
			tempEnemy.render(g);
		}
	}

	public void addBullet(Bullet block) {
		b.add(block);
	}

	public void removeBullet(Bullet block) {
		b.remove(block);
	}

	public void addEnemy(Enemy block) {
		e.add(block);
	}

	public void removeEnemy(Enemy block) {
		e.remove(block);
	}
}
