package ua.kas.superMario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ua.kas.superMario.Main;
import ua.kas.superMario.entity.Entity;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		for (Entity en : Main.handler.entity) {

			switch (key) {
			case KeyEvent.VK_W:
				if (!en.jumping) {
					en.jumping = true;
					en.gravity = 10.0;
				}
				break;

			// case KeyEvent.VK_S:
			// en.setVelY(5);
			// break;

			case KeyEvent.VK_A:
				en.setVelX(-5);
				en.facing = 0;
				break;

			case KeyEvent.VK_D:
				en.setVelX(5);
				en.facing = 1;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		for (Entity en : Main.handler.entity) {

			switch (key) {

			// case KeyEvent.VK_W:
			// en.setVelY(0);
			// break;

			// case KeyEvent.VK_S:
			// en.setVelY(0);
			// break;

			case KeyEvent.VK_A:
				en.setVelX(0);
				break;

			case KeyEvent.VK_D:
				en.setVelX(0);
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// not using
	}
}