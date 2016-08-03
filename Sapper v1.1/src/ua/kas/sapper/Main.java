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

	public static final int HEIGHT = 360;
	public static final int WIDTH = 360;
	public static final int MINE = 10;

	public static int[][] counts = new int[9][9];

	public static JButton[][] buttons = new JButton[9][9];

	public static JButton reset = new JButton("Reset");

	public static JFrame frame;

	public static Container grid = new Container();

	public static Main main;
	public static Tile tile;

	public Main() {
		tile = new Tile();
		frame = new JFrame("Sapper v1.1");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Button grid
		grid.setLayout(new GridLayout(9, 9));
		for (int a = 0; a < buttons.length; a++) {
			for (int b = 0; b < buttons[0].length; b++) {
				buttons[a][b] = new JButton();
				buttons[a][b].addActionListener(this);
				grid.add(buttons[a][b]);
			}
		}

		reset.addActionListener(this);
		frame.add(reset, BorderLayout.NORTH);
		frame.add(grid, BorderLayout.CENTER);
		frame.setVisible(true);

		createRandomMines();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(reset)) {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					buttons[x][y].setIcon(null);
				}
			}
			createRandomMines();
		} else {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					if (e.getSource().equals(buttons[x][y])) {
						if (counts[x][y] == MINE) {
							lostGame();
						}
						if (counts[x][y] == 0) {
							buttons[x][y].setIcon(tile.getZero());
						}
						if (counts[x][y] == 1) {
							buttons[x][y].setIcon(tile.getOne());
						}
						if (counts[x][y] == 2) {
							buttons[x][y].setIcon(tile.getTwo());
						}
						if (counts[x][y] == 3) {
							buttons[x][y].setIcon(tile.getThree());
						}
						if (counts[x][y] == 4) {
							buttons[x][y].setIcon(tile.getFour());
						}
					}
				}
			}
		}
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
		counts = new int[9][9];

		for (int i = 0; i < 10; i++) {
			int choice = (int) (Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}

		// initialize neighbor counts
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				if (counts[x][y] != MINE) {
					int neighborcount = 0;
					if (y > 0 && counts[x][y - 1] == MINE) {// up
						neighborcount++;
					}
					if (y < counts[0].length - 1 && counts[x][y + 1] == MINE) {// down
						neighborcount++;
					}
					if (x > 0 && counts[x - 1][y] == MINE) {// left
						neighborcount++;
					}
					if (x < counts.length - 1 && counts[x + 1][y] == MINE) {// right
						neighborcount++;
					}
					if (x > 0 && y > 0 && counts[x - 1][y - 1] == MINE) { // up-left
						neighborcount++;
					}
					if (x < counts.length - 1 && y > 0 && counts[x + 1][y - 1] == MINE) { // up-right
						neighborcount++;
					}
					if (x > 0 && y < counts[0].length - 1 && counts[x - 1][y + 1] == MINE) { // down-left
						neighborcount++;
					}
					if (x < counts.length - 1 && y < counts[0].length - 1 && counts[x + 1][y + 1] == MINE) { // down-right
						neighborcount++;
					}
					counts[x][y] = neighborcount;
				}
			}
		}

	}

	public void lostGame() {
		for (int x = 0; x < buttons.length; x++) {
			for (int y = 0; y < buttons[0].length; y++) {
				if (buttons[x][y].isEnabled()) {
					if (counts[x][y] == MINE) {
						buttons[x][y].setIcon(tile.getMine());
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		main = new Main();
	}
}
