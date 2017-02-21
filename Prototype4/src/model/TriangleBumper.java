package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class TriangleBumper implements IBumper {
	private double x, y;
	private Color colour;
	private static final double L = 20;
	
	public TriangleBumper (double x, double y, Color c) {
	this.x = x*L;
    this.y = y*L;
    this.colour = c;
	}
	
	@Override
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,	x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y,	x+L,	y));
		lineSegments.add(new LineSegment(x+L,	y+L,	x+L,	y));
		return lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x, 	y, 	0));
		circles.add(new Circle(x+L,	y, 		0));
		circles.add(new Circle(x+L,	y+L,	0));
		return circles;
	}

	@Override
	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public Color getColour() {
		return colour;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
   
}