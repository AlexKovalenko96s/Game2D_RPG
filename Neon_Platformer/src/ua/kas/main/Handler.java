package ua.kas.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.Block;
import ua.kas.main.object.Flag;
import ua.kas.main.object.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public BufferedImage level2 = null;

	private GameObject gameObject;
	private Camera camera;
	private Game game;

	public BufferedImageLoader loader;

	public Handler(Camera camera, Game game, BufferedImageLoader loader) {
		this.camera = camera;
		this.game = game;
		this.loader = loader;

		loader = new BufferedImageLoader();

		level2 = loader.loadImage("/level2.png");
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

	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) {
					addObject(new Block(xx * 32, yy * 32, ObjectId.Block, 0, game));
				}
				if (red == 0 && green == 0 && blue == 255) {
					addObject(new Player(xx * 32, yy * 32, ObjectId.Player, this, camera, game));
				}
				if (red == 38 && green == 127 && blue == 0) {
					addObject(new Block(xx * 32, yy * 32, ObjectId.Block, 1, game));
				}
				if (red == 255 && green == 216 && blue == 0) {
					addObject(new Flag(xx * 32, yy * 32, ObjectId.Flag));
				}
			}
		}
	}

	public void switchLevel() {
		clearLevel();
		camera.setX(0);
		camera.setY(0);
		switch (Game.LEVEL) {
		case 1:
			loadImageLevel(level2);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "The End");
			break;
		}
		Game.LEVEL++;
	}

	private void clearLevel() {
		object.clear();
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
