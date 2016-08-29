package ua.kas.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.Block;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject gameObject;
	private Game game;

	public Handler(Game game) {
		this.game = game;
	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			gameObject = object.get(i);
			gameObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			gameObject = object.get(i);
			gameObject.render(g);
		}
	}

	public void loadImageLoad(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) {// land
					addObject(new Block(xx * 32, yy * 32, ObjectId.Block, 0, game));
				}
				if (red == 38 && green == 127 && blue == 0) {// grass
					addObject(new Block(xx * 32, yy * 32, ObjectId.Block, 1, game));
				}
			}
		}
	}

	public void clearObject() {
		object.clear();
	}

	public void addObject(GameObject tempObject) {
		this.object.add(tempObject);
	}

	public void removeObject(GameObject tempObject) {
		this.object.remove(tempObject);
	}
}
