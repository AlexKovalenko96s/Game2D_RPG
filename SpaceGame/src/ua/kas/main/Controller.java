package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import ua.kas.main.object.Enemy;
import ua.kas.main.object.Entity;

public class Controller {

	private LinkedList<Entity> e = new LinkedList<Entity>();

	private Random random = new Random();

	private Entity entity;
	private SpriteSheet spriteSheet;

	public Controller(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(random.nextInt(640), -10, spriteSheet));
		}
	}

	public void tick() {
		for (int i = 0; i < e.size(); i++) {
			entity = e.get(i);
			entity.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < e.size(); i++) {
			entity = e.get(i);
			entity.render(g);
		}
	}

	public void addEntity(Entity block) {
		e.add(block);
	}

	public void removeEntity(Entity block) {
		e.remove(block);
	}
}
