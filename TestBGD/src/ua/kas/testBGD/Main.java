package ua.kas.testBGD;

import javax.swing.JFrame;

import ua.kas.testBGD.objects.Player;

public class Main {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	public static JFrame frame;

	public static Main main;
	public static Player player;
	public static Graphics graphics;

	public Main() {
		frame = new JFrame("Test");
		frame.setSize(WIDTH + 6, HEIGHT + 29);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(graphics = new Graphics());
		frame.setVisible(true);

		graphics.repaint();
	}

	public static void main(String[] args) {
		main = new Main();
	}
}