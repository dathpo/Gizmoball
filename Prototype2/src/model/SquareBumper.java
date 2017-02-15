package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class SquareBumper implements IBumper {     

	private double x,y;
	private Color colour;
	private static final double SIZE = 20;

	public SquareBumper(double x, double y, Color c){
		this.x = x;
		this.y = y;
		this.colour = c;
	}

	public List<LineSegment> getLineSegments() {
		List<LineSegment> lsl = new ArrayList<LineSegment>();
		lsl.add(new LineSegment(x, y, x+SIZE, y));
		lsl.add(new LineSegment(x+SIZE, y, x+SIZE, y+SIZE));
		lsl.add(new LineSegment(x, y+SIZE, x+SIZE, y+SIZE));
		lsl.add(new LineSegment(x, y, x, y+SIZE));
		return lsl;
	}

	public List<Circle> getCircles() {
		List<Circle> cl = new ArrayList<Circle>();
		cl.add(new Circle(x, y, 0));
		cl.add(new Circle(x+SIZE, y, 0));
		cl.add(new Circle(x+SIZE, y+SIZE, 0));
		cl.add(new Circle(x, y+SIZE, 0));
		return cl;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	
}
