package ua.kas.main;

import ua.kas.main.framework.GameObject;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject objectPlayer) {
		x = -objectPlayer.getX() + (Game.WIDTH * Game.SCALE) / 2;
		y = -objectPlayer.getY() + (Game.HEIGHT * Game.SCALE) / 2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
