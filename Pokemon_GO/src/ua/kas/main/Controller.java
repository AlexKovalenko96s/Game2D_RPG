package ua.kas.main;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;
import ua.kas.main.object.Enemy;

public class Controller {

	private ArrayList<EntityA> al_entityA = new ArrayList<EntityA>();
	private ArrayList<EntityB> al_entityB = new ArrayList<EntityB>();

	private Random random = new Random();

	private Game game;
	private SpriteSheet spriteSheet;
	private EntityA entityA;
	private EntityB entityB;

	public Controller(SpriteSheet spriteSheet, Game game) {
		this.spriteSheet = spriteSheet;
		this.game = game;
	}

	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			int n = random.nextInt(4);
			Image img = null;
			if (n == 0) {
				img = spriteSheet.getEnemy1();
			}
			if (n == 1) {
				img = spriteSheet.getEnemy2();
			}
			if (n == 2) {
				img = spriteSheet.getEnemy3();
			}
			if (n == 3) {
				img = spriteSheet.getEnemy4();
			}
			addEntity(new Enemy(random.nextInt(640 - 32), -10, img, game, this));
		}
	}

	public void tick() {
		for (int i = 0; i < al_entityA.size(); i++) {
			entityA = al_entityA.get(i);
			entityA.tick();
		}
		for (int i = 0; i < al_entityB.size(); i++) {
			entityB = al_entityB.get(i);
			entityB.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < al_entityA.size(); i++) {
			entityA = al_entityA.get(i);
			entityA.render(g);
		}
		for (int i = 0; i < al_entityB.size(); i++) {
			entityB = al_entityB.get(i);
			entityB.render(g);
		}
	}

	public void addEntity(EntityA block) {
		al_entityA.add(block);
	}

	public void removeEntity(EntityA block) {
		al_entityA.remove(block);
	}

	public void addEntity(EntityB block) {
		al_entityB.add(block);
	}

	public void removeEntity(EntityB block) {
		al_entityB.remove(block);
	}

	public ArrayList<EntityA> getAl_entityA() {
		return al_entityA;
	}

	public ArrayList<EntityB> getAl_entityB() {
		return al_entityB;
	}

	public void clearAllEntity() {
		al_entityA.clear();
		al_entityB.clear();

	}
}
