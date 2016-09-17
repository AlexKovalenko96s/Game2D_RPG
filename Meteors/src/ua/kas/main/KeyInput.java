package ua.kas.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ua.kas.main.Game.STATE;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class KeyInput implements KeyListener {

	private boolean[] keyDown = new boolean[4];

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getObjectId() == ObjectId.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[3] = true;
				}
			}
		}
		if (key == KeyEvent.VK_ENTER) {
			if (Game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else if (!Game.paused) {
					Game.paused = true;
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
			if (tempObject.getObjectId() == ObjectId.Player) {
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
				}
				if (key == KeyEvent.VK_A) {
					keyDown[2] = false;
				}
				if (key == KeyEvent.VK_D) {
					keyDown[3] = false;
				}

				// vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				}
				// horizontal movement
				if (!keyDown[2] && !keyDown[3]) {
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