package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class TriangleBumper implements IBumper {
	private double x, y;
	private Color colour;
	private String gizmoName;
	private static final double L = 20;
	private boolean rotated = false;
	
	public TriangleBumper(String gizmoName, double x, double y, Color c) {
	this.x = x*L;
    this.y = y*L;
    this.colour = Color.BLUE;
    this.gizmoName = gizmoName;
	}
	
	@Override
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,	x,	y+L));
		lineSegments.add(new LineSegment(x,		y,	x+L,	y));
		lineSegments.add(new LineSegment(x,	y+L,	x+L,	y));
		return lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x, 	y, 	0));
		circles.add(new Circle(x+L,	y, 		0));
		circles.add(new Circle(x,	y+L,	0));
		return circles;
	}

	public List<LineSegment> rotatedLS() {
		rotated = true;
		List<LineSegment> rLS = new ArrayList<LineSegment>();
		rLS.add(new LineSegment(x,		y,	x+L,	y+L));
		rLS.add(new LineSegment(x,		y,	x+L,	y));
		rLS.add(new LineSegment(x+L,	y+L,	x+L,	y));
		return rLS;
	}
	
	public List<Circle> rotatedC() {
		rotated = true;
		List<Circle> rCircles = new ArrayList<Circle>();
		rCircles.add(new Circle(x, 	y, 	0));
		rCircles.add(new Circle(x+L,	y, 		0));
		rCircles.add(new Circle(x+L,	y+L,	0));
		return rCircles;
	}
	
	public void setRotation() {
		rotated = true;
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

	@Override
	public String getGizmoName() {
		return gizmoName;
	}
   
}