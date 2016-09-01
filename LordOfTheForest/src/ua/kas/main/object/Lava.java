package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Animation;
import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Lava extends GameObject {

	private int type;
	// 0 = under
	// 1 = up

	public Texture texture;

	private Animation animationLavaUp, animationLavaDown;

	public Lava(float x, float y, ObjectId id, Texture texture, int type) {
		super(x, y, id);
		this.texture = texture;
		this.type = type;
		animationLavaUp = new Animation(10, texture.lava[0], texture.lava[1], texture.lava[2]);
		animationLavaDown = new Animation(10, texture.lava[3], texture.lava[4], texture.lava[5]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if (type == 1) {
			animationLavaUp.runAnimation();
		} else if (type == 0) {
			animationLavaDown.runAnimation();
		}
	}

	@Override
	public void render(Graphics g) {
		if (type == 1) {
			animationLavaUp.drawAnimation(g, (int) x, (int) y);
		} else if (type == 0) {
			animationLavaDown.drawAnimation(g, (int) x, (int) y);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
