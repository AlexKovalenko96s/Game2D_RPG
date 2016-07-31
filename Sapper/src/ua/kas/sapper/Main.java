package ua.kas.sapper;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main implements ActionListener {

	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final int MINE = 10;

	public static JButton reset = new JButton("Reset");

	public static JButton[][] buttons = new JButton[20][20];

	public static int[][] counts = new int[20][20];

	public static JFrame frame;

	public static Container grid = new Container();

	public Main() {
		frame = new JFrame("Sapper");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		// Button grid

		grid.setLayout(new GridLayout(20, 20));
		for (int a = 0; a < buttons.length; a++) {
			for (int b = 0; b < buttons[0].length; b++) {
				buttons[a][b] = new JButton();
				buttons[a][b].addActionListener(this);
				grid.add(buttons[a][b]);
			}
		}

		frame.add(reset, BorderLayout.NORTH);
		frame.add(grid, BorderLayout.CENTER);
		reset.addActionListener(this);

		frame.setVisible(true);

		createRandomMines();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	public void createRandomMines() {
		// Initialize list of random pairs
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				list.add(x * 100 + y);
			}
		}

		// reset counts, pick out 30 mines
		counts = new int[20][20];
		for (int a = 0; a < 30; a++) {
			int choice = (int) (Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}