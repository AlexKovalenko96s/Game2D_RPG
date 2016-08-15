package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import ua.kas.main.classes.EntityA;
import ua.kas.main.classes.EntityB;
import ua.kas.main.object.Enemy;

public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();

	private Random random = new Random();

	private EntityA entityA;
	private EntityB entityB;

	private Game game;
	private SpriteSheet spriteSheet;

	public Controller(SpriteSheet spriteSheet, Game game) {
		this.spriteSheet = spriteSheet;
		this.game = game;
	}

	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(random.nextInt(640 - 32), -10, spriteSheet, game, this));
		}
	}

	public void tick() {
		// A class
		for (int i = 0; i < ea.size(); i++) {
			entityA = ea.get(i);
			entityA.tick();
		}

		// B class
		for (int i = 0; i < eb.size(); i++) {
			entityB = eb.get(i);
			entityB.tick();
		}
	}

	public void render(Graphics g) {
		// A class
		for (int i = 0; i < ea.size(); i++) {
			entityA = ea.get(i);
			entityA.render(g);
		}

		// B class
		for (int i = 0; i < eb.size(); i++) {
			entityB = eb.get(i);
			entityB.render(g);
		}
	}

	public void addEntity(EntityA block) {
		ea.add(block);
	}

	public void removeEntity(EntityA block) {
		ea.remove(block);
	}

	public void addEntity(EntityB block) {
		eb.add(block);
	}

	public void removeEntity(EntityB block) {
		eb.remove(block);
	}

	public LinkedList<EntityA> getEa() {
		return ea;
	}

	public LinkedList<EntityB> getEb() {
		return eb;
	}
}
