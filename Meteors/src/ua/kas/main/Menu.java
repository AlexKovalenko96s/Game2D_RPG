package ua.kas.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ua.kas.main.Game.STATE;
import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.BasicEnemy;
import ua.kas.main.object.HardEnemy;
import ua.kas.main.object.Player;

public class Menu implements MouseListener {

	private Random random;
	private Handler handler;
	private HUD hud;

	public Menu(Handler handler, HUD hud) {
		this.hud = hud;
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

		if (Game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 150, 200, 64)) {
				// Game.gameState = STATE.Game;
				// handler.addObject(new Player((Game.WIDTH / 2) - 16,
				// (Game.HEIGHT / 2) - 16, ObjectId.Player, handler));
				// handler.clearEnemy();
				// handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH -
				// 50), random.nextInt(Game.HEIGHT - 50),
				// ObjectId.BasicEnemy, handler));

				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }

				// AudioPlayer.getSound("clic_sound").play();
				soundClick();
				Game.gameState = STATE.Select;
				return;
			}
			// help button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 250, 200, 64)) {
				Game.gameState = STATE.Help;
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
			}
			// quit button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------

				System.exit(1);
			}
		}

		if (Game.gameState == STATE.Select) {
			// normal button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 16, ObjectId.Player, handler));
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ObjectId.BasicEnemy, handler));
				Game.difficulty = 0;
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------
			}
			// hard button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH / 2) - 16, (Game.HEIGHT / 2) - 16, ObjectId.Player, handler));
				handler.clearEnemy();
				handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ObjectId.BasicEnemy, handler));
				Game.difficulty = 1;
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------
			}
			// back button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------
				return;
			}
		}

		// back button for help
		if (Game.gameState == STATE.Help) {
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------
				return;
			}
		}

		// button try again
		if (Game.gameState == STATE.End) {
			if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, 350, 200, 64)) {
				Game.gameState = STATE.Select;
				hud.setLevel(1);
				hud.setScore(0);
				soundClick();
				// AudioPlayer.getSound("clic_sound").play();

				// --------
				// try {
				// Sound sound = new
				// Sound(this.getClass().getResource("click.wav"));
				// sound.play();
				// } catch (SlickException e1) {
				// e1.printStackTrace();
				// }
				// --------
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// not used
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
		if (Game.gameState == STATE.Menu) {
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
		} else if (Game.gameState == STATE.Help) {
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
		} else if (Game.gameState == STATE.End) {
			Font font1 = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			g.setFont(font1);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", (Game.WIDTH / 2) - 140, 70);

			g.setFont(font3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

			g.setFont(font2);
			g.drawRect((Game.WIDTH / 2) - 100, 350, 200, 64);
			g.drawString("Try Again", (Game.WIDTH / 2) - 65, 350 + 40);
		} else if (Game.gameState == STATE.Select) {
			Font font1 = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font1);
			g.setColor(Color.WHITE);
			g.drawString("SELECT DIFFICULTY", (Game.WIDTH / 2) - 250, 70);

			g.setFont(font2);

			g.drawRect((Game.WIDTH / 2) - 100, 150, 200, 64);
			g.drawString("Normal", (Game.WIDTH / 2) - 45, 150 + 40);

			g.drawRect((Game.WIDTH / 2) - 100, 250, 200, 64);
			g.drawString("Hard", (Game.WIDTH / 2) - 30, 250 + 40);

			g.drawRect((Game.WIDTH / 2) - 100, 350, 200, 64);
			g.drawString("Back", (Game.WIDTH / 2) - 30, 350 + 40);
		}
	}

	private void soundClick() {
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}

		AudioInputStream inputStream;

		try {
			inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("click.wav"));
			clip.open(inputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}