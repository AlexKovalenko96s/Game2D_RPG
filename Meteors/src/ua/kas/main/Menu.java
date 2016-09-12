package ua.kas.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import ua.kas.main.Game.STATE;
import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.BasicEnemy;
import ua.kas.main.object.Player;

public class Menu implements MouseListener {

	private Random random;
	private Game game;
	private Handler handler;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		random = new Random();
	}

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

		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 16, ObjectId.Player, handler));
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT),
						ObjectId.BasicEnemy, handler));
			}
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				System.exit(1);
			}
		}

		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void tick() {
		// not used
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font font1 = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font1);
			g.setColor(Color.WHITE);
			g.drawString("Menu", (Game.WIDTH / 2) - 100 + 30, 70);

			g.setFont(font2);

			g.drawRect((Game.WIDTH / 2) - 100, 150, 200, 64);
			g.drawString("Play", (Game.WIDTH / 2) - 30, 150 + 40);

			g.drawRect((Game.WIDTH / 2) - 100, 250, 200, 64);
			g.drawString("Help", (Game.WIDTH / 2) - 30, 250 + 40);

			g.drawRect((Game.WIDTH / 2) - 100, 350, 200, 64);
			g.drawString("Quit", (Game.WIDTH / 2) - 30, 350 + 40);
		} else if (game.gameState == STATE.Help) {
			Font font1 = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			g.setFont(font1);
			g.setColor(Color.WHITE);
			g.drawString("Help", (Game.WIDTH / 2) - 100 + 30, 70);

			g.setFont(font3);
			g.drawString("Use WASD keys to move player and dodge enemies.", 70, 200);

			g.setFont(font2);
			g.drawRect((Game.WIDTH / 2) - 100, 350, 200, 64);
			g.drawString("Back", (Game.WIDTH / 2) - 30, 350 + 40);
		}
	}
}