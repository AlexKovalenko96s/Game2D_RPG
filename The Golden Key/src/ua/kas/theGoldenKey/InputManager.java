package ua.kas.theGoldenKey;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_UP:
			Core.player.isMoving = true;
			Core.player.up = true;
			break;

		case KeyEvent.VK_DOWN:
			Core.player.isMoving = true;
			Core.player.down = true;
			break;

		case KeyEvent.VK_LEFT:
			Core.player.isMoving = true;
			Core.player.left = true;
			break;

		case KeyEvent.VK_RIGHT:
			Core.player.isMoving = true;
			Core.player.right = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_UP:
			Core.player.isMoving = false;
			Core.player.up = false;
			break;

		case KeyEvent.VK_DOWN:
			Core.player.isMoving = false;
			Core.player.down = false;
			break;

		case KeyEvent.VK_LEFT:
			Core.player.isMoving = false;
			Core.player.left = false;
			break;

		case KeyEvent.VK_RIGHT:
			Core.player.isMoving = false;
			Core.player.right = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
