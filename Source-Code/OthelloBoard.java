import javax.swing.*;
import java.awt.*;
import java.lang.*;

public class OthelloBoard extends JFrame {
	private final int SIZE = 8;
	private int[][] board = new int[SIZE][SIZE];
	public JPanel cellpanel;
	private CellDisplay[][] celldisplay = new CellDisplay[SIZE][SIZE];
	
	public OthelloBoard() {
		setTitle("Othello");
		setSize(600,600);
		cellpanel = new JPanel();
		cellpanel.setLayout(new GridLayout(8,8,1,1));
		setLocationRelativeTo(null);
		setResizable(false);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				celldisplay[i][j] = new CellDisplay();
				cellpanel.add(celldisplay[i][j]);
				board[i][j] = 0;
			}
		}
		add(cellpanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void placePiece(int color, int row, int col) {
		board[row][col] = color;
	}
	
	public void resetBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = 0;
			}
		}
		placePiece(1,3,3);
		placePiece(2,3,4);
		placePiece(2,4,3);
		placePiece(1,4,4);
	}
	
	public int countPiece(int color) {
		int result = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == color) result++;
			}
		}
		return result;
	}
	
	public int countLegalMove(int color) {
		int result = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (legalMove(color,i,j)) result++;
			}
		}
		return result;
	}
	
	public int getCellHeight() {
		return celldisplay[0][0].getHeight();
	}
	
	public int getCellWidth() {
		return celldisplay[0][0].getWidth();
	}

	public void repaintBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				celldisplay[i][j].setColor(board[i][j]);
			}
		}
	}
	
	public int checkMove(int color, int row, int col, int dx, int dy) {
		int result = 0;
		int x = row + dx;
		int y = col + dy;
		int opcolor = (color == 1) ? 2 : 1;
		try {
			if (board[x][y] == 0) return 0;
			else if (board[x][y] == color) return 0;
			else {
				while (board[x][y] == opcolor) {
					result++;
					x = x + dx;
					y = y + dy;
					if (board[x][y] == 0) result = 0;
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
		return result;
	}
	
	public boolean legalMove(int color, int row, int col) {
		if (board[row][col] != 0) return false;
		int numflip = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) numflip = numflip + checkMove(color,row,col,i,j);
			}
		}
		if (numflip > 0) return true;
		else return false;
	}
	
	public void flipDirection(int color, int row, int col, int dx, int dy) {
		int numflip = checkMove(color,row,col,dx,dy);
		if (numflip == 0) return;
		else {
			int x = row + dx;
			int y = col + dy;
			int opcolor = (color == 1) ? 2 : 1;
			while (board[x][y] == opcolor) {
				board[x][y] = color;
				x = x + dx;
				y = y + dy;
			}
		}
	}
	
	public void flip(int color, int row, int col) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) flipDirection(color,row,col,i,j);
			}
		}
	}	
}
