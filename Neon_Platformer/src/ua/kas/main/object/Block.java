package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Game;
import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Block extends GameObject {

	private int type;

	private Texture texture;
	public Game game;

	public Block(float x, float y, ObjectId id, Game game, int type) {
		super(x, y, id);
		this.type = type;
		texture = game.getInstance();
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		if (type == 0) {// dirt block
			g.drawImage(texture.block[0], (int) x, (int) y, null);
		}
		if (type == 1) {// grass block
			g.drawImage(texture.block[1], (int) x, (int) y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}