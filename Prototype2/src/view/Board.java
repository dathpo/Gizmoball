package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Ball;
import model.IBumper;
import model.Model;
import physics.Circle;

public class Board extends JPanel implements Observer {

	protected static int width;
	protected static int height;
	private Model model;
	private static final int L = 20;

	public Board(int w, int h, Model m) {
		m.addObserver(this);
		width = w;
		height = h;
		model = m;
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
		paintGrid(g);
		paintBall(g);
		paintSquareBumpers(g);
		paintBumpers(g);
	}
	
	public void paintGrid(Graphics g) {
		int rows = 20;
		int columns = 20;
		int htOfRow = height / (rows);
		int wdOfRow = width / (columns);
		for (int k = 0; k <= rows; k++)
			g.drawLine(0, k * htOfRow, width, k * htOfRow );
		for (int k = 0; k <= columns; k++)
			g.drawLine(k*wdOfRow , 0, k*wdOfRow , height);
	}

	public void paintBall(Graphics g) {
		Ball b = model.getBall();
		if (b != null) {
			g.setColor(b.getColour());
			int x = (int) (b.getX() - b.getRadius());
			int y = (int) (b.getY() - b.getRadius());
			int radius = (int) (2 * b.getRadius());
			g.fillOval(x, y, radius, radius);
		}
	}
	
	public void paintSquareBumpers(Graphics g) {
		List<IBumper> b = model.getBumpers();	
	}
	
	public void paintBumpers(Graphics g) {
			for(IBumper bumper : model.getBumpers()){
				List<Circle> circles = bumper.getCircles();
				g.setColor(bumper.getColour());
				if(circles.size()==1){
					int radius = (int)circles.get(0).getRadius();
					g.fillOval((int)circles.get(0).getCenter().x()-radius, (int)circles.get(0).getCenter().y()-radius, radius*2, radius*2);
				
				}
			}
		}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
