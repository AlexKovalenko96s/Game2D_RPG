package ua.kas.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// not used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (mx >= Game.WIDTH + 220 && mx <= Game.WIDTH + 220 + 80) {
			if (my >= Game.HEIGHT + 60 && my <= Game.HEIGHT + 60 + 30) {
				Game.state = Game.STATE.GAME;
			}
			if (my >= Game.HEIGHT + 100 && my <= Game.HEIGHT + 100 + 30) {

			}
			if (my >= Game.HEIGHT + 140 && my <= Game.HEIGHT + 140 + 30) {
				System.exit(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// not used
	}
}