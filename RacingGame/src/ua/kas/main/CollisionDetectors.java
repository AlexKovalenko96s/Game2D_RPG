package ua.kas.main;

// check collision

import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

public class CollisionDetectors {

	public static boolean PolylineIntersection(Polyline shape, Polyline world) {
		Path result = (Path) Shape.intersect(shape, world);
		return !result.getElements().isEmpty();
	}
}