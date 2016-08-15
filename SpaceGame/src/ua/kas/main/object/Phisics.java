package ua.kas.main.object;

import java.util.LinkedList;

import ua.kas.main.classes.EntityA;
import ua.kas.main.classes.EntityB;

public class Phisics {

	public static boolean Collision(EntityA antityA, LinkedList<EntityB> entityB) {
		for (int i = 0; i < entityB.size(); i++) {
			if (antityA.getBounds().intersects(entityB.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}

	public static boolean Collision(EntityB antityB, LinkedList<EntityA> entityA) {
		for (int i = 0; i < entityA.size(); i++) {
			if (antityB.getBounds().intersects(entityA.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}
}
