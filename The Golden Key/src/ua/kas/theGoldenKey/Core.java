package ua.kas.theGoldenKey;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Core extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;

	private static JFrame frame;
	public static final int res = 1;
	public static double oY = 0, oX = 0;
	public static int dir = 0;
	public static boolean moving = false, run = false;
	private Image screen;
	public static Dimension screenSize = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(screenSize.width, screenSize.height);
	public static Dimension Size;
	public static String name = "The Golden Key";
	public int targetFPS = 5;

	public Core() {
		setPreferredSize(screenSize);
	}

	public static void main(String[] args) {
		Core core = new Core();
		frame = new JFrame();
		frame.add(core);
		frame.pack();

		Size = new Dimension(frame.getWidth(), frame.getHeight());

		frame.setTitle(name);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		core.start();
	}

	public void start() {
		requestFocus();

		// define classes

		run = true;
		new Thread(this).start();
	}

	public void stop() {
		run = false;
	}

	public void tick() {

	}

	public void render() {

		Graphics g = screen.getGraphics();

		g = this.getGraphics();
		g.drawImage(screen, 0, 0, screenSize.width, screenSize.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}

	@Override
	public void run() {
		screen = createVolatileImage(pixel.width, pixel.height);

		while (run) {

			long start = System.nanoTime();

			tick();
			render();

			long elapsed = System.nanoTime();
			long wait = (1000 / targetFPS) - elapsed / 1000000;

			if (wait < 0) {
				wait = 5;
			}

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
