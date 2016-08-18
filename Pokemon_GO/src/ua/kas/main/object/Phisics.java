package ua.kas.main.object;

import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;

public class Phisics {

	public static boolean collision(EntityA entityA, EntityB entityB) {
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		return false;
	}

	public static boolean collision(EntityB entityB, EntityA entityA) {
		if (entityB.getBounds().intersects(entityA.getBounds())) {
			return true;
		}
		return false;
	}
}
