package ua.kas.maze;

import javax.swing.JFrame;

public class Maze {

	public Maze() {
		JFrame f = new JFrame();
		f.setTitle("Maze");
		f.add(new Board());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(454, 475);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new Maze();
	}
}
