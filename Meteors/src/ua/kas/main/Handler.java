package ua.kas.main;

import java.awt.Graphics;
import java.util.LinkedList;

import ua.kas.main.Game.STATE;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemy() {
		int counter = object.size();
		for (int i = 0; i < counter; i++) {
			GameObject tempObject = object.get(i);
			if (Game.gameState == STATE.End) {
				removeObject(tempObject);
				i--;
				counter--;
			} else {
				if (tempObject.getObjectId() != ObjectId.Player) {
					removeObject(tempObject);
					i--;
					counter--;
				}
			}
		}
	}
}
