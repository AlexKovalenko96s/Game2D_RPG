package ua.kas.main;

import ua.kas.main.framework.GameObject;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject player) {
		x = -player.getX() + (Game.WIDTH * Game.SCALE) / 2;
		y = -player.getY() + (Game.HEIGHT * Game.SCALE) / 2;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
