package view;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


import model.Model;
import model.VerticalLine;
import model.HorizontalLine;


public class BuildBoard extends JPanel implements Observer {

<<<<<<< HEAD
	/** Created by Greig Cairns
=======
	/**
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;

	public BuildBoard(int w, int h, Model m) {

		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// Draw all the vertical lines
		for (VerticalLine vl : gm.getLinesVertical()) {
			g2.fillRect(vl.getX(), vl.getY(), 1, vl.getHeight());
		}
		// Draw all the horizontal lines
		for (HorizontalLine hl : gm.getLinesHorizontal()) {
			g2.fillRect(hl.getX(), hl.getY(), hl.getWidth(), 1);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
