package ua.kas.superMario;

import ua.kas.superMario.entity.Entity;

public class Camera {

	public int x, y;

	public void tick(Entity player) {
		setX(-player.getX() - 32 + Main.WIDTH * Main.SCALE / 2);
		setY(-player.getY() - 32 + Main.HEIGHT * Main.SCALE / 2);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
