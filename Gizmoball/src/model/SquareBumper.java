package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class SquareBumper implements IBumper {     

	private double x,y;
	private Color colour;
	private String gizmoName;
	private static final double L = 20;

	public SquareBumper(String gizmoName, double x, double y, Color c){
		this.x = x*L;
		this.y = y*L;
		this.colour = Color.RED;
		this.gizmoName = gizmoName;
	}

	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		lineSegments.add(new LineSegment(x,		y,		x+L,	y));
		lineSegments.add(new LineSegment(x+L,	y,		x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y+L,	x+L,	y+L));
		lineSegments.add(new LineSegment(x,		y,		x,		y+L));
		return lineSegments;
	}

	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(new Circle(x,	y,		0));
		circles.add(new Circle(x,	y+L,	0));
		circles.add(new Circle(x+L,	y,		0));
		circles.add(new Circle(x+L,	y+L,	0));
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

	@Override
	public String getGizmoName() {
		return gizmoName;
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}
	
	
}