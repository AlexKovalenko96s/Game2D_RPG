package ua.kas.maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;

	private Trap t;
	private Map m;
	private Player p;
	private Burns b;

	private Random random = new Random();

	private boolean win = false;
	private boolean fail = false;

	private String message = " ";

	private Font font = new Font("Serif", Font.BOLD, 48);

	public Board() {

		m = new Map();
		b = new Burns();
		p = new Player();
		t = new Trap();

		addKeyListener(new Al());
		setFocusable(true);

		timer = new Timer(25, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (m.getMap(p.getTileX(), p.getTileY()).equals("d")) {
			message = "Finish!";
			win = true;
		}
		if ((p.getTileX() == t.getTileX() && p.getTileY() == t.getTileY())
				|| p.getTileX() == b.getTileX() && p.getTileY() == b.getTileY()) {
			message = "Fail!";
			fail = true;
		}

		try {
			Thread.currentThread();
			Thread.sleep(60);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Trap moving

		t.move();

		// Burns moving

		int r = random.nextInt(4);

		if (r == 0) {
			if (!m.getMap(b.getTileX(), b.getTileY() - 1).equals("w")
					&& !m.getMap(b.getTileX(), b.getTileY() - 1).equals("d")) {
				b.move(0, -1);
			}
		}

		if (r == 1) {
			if (!m.getMap(b.getTileX(), b.getTileY() + 1).equals("w")
					&& !m.getMap(b.getTileX(), b.getTileY() + 1).equals("d")) {
				b.move(0, 1);
			}
		}

		if (r == 2) {
			if (!m.getMap(b.getTileX() - 1, b.getTileY()).equals("w")
					&& !m.getMap(b.getTileX() - 1, b.getTileY()).equals("d")) {
				b.move(-1, 0);
			}
		}

		if (r == 3) {
			if (!m.getMap(b.getTileX() + 1, b.getTileY()).equals("w")
					&& !m.getMap(b.getTileX() + 1, b.getTileY()).equals("d")) {
				b.move(1, 0);
			}
		}

		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (!win && !fail) {
			for (int y = 0; y < 14; y++) {
				for (int x = 0; x < 14; x++) {
					if (m.getMap(x, y).equals("g")) {
						g.drawImage(m.getGrass(), x * 32, y * 32, null);
					}
					if (m.getMap(x, y).equals("w")) {
						g.drawImage(m.getWall(), x * 32, y * 32, null);
					}
					if (m.getMap(x, y).equals("d")) {
						g.drawImage(m.getDonuts(), x * 32, y * 32, null);
					}
				}
			}

			g.drawImage(p.getPlayer(), p.getTileX() * 32, p.getTileY() * 32, null);
			g.drawImage(t.getTrap(), t.getTileX() * 32, t.getTileY() * 32, null);
			g.drawImage(b.getBurns(), b.getTileX() * 32, b.getTileY() * 32, null);

		}
		if (win || fail) {

			g.setColor(Color.ORANGE);
			g.setFont(font);

			g.drawString(message, 150, 300);

		}
	}

	public class Al implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_W) {
				if (!m.getMap(p.getTileX(), p.getTileY() - 1).equals("w")) {
					p.move(0, -1);
				}
			}

			if (key == KeyEvent.VK_S) {
				if (!m.getMap(p.getTileX(), p.getTileY() + 1).equals("w")) {
					p.move(0, 1);
				}
			}

			if (key == KeyEvent.VK_A) {
				if (!m.getMap(p.getTileX() - 1, p.getTileY()).equals("w")) {
					p.move(-1, 0);
				}
			}

			if (key == KeyEvent.VK_D) {
				if (!m.getMap(p.getTileX() + 1, p.getTileY()).equals("w")) {
					p.move(1, 0);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
}
