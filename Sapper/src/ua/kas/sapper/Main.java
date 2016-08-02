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

	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;
	public static final int MINE = 10;

	public static JButton reset = new JButton("Reset");

	public static JButton[][] buttons = new JButton[20][20];

	public static int[][] counts = new int[20][20];

	public static JFrame frame;

	public static Container grid = new Container();

	public static Tile tile = new Tile();

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
						if (counts[x][y] == 0) {
							buttons[x][y].setIcon(tile.getZero());
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add(x * 100 + y);
							clearZeros(toClear);
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
						if (counts[x][y] == MINE) {
							buttons[x][y].setIcon(tile.getMine());
							lostGame();
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
		counts = new int[20][20];
		for (int a = 0; a < 1; a++) {
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
					if (x > 0 && counts[x - 1][y] == MINE) {// left
						neighborcount++;
					}
					if (x < counts.length - 1 && counts[x + 1][y] == MINE) {// right
						neighborcount++;
					}
					if (y < counts[0].length - 1 && counts[x][y + 1] == MINE) {// down
						neighborcount++;
					}
					if (x > 0 && y > 0 && counts[x - 1][y - 1] == MINE) {// up/left
						neighborcount++;
					}
					if (x < counts.length - 1 && y > 0 && counts[x + 1][y - 1] == MINE) {// up/right
						neighborcount++;
					}
					if (x < counts.length - 1 && y < counts[0].length - 1 && counts[x + 1][y + 1] == MINE) {// down/right
						neighborcount++;
					}
					if (x > 0 && y < counts[0].length - 1 && counts[x - 1][y + 1] == MINE) {// down/left
						neighborcount++;
					}
					counts[x][y] = neighborcount;
				}
			}
		}
	}

	public void number(int x, int y) {
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
	}

	public void clearZeros(ArrayList<Integer> toClear) {
		if (toClear.size() == 0) {
			return;
		} else {
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
			if (x > 0 && y > 0 && buttons[x - 1][y - 1].isEnabled()) {// up/left
				number(x - 1, y - 1);
				if (counts[x - 1][y - 1] == 0) {
					toClear.add((x - 1) * 100 + (y - 1));
				}
			}
			if (y > 0 && buttons[x][y - 1].isEnabled()) { // up
				number(x, y - 1);
				if (counts[x][y - 1] == 0) {
					toClear.add(x * 100 + y - 1);
				}
			}
			if (x < counts.length - 1 && y > 0 && buttons[x + 1][y - 1].isEnabled()) {// up/right
				number(x + 1, y - 1);
				if (counts[x + 1][y - 1] == 0) {
					toClear.add((x + 1) * 100 + (y - 1));
				}
			}
			if (x > 0 && y < counts[0].length - 1 && buttons[x - 1][y + 1].isEnabled()) {// down/left
				number(x - 1, y + 1);
				if (counts[x - 1][y + 1] == 0) {
					toClear.add((x - 1) * 100 + (y + 1));
				}
			}
			if (y < counts[0].length - 1 && buttons[x][y + 1].isEnabled()) { // down
				number(x, y + 1);
				if (counts[x][y + 1] == 0) {
					toClear.add(x * 100 + y + 1);
				}
			}
			if (x < counts.length - 1 && y < counts[0].length - 1 && buttons[x + 1][y + 1].isEnabled()) {// down/right
				number(x + 1, y + 1);
				if (counts[x + 1][y + 1] == 0) {
					toClear.add((x + 1) * 100 + (y + 1));
				}
			}
			if (x > 0 && buttons[x - 1][y].isEnabled()) { // left
				number(x - 1, y);
				if (counts[x - 1][y] == 0) {
					toClear.add((x - 1) * 100 + y);
				}
			}
			if (x < counts.length - 1 && buttons[x + 1][y].isEnabled()) { // right
				number(x + 1, y);
				if (counts[x + 1][y] == 0) {
					toClear.add((x + 1) * 100 + y);
				}
			}
			clearZeros(toClear);
		}
	}

	public void lostGame() {
		for (int x = 0; x < buttons.length; x++) {
			for (int y = 0; y < buttons[0].length; y++) {
				if (buttons[x][y].isEnabled()) {
					// number(x, y);
					if (counts[x][y] == MINE) {
						buttons[x][y].setIcon(tile.getMine());
					} else {
						// buttons[x][y].setEnabled(false);
					}

				}
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}