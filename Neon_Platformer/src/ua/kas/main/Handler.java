package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;

import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.Block;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject gameObject;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			gameObject = object.get(i);
			gameObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			gameObject = object.get(i);
			gameObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void createLevel() {
		for (int i = 0; i < (Game.HEIGHT * Game.SCALE + 32 + 10) / 32; i++) {
			addObject(new Block(0, i * 32 - 32, ObjectId.Block));
		}

		for (int i = 0; i < (Game.HEIGHT * Game.SCALE + 32 + 10) / 32; i++) {
			addObject(new Block(Game.WIDTH * Game.SCALE - 32 + 10, i * 32 - 32, ObjectId.Block));
		}

		for (int i = 0; i < (Game.WIDTH * Game.SCALE + 32 + 10) / 32; i++) {
			addObject(new Block(i * 32 - 32, Game.HEIGHT * Game.SCALE + 10 - 32, ObjectId.Block));
		}

		for (int i = 0; i < 6; i++) {
			addObject(new Block((i + 10) * 32 - 32, Game.HEIGHT * 2 + 10 - 32, ObjectId.Block));
		}
	}
}
