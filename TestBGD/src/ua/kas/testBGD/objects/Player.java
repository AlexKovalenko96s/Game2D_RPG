package ua.kas.testBGD.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ua.kas.testBGD.Controller;

public class Player extends GlobalPosition implements KeyListener {

	private BufferedImage sheet;

	public static ArrayList<Enemy> e = Controller.getEnemyBounds();

	public int velX;
	public int velY;

	public Player(int x, int y) {
		super(x, y);
	}

	public void update() {
		x += velX;
		y += velY;
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (x > 608) {
			x = 608;
		}
		if (y > 448) {
			y = 448;
		}
		collision();
	}

	public void collision() {
		for (int i = 0; i < e.size(); i++) {
			if (getBounds().intersects(e.get(i).getBounds())) {
				System.out.println("COLLISION!!");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			velY = -5;
		}
		if (key == KeyEvent.VK_S) {
			velY = +5;
		}
		if (key == KeyEvent.VK_A) {
			velX = -5;
		}
		if (key == KeyEvent.VK_D) {
			velX = +5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			velY = 0;
		}
		if (key == KeyEvent.VK_S) {
			velY = 0;
		}
		if (key == KeyEvent.VK_A) {
			velX = 0;
		}
		if (key == KeyEvent.VK_D) {
			velX = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void draw(Graphics g) {
		g.drawImage(getPlayerImage(), x + velX, y + velY, null);
	}

	public Image getPlayerImage() {
		try {
			sheet = ImageIO.read(new File("res/piratesTest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon i = new ImageIcon(sheet.getSubimage(1 * 32 - 32, 1 * 32 - 32, 32, 32));
		return i.getImage();
	}
}