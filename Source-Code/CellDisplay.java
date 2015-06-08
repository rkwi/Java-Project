import javax.swing.*;
import java.awt.*;

public class CellDisplay extends JPanel {
	private int color;
	public CellDisplay() {
		color = 0;
		setBackground(Color.GREEN);
	}
	
	public void setColor (int color) {
		this.color = color;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (color == 1) {
			g.setColor(Color.WHITE);
			g.fillOval(5,5,getWidth() - 10,getHeight() - 10);
		}
		else if (color == 2) {
			g.setColor(Color.BLACK);
			g.fillOval(5,5,getWidth() - 10,getHeight() - 10) ;
		}
	}
}
