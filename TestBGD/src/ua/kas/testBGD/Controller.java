package ua.kas.testBGD;

import java.awt.Graphics;
import java.util.ArrayList;

import ua.kas.testBGD.objects.Enemy;

public class Controller {

	public static ArrayList<Enemy> e = new ArrayList<Enemy>();

	public Enemy enemy;

	public Controller() {
		addEnemy(new Enemy(50, 50));
		addEnemy(new Enemy(150, 150));
		addEnemy(new Enemy(250, 250));
		addEnemy(new Enemy(350, 350));
		addEnemy(new Enemy(450, 450));
	}

	public void update() {
		for (int i = 0; i < e.size(); i++) {
			enemy = e.get(i);
			enemy.update();
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < e.size(); i++) {
			enemy = e.get(i);
			enemy.draw(g);
		}
	}

	public void addEnemy(Enemy enemy) {
		e.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		e.remove(enemy);
	}

	public static ArrayList<Enemy> getEnemyBounds() {
		return e;
	}
}
