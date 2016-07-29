package ua.kas.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main implements ActionListener {

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int HEIGHT = 829;
	public static final int WIDTH = 966;
	public static final int SCALE = 32;

	public Dimension dim;

	public JFrame frame;

	public Point head;
	public Point apple;

	public static RenderPanel renderPanel;
	public static Main main;

	public Main() {

		frame = new JFrame("Snake v1.1");

		dim = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(renderPanel = new RenderPanel());
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		renderPanel.repaint();
	}

	public static void main(String[] args) {
		main = new Main();
	}
}
