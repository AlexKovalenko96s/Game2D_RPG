package ua.kas.main.object;

import ua.kas.main.classes.EntityA;
import ua.kas.main.classes.EntityB;

public class Phisics {

	public static boolean Collision(EntityA entityA, EntityB entityB) {
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		return false;
	}

	public static boolean Collision(EntityB entityB, EntityA entityA) {
		if (entityB.getBounds().intersects(entityA.getBounds())) {
			return true;
		}
		return false;
	}
}
