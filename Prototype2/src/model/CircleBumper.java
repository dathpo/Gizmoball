package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class CircleBumper implements IBumper { 

	private Circle circle;
	private double x, y;
	private Color colour;
	private static final double L = 20;
	private static final int RADIUS = 10;

	public CircleBumper(double x, double y, Color c) {
		this.x = x; // Centre coordinates
		this.y = y;
		this.circle = new Circle((L*x)+RADIUS, (L*y)+RADIUS, RADIUS);
		this.colour = c;
	}

	public List<LineSegment> getLineSegments() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(circle);
		return circles;
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
