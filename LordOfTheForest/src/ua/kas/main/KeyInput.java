package ua.kas.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class KeyInput implements KeyListener {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
				}
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-13);
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used
	}
}