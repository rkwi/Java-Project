import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class OthelloGame {
	private OthelloBoard othelloboard;
	private int turncolor;
	private JFrame gameframe = new JFrame();
	private JLabel gamelabel = new JLabel();
	private JMenuBar mainBar = new JMenuBar();
	private JMenu menu = new JMenu("Menu");
	private JMenuItem newgame = new JMenuItem("New game");
	
	public OthelloGame() {
		othelloboard = new OthelloBoard();
		mainBar.add(menu);
		menu.add(newgame);
		othelloboard.setJMenuBar(mainBar);
		fillGameFrame();
		gameframe.setVisible(true);
		newgame.addActionListener(new NewGameListener());
		othelloboard.cellpanel.addMouseListener(new MouseControl());
	}
	
	public void move(int color, int row, int col) {
		othelloboard.placePiece(color,row,col);
		othelloboard.flip(color,row,col);
		othelloboard.repaintBoard();
	}
	
	public void endTurn(int color) {
		turncolor = (color == 1) ? 2 : 1;
		setTurnLabel(turncolor);
	}
	
	public void checkGame() {
		if (othelloboard.countLegalMove(1) == 0 && othelloboard.countLegalMove(2) == 0) {
			if (othelloboard.countPiece(1) > othelloboard.countPiece(2)) gamelabel.setText("White wins!");
			else if (othelloboard.countPiece(1) == othelloboard.countPiece(2)) gamelabel.setText("Draw!");
			else gamelabel.setText("Black wins!");
		}
	}
	
	public void fillGameFrame() {
		gameframe.setSize(150,150);
		gameframe.setResizable(false);
		gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameframe.add(gamelabel);
	}
	
	public void setTurnLabel(int color) {
		if (color == 1) gamelabel.setText("Turn: White");
		else if (color == 2) gamelabel.setText("Turn: Black");
	}
	
	class MouseControl implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int row = e.getY()/othelloboard.getCellHeight();
			int col = e.getX()/othelloboard.getCellWidth();
			if (othelloboard.legalMove(turncolor,row,col)) {
				move(turncolor,row,col);
				checkGame();
				int opcolor = (turncolor == 1) ? 2 : 1;
				if (othelloboard.countLegalMove(opcolor) != 0) {
					endTurn(turncolor);
				}
			}
		}
		
		public void mouseEntered(MouseEvent e) {
		}
		
		public void mouseExited(MouseEvent e) {
		}
		
		public void mousePressed(MouseEvent e) {
		}
	
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			othelloboard.resetBoard();
			othelloboard.repaintBoard();
			turncolor = 1;
			setTurnLabel(turncolor);
		}
	}
	
	public static void main(String[] args) {
		OthelloGame g = new OthelloGame();
	}
}
