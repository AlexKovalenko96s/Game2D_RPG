package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;

import ua.kas.main.framework.GameObject;

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
}
