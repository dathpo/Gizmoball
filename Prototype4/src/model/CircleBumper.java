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
	private static final double RADIUS = 10;

	public CircleBumper(String gizmoName, double x, double y, Color c) {
		this.x = (L*x)+RADIUS; // Centre coordinates
		this.y = (L*y)+RADIUS;
		this.colour = c;
	}

	public List<LineSegment> getLineSegments() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
		this.circle = new Circle(x, y, RADIUS);
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