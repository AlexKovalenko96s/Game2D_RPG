package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Lava extends GameObject {

	Texture texture;

	public Lava(float x, float y, ObjectId id, Texture texture) {
		super(x, y, id);
		this.texture = texture;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// not used
	}

	@Override
	public void render(Graphics g) {
		// not used
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
