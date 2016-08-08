package ua.kas.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		game.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		game.keyReleased(e);
	}
}
