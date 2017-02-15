package model;

import java.awt.Color;

import physics.Circle;
import physics.LineSegment;

public class CircleBumper implements IBumper { 

	private Circle circle;
	private double radius, x, y;
	private Color colour;

	public CircleBumper(double x, double y, double r, Color c) {
		this.x = x; // Centre coordinates
		this.y = y;
		this.radius = r;
		this.circle = new Circle(x, y, r);
		this.colour = c;

	}

	public Circle getCircle() {
		return new Circle(x, y, radius);
	}

	public double getRadius() {
		return radius;
	}

}
