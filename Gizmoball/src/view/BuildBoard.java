package view;

import java.awt.Graphics;

import model.Model;

public class BuildBoard extends Board {

	public BuildBoard(Model m) {
		super(height, height, m);
	}
	
	public void paintComponent(Graphics g) {
	
	paintGrid(g);
	}
	
public void paintGrid(Graphics g) {
		
		for (int k=0; k<=L; k++) {
			g.drawLine(0, k*height/L, width, k*height/L);
		}
		for (int k=0; k<=L; k++) {
			g.drawLine(k*width/L, 0, k*width/L, height);
		}
	}

}
